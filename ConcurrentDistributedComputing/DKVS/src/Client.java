import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Daria on 09.05.2016.
 */
public class Client {

    public static void main(String[] args) {
        int serverPort = Integer.parseInt(args[0]);
//        String address = "127.0.0.1";
        try {
//            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String req = keyboard.readLine();
                out.println(req);
                System.out.println("REQ: " + req);
                String ans = in.readLine();
                System.out.println("ANS: " + ans);
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}