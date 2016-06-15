import javafx.util.Pair;

import java.io.*;
import java.util.*;

/**
 * Created by Daria on 09.05.2016.
 */
public class DistributedStorage {

    int n;
    Map<Integer, Replica> nodes;
    Map<Integer, Integer> ports = new HashMap<>();
    int timeout;
    int leader = 0;

    public DistributedStorage(int n) {
        this.n = n;
        nodes = new HashMap<>();
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File("dkvs.properties"));
            Scanner reader = new Scanner(file);
            for (int i = 0; i < 2 * n + 1; i++) {
                String name = reader.nextLine();
                int num = Integer.parseInt(name.substring(name.indexOf('.') + 1, name.indexOf('=')));
                int port = Integer.parseInt(name.substring(name.indexOf(':') + 1));
                ports.put(num, port);
            }
            timeout = Integer.parseInt(reader.nextLine().split("=")[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void start(int k) throws IOException {
        if (k == -1) {
            for (int num: ports.keySet()) {
                nodes.put(num, new Replica(n, num, ports.get(num), leader, timeout, ports, Replica.NORMAL));
            }
            for (Replica r: nodes.values()) {
                r.start();
            }
            return;
        }
        if (ports.get(k) != null) {
            int state = Replica.NORMAL;
            if (nodes.containsKey(k)) {
                nodes.get(k).stop();
                System.err.println("RECOVERING START");
                state = Replica.RECOVERING;
            }
            nodes.put(k, new Replica(n, k, ports.get(k), leader, timeout, ports, state));
            nodes.get(k).start();
        } else {
            System.err.println("No server with number " + k);
        }
    }
}
