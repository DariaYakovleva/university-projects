import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Daria on 09.05.2016.
 */


public class Replica {
    public static int NORMAL = 1;
    public static int VIEWCHANGES = 2;
    public static int RECOVERING = 3;
    public static String GET = "get";
    public static String SET = "set";
    public static String DELETE = "delete";
    public static int PPEPARE = 0;
    public static int COMMITED = 1;

    int TIMEOUT = 5; //seconds
    MainLogicThread listenThread;
    Map<Integer, CommThread> replics;
    Map<Integer, Integer> replicsLive;
    Map<Integer, ConcurrentLinkedQueue<Message>> MessageQueue;
    Map<String, String> buffer;
    Map<Integer, Integer> ports;
    PrintWriter report;
    PrintWriter logsFile;
    AtomicInteger State; //текущее состояние
    AtomicInteger Leader; //the primary replica
    File logsF;
    int num;
    int n;


    Replica(int n, int num, int port, int leader, int timeout, Map<Integer, Integer> ports, int state) throws IOException {
        this.n = n;
        this.num = num;
        this.TIMEOUT = timeout;
        this.ports = ports;
        State = new AtomicInteger(state);
        Leader = new AtomicInteger(leader);
        report = new PrintWriter(System.out, true);
        buffer = new ConcurrentHashMap<>();
        MessageQueue = new HashMap<>();
        replics = new HashMap<>();
        replicsLive = new ConcurrentHashMap<>();
        logsF = new File("dkvs_" + (num + 1) + ".log");
        logsF.createNewFile();
        logsFile = new PrintWriter(new FileOutputStream(logsF), true);
        for (Integer numR: ports.keySet()) {
            int portR = ports.get(numR);
            MessageQueue.put(numR, new ConcurrentLinkedQueue<>());
            replics.put(numR, new CommThread(num, portR, MessageQueue.get(numR), report, TIMEOUT, State, Leader));
        }
        listenThread = new MainLogicThread(n, num, port, buffer, MessageQueue, report, State, Leader, timeout, replicsLive, logsFile);
        report.println("PERLICA #" + num + " created");
    }

    void start() throws IOException {
        Scanner logs = new Scanner(new FileInputStream(logsF), "UTF-8");
        buffer.clear();
        while (logs.hasNext()) {
            String state = logs.nextLine();
            String[] kv = state.split(" ");
            if (kv[0].compareTo(SET) == 0) {
                buffer.put(kv[0], kv[1]);
            } else {
                buffer.remove(kv[0]);
            }
        }
        listenThread.start();
        for (int i : replics.keySet()) {
            replics.get(i).start();
            replicsLive.put(i, 1);
            Timer checkReplics = new Timer(true);
            checkReplics.schedule(new TimerTask() {
                @Override
                public void run() {
//                    System.err.println("REPLIVE " + num + "->" + i + " " + replicsLive.get(i));
                    if (replicsLive.get(i) == 0) {
                        report.println("PERLICA #" + num + " restart connection with " + i);
                        replics.get(i).close();
                        replics.put(i, new CommThread(num, ports.get(i), MessageQueue.get(i), report, TIMEOUT, State, Leader));
                        replics.get(i).start();
                        replicsLive.put(i, 1);
                    }
                    replicsLive.put(i, 0);
                }
            }, TIMEOUT * 1000, TIMEOUT * 1000);
        }
        report.println("PERLICA #" + num + " started");
    }

    void stop() throws IOException {
        for (String key : buffer.keySet()) {
            logsFile.append("set " + key + " " + buffer.get(key));
        }
        logsFile.close();
        report.println("PERLICA #" + num + " stopped");
    }
}
