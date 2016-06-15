import com.sun.org.apache.regexp.internal.RE;
import javafx.util.Pair;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Daria on 09.05.2016.
 */


class MainLogicThread extends Thread {

    int num;
    int n;
    Map<String, String> buffer;
    PrintWriter logs;
    AtomicInteger ViewNumber;
    AtomicInteger OperationNumber; //номер операции
    AtomicInteger CommitNumber;
    AtomicInteger State; //текущее состояние
    AtomicInteger Leader;
    Map<Integer, Integer> OKCounter;
    ServerSocket server;
    Map<Integer, ConcurrentLinkedQueue<Message>> messageQueue;
    Map<Integer, PrintWriter> clients;
    List<Pair<String, AtomicInteger>> log;
    Map<Integer, Integer> replicsLive;

    AtomicInteger LeaderLive = new AtomicInteger(1);
    AtomicInteger ViewChangesCnt = new AtomicInteger(0);
    AtomicInteger doViewChangesCnt = new AtomicInteger(0);
    AtomicInteger lastView;
    AtomicInteger RecoveryConter = new AtomicInteger(0);
    Message lastUpdate = null;
    PrintWriter logsFile;
    int timeout;

    public MainLogicThread(int n, int num, int port, Map<String, String> buffer, Map<Integer, ConcurrentLinkedQueue<Message>> messageQueue, PrintWriter logs,
                           AtomicInteger State, AtomicInteger Leader, int timeout, Map<Integer, Integer> replicsLive, PrintWriter logsFile) {
        try {
            server = new ServerSocket(port);
            this.n = n;
            this.num = num;
            this.buffer = buffer;
            this.logs = logs;
            this.timeout = timeout;
            this.replicsLive = replicsLive;
            OperationNumber = new AtomicInteger(0);
            this.messageQueue = messageQueue;
            OKCounter = new HashMap<>();
            ViewNumber = new AtomicInteger(0);
            lastView = new AtomicInteger(ViewNumber.get());
            CommitNumber = new AtomicInteger(0);
            this.State = State;
            this.Leader = Leader;
            this.logs.println(num + ".MainLogicThread: created");
            clients = new ConcurrentHashMap<>();
            log = new ArrayList<>();
            this.logsFile = logsFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        logs.println(num + ".MainLogicThread: is running");
        Timer checkLeader = new Timer(true);
        checkLeader.schedule(new TimerTask() {
            @Override
            public void run() {
                if (num != Leader.get()) {
//                    System.err.println("TIMER " + LeaderLive.get());
                    if (LeaderLive.get() == 0) {
                        logs.println(num + ".MainLogicThread: " + "CHANGE LEADER: " + Leader.get());
                        changeLeader();
                    }
                    LeaderLive.set(0);
                } else {
//                    System.err.println("COMMIT " + CommitNumber.get() + " " + CommitNumber.hashCode());
                    Message commit = new Message(Request.COMMIT, num, OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
                    for (Integer backups : messageQueue.keySet()) {
                        if (backups != Leader.get()) messageQueue.get(backups).add(commit);
                    }
                }
            }
        }, timeout * 1000, timeout * 1000);

        if (State.get() == Replica.RECOVERING) recoveryProtocol();

        while (true) {
            Socket connection = null;
            try {
                connection = server.accept();
            } catch (IOException e) {
                break;
            }
            Socket finalConnection = connection;
            Thread connectionThread = new Thread(new Runnable() {
                public void run() {
                    Socket client = finalConnection;
                    InputStream clientIn = null;
                    OutputStream clientOut = null;
                    try {
                        clientIn = client.getInputStream();
                        clientOut = client.getOutputStream();
                    } catch (IOException e) {
                        try {
                            client.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        return;
                    }
                    BufferedReader request = new BufferedReader(new InputStreamReader(clientIn));
                    PrintWriter response = new PrintWriter(clientOut, true);
                    clients.put(client.hashCode(), response);
                    while (true) {
                        try {
                            String req = request.readLine();
                            if (req == null) continue;
                            if (!req.contains("COMMIT") && !req.contains("PING") && !req.contains("PONG"))
                                logs.println(num + ".MainLogicThread: " + "REQUEST: " + req);
                            String res = process(req, client.hashCode());
                            if (res != null) {
                                logs.println(num + ".MainLogicThread: " + "RESPONSE: " + res);
                                response.println("Server #" + num + " :" + res);
                            }
                        } catch (IOException e) {
                            try {
                                request.close();
                                response.close();
                            } catch (IOException e1) {}
                            break;
                        }
                    }
                    clients.remove(client.hashCode());
                }
            });
            connectionThread.start();
        }
    }

    String process(String req, int clientNumber) throws IOException {
        String[] command = req.split(" ");
        if (command[0].compareTo(Replica.GET) == 0) {
            if (!buffer.containsKey(command[1])) return "NOT_FOUND";
            return buffer.get(command[1]);
        }
        if (command[0].compareTo(Replica.SET) == 0 || command[0].compareTo(Replica.DELETE) == 0) {
            if (num != Leader.get()) {
                Message toLeader = new Message();
                toLeader.text = req + " " + num + " " + clientNumber;
                messageQueue.get(Leader.get()).add(toLeader);
                return null;
            }
            int op = OperationNumber.getAndIncrement();
            Message message = new Message(Request.PREPARE, num, op, ViewNumber.get(), CommitNumber.get());
            if (req.contains(Replica.SET) && command.length > 3 || req.contains(Replica.DELETE) && command.length > 2) {
                message.command = req;
                clientNumber = Integer.parseInt(command[command.length - 1]);
                int server = Integer.parseInt(command[command.length - 2]);
                replicsLive.put(server, 1);
            } else {
                message.command = req + " " + num + " " + clientNumber;
            }
            log.add(new Pair<String, AtomicInteger>(message.command, new AtomicInteger(Replica.PPEPARE)));
            message.clientNumber = clientNumber;
            for (Integer backups : messageQueue.keySet()) {
                if (backups != Leader.get()) messageQueue.get(backups).add(message);
            }
            return null;
        }

        Message in = new Message(req);

        if (in.server == Leader.get()) LeaderLive.set(1);
        replicsLive.put(in.server, 1);

        switch (in.request) {
            case SET: {
                buffer.put(in.key, in.value);
                logsFile.println(Replica.SET + " " + in.key + " " + in.value);
            }
            break;
            case DELETE: {
                buffer.remove(in.key);
                logsFile.println(Replica.DELETE + " " + in.key);
            }
            break;
            case PREPARE: {
                for (int i = 0; i < log.size(); i++) {
                    String curReq = log.get(i).getKey();
                    if (log.get(i).getValue().get() == Replica.PPEPARE) {
                        if (i < in.CommitNumber) {
                            executeOperation(curReq, i, false);
                        } else {
                            while (i < log.size() && log.get(i).getValue().get() == Replica.PPEPARE) {
                                System.err.println("LOL");
                            }
                        }
                    }
                }
                int op = OperationNumber.getAndIncrement();
                Message ans = new Message(req);
                log.add(new Pair<String, AtomicInteger>(ans.command, new AtomicInteger(Replica.PPEPARE)));
                ans.request = Request.PREPARE_OK;
                ans.server = num;
                messageQueue.get(Leader.get()).add(ans);
            }
            break;
            case PING: {
                Message message = new Message();
                message.server = num;
                message.request = Request.PONG;
                messageQueue.get(in.server).add(message);
            }
            break;
            case PONG: {
                replicsLive.put(in.server, 1);
            }
            break;
            case COMMIT: {
                for (int i = 0; i < log.size(); i++) {
                    String curReq = log.get(i).getKey();
                    if (log.get(i).getValue().get() == Replica.PPEPARE) {
                        if (i < in.CommitNumber) {
                            executeOperation(curReq, i, false);
                        }
                    }
                }
            }
            break;
            case PREPARE_OK: {
                int numOp = in.OperationNumber;
                int cnt = 1;
                if (OKCounter.containsKey(numOp)) cnt = OKCounter.get(numOp) + 1;
                if (cnt == 0) break;
                if (cnt < n) {
                    OKCounter.put(numOp, cnt);
                } else {
                    for (int i = 0; i < log.size(); i++) {
                        String curReq = log.get(i).getKey();
                        if (log.get(i).getValue().get() == Replica.PPEPARE) {
                            if (i < in.OperationNumber) {
                                executeOperation(curReq, i, true);
                            }
                        }
                    }
                    int com = CommitNumber.getAndIncrement();
                    OKCounter.put(numOp, -1);
                    executeOperation(in.command, numOp, true);
                }
            }
            break;
            case STARTVIEWCHANGE: {
                if (ViewChangesCnt.get() == -1) return null;
                if (ViewChangesCnt.incrementAndGet() == n) {
                    Message doChanges = new Message(Request.DOVIEWCHANGE, num,
                            OperationNumber.get(), in.ViewNumber, CommitNumber.get());
                    doChanges.LastNormalView = ViewNumber.get();
                    if (State.get() != Replica.NORMAL) doChanges.LastNormalView = lastView.get();
                    doChanges.setLog(log);
                    int leader = Leader.get();
                    Leader.compareAndSet(leader, (leader + 1) % (2 * n + 1));
                    messageQueue.get(Leader.get()).add(doChanges);
                    ViewChangesCnt.set(-1);
                }
            }
            break;
            case DOVIEWCHANGE: {
                if (doViewChangesCnt.get() == -1) return null;
                if (lastUpdate == null || in.LastNormalView > lastUpdate.LastNormalView ||
                        (in.LastNormalView == lastUpdate.LastNormalView && in.OperationNumber > lastUpdate.OperationNumber)) {
                    lastUpdate = in;
                }
                if (doViewChangesCnt.incrementAndGet() == n + 1) {
                    List<Pair<String, AtomicInteger>> prevLog = log;
                    log = lastUpdate.getLog();
                    OperationNumber.set(lastUpdate.OperationNumber);
                    CommitNumber.set(lastUpdate.CommitNumber);
                    Message startView = new Message(Request.STARTVIEW, num,
                            OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
                    startView.setLog(log);
                    State.compareAndSet(Replica.VIEWCHANGES, Replica.NORMAL);
                    for (int i = 0; i < messageQueue.size(); i++) {
                        ConcurrentLinkedQueue que = messageQueue.get(i);
                        if (num != i) que.add(startView);
                    }
                    for (int i = 0; i < log.size(); i++) {
                        if ((i >= prevLog.size() || prevLog.get(i).getValue().get() == Replica.PPEPARE) && log.get(i).getValue().get() == Replica.COMMITED) {
                            executeOperation(log.get(i).getKey(), i, false);
                        }
                    }
                    lastUpdate = null;
                    doViewChangesCnt.set(-1);
                }
            }
            break;
            case STARTVIEW: {
                List<Pair<String, AtomicInteger>> prevLog = log;
                log = in.getLog();
                OperationNumber.set(in.OperationNumber);
                ViewNumber.set(in.ViewNumber);
                CommitNumber.set(in.CommitNumber);
                State.compareAndSet(Replica.VIEWCHANGES, Replica.NORMAL);
                ViewChangesCnt.set(0);
                doViewChangesCnt.set(0);
                for (int i = 0; i < log.size(); i++) {
                    if (i < CommitNumber.get()) {
                        if ((i >= prevLog.size() || prevLog.get(i).getValue().get() == Replica.PPEPARE) && log.get(i).getValue().get() == Replica.COMMITED) {
                            executeOperation(log.get(i).getKey(), i, false);
                        }
                    } else {
                        int op = OperationNumber.getAndIncrement();
                        Message ans = new Message(Request.PREPARE_OK, num,
                                OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
                        ans.command = log.get(i).getKey();
                        ans.server = num;
                        messageQueue.get(Leader.get()).add(ans);
                    }
                }
            }
            break;
            case REPLY: {
//                System.err.println("REPLY " + in.command + " to " + in.clientNumber + "; " + clients.get(in.clientNumber));
                clients.get(in.clientNumber).println(in.command);
            }
            break;
            case RECOVERY: {
                if (State.get() != Replica.NORMAL) return null;
                Message response = new Message(Request.RECOVERYRESPONSE, num, OperationNumber.get(),
                        ViewNumber.get(), CommitNumber.get());
                response.time = System.currentTimeMillis();
                if (num == Leader.get()) {
                    response.setLog(log);
                } else {
                    response.CommitNumber = -1;
                    response.OperationNumber = -1;
                }
                messageQueue.get(in.server).add(response);
            }
            break;
            case RECOVERYRESPONSE: {
                if (State.get() == Replica.NORMAL) return null;
                if (in.CommitNumber >= 0) {
                    Leader.set(in.server);
                    OperationNumber.set(in.OperationNumber);
                    CommitNumber.set(in.CommitNumber);
                    ViewNumber.set(in.ViewNumber);
                    log = in.getLog();
                }
                if (RecoveryConter.incrementAndGet() >= n + 1 && CommitNumber.get() >= 0) {
                    if (!State.compareAndSet(Replica.RECOVERING, Replica.NORMAL)) {
                        System.err.println("recovery -> normal :(");
                    }
                }
            }
            break;
        }
        return null;
    }

    public void executeOperation(String command, int opNum, boolean needReply) {
        Message request = new Message();
        String[] req2 = command.split(" ");
        request.request = Request.valueOf(req2[0].toUpperCase());
        request.key = req2[1];
        if (req2.length > 2) request.value = req2[2];
        log.get(opNum).getValue().set(Replica.COMMITED);
        messageQueue.get(num).add(request);
        if (needReply) {
            Message ans = new Message(Request.REPLY, num, OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
            switch (request.request) {
                case SET: {
                    ans.command = "STORED";
                }
                break;
                case DELETE: {
                    ans.command = "DELETED";
                }
                break;
            }
            ans.clientNumber = Integer.parseInt(req2[req2.length - 1]);
            messageQueue.get(Integer.parseInt(req2[req2.length - 2])).add(ans);
        }
    }

    public void changeLeader() {
        if (!State.compareAndSet(Replica.NORMAL, Replica.VIEWCHANGES)) return;
        lastView.set(ViewNumber.get());
        ViewNumber.getAndIncrement();
        Message message = new Message(Request.STARTVIEWCHANGE, num, OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
        for (ConcurrentLinkedQueue que : messageQueue.values()) {
            que.add(message);
        }
    }
    public void recoveryProtocol() {
        logs.println(num + ".MainLogicThread: " + "START RECOVERY ");
        if (!State.compareAndSet(Replica.NORMAL, Replica.RECOVERING)) return;
        Message message = new Message(Request.RECOVERY, num, OperationNumber.get(), ViewNumber.get(), CommitNumber.get());
        message.time = System.currentTimeMillis(); //nonce
        for (ConcurrentLinkedQueue que : messageQueue.values()) {
            que.add(message);
        }
    }
}
