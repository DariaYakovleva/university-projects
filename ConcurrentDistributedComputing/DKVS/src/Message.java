import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Daria on 05.06.2016.
 */
enum Request {
    GET, SET, DELETE, PREPARE, PING, PONG, COMMIT, PREPARE_OK,
    STARTVIEWCHANGE, DOVIEWCHANGE, STARTVIEW, RECOVERY, RECOVERYRESPONSE, REPLY;
}

public class Message {
    Request request;
    String command = "";
    String text = "";
    String key = "";
    String value = "";
    String log = "";
    int OperationNumber = -1;
    int ViewNumber = -1;
    int LastNormalView = -1;
    int CommitNumber = -1;
    int server = -1;
    int clientNumber = -1;
    long time = -1;

    public Message() {
    }

    public Message(String request) {
        String[] fields = request.split(";");
        for (String field : fields) {
            String[] kv = field.split("=");
            if (kv.length == 1) continue;
            switch (kv[0]) {
                case "request": {
                    this.request = Request.valueOf(kv[1]);
                }
                break;
                case "command": {
                    command = kv[1];
                }
                break;
                case "key": {
                    key = kv[1];
                }
                break;
                case "value": {
                    value = kv[1];
                }
                break;
                case "operation": {
                    OperationNumber = Integer.parseInt(kv[1]);
                }
                break;
                case "view": {
                    ViewNumber = Integer.parseInt(kv[1]);
                }
                break;
                case "commit": {
                    CommitNumber = Integer.parseInt(kv[1]);
                }
                break;
                case "server": {
                    server = Integer.parseInt(kv[1]);
                }
                break;
                case "client": {
                    clientNumber = Integer.parseInt(kv[1]);
                }
                break;
                case "time": {
                    time = Long.parseLong(kv[1]);
                }
                break;
                case "last": {
                    LastNormalView = Integer.parseInt(kv[1]);
                }
                break;
                case "log": {
                    log = kv[1];
                }
                break;
            }
        }
    }

    public Message(Request request, int server, int OperationNumber, int ViewNumber, int CommitNumber) {
        this.request = request;
        this.server = server;
        this.OperationNumber = OperationNumber;
        this.ViewNumber = ViewNumber;
        this.CommitNumber = CommitNumber;
    }

    String getRequest() {
        if (!text.isEmpty()) return text;
        String req = "request=" + request.name() + ";";
        req += "command=" + command + ";";
        req += "key=" + key + ";";
        req += "value=" + value + ";";
        req += "operation=" + OperationNumber + ";";
        req += "view=" + ViewNumber + ";";
        req += "commit=" + CommitNumber + ";";
        req += "server=" + server + ";";
        req += "client=" + clientNumber + ";";
        req += "time=" + time + ";";
        req += "last=" + time + ";";
        req += "log=" + log + ";";
        return req;
    }

    public void setLog(List<Pair<String, AtomicInteger>> logs) {
        if (logs.isEmpty()) {
            log = "[]";
            return;
        }
        log = "[";
        for (Pair<String, AtomicInteger> sLog: logs) {
            log += sLog.getKey() + ":" + sLog.getValue().get() + ",";
        }
        log = log.substring(0, log.length() - 1) + "]";
    }

    public List<Pair<String, AtomicInteger>> getLog() {
        System.err.println("get LOG " + log);
        List<Pair<String, AtomicInteger>> nLog = new ArrayList<>();
        if (log.length() <= 2) return nLog;
        String[] arLog = log.substring(1, log.length() - 1).split(",");
        for (int i = 1; i < arLog.length; i++) {
            String[] req = arLog[i].split(":");
            nLog.add(new Pair<>(req[0], new AtomicInteger(Integer.parseInt(req[1]))));
        }
        return nLog;
    }
}
