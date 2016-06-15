import org.omg.CORBA.TIMEOUT;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Daria on 05.06.2016.
 */

class CommThread extends Thread {

    int port;
    PrintWriter logs;
    Socket replica;
    ConcurrentLinkedQueue<Message> messageQueue;
    int timeout;
    int num;
    AtomicInteger State;
    AtomicInteger Leader;
    boolean work;

    public CommThread(int num, int port, ConcurrentLinkedQueue<Message> messageQueue, PrintWriter logs, int timeout,
                      AtomicInteger State, AtomicInteger Leader) {
        this.num = num;
        this.port = port;
        this.messageQueue = messageQueue;
        this.logs = logs;
        this.timeout = timeout;
        this.State = State;
        this.Leader = Leader;
        work = true;
        logs.println(num + ".CommThread." + port + ":" + " created");
    }

    public void run() {
        while (true) {
            try {
                work = true;
                replica = new Socket(InetAddress.getLocalHost(), port);
                BufferedReader in = new BufferedReader(new InputStreamReader(replica.getInputStream()));
                PrintWriter out = new PrintWriter(replica.getOutputStream(), true);
                logs.println(num + ".CommThread." + port + ":" + " is running");
                while (work) {
                    if (!messageQueue.isEmpty()) {
                        Message message = messageQueue.poll();
                        message.server = num;
                        out.println(message.getRequest());
                        if (message.request != Request.COMMIT && message.request != Request.PONG)
                            logs.println(num + ".CommThread." + port + ": send message: " + message.getRequest());
                    } else {
                        Message message = new Message();
                        message.server = num;
                        message.request = Request.PING;
                        out.println(message.getRequest());
                        sleep(timeout * 100);
//                    logs.println(num + ".CommThread." + port + ": send message: " + "ping\n");
                    }
                }
            } catch (IOException e) {
                logs.println(num + ".CommThread." + port + ":" + " can't connect");
                close();
            } catch (InterruptedException e) {
                logs.println(num + ".CommThread." + port + ":" + " interrupted");
                close();
            }
        }
    }

    public void close() {
        work = false;
//        try {
//            replica.shutdownInput();
//            replica.shutdownOutput();
//            replica.close();
//        } catch (IOException e) {
//            e.printStackTrace(); //TODO need?
//        }
    }
}
