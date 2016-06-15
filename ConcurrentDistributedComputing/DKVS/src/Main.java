import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        DistributedStorage kvs = new DistributedStorage(1);
        try {
            if (args.length == 0) {
                kvs.start(-1);
            } else {
                kvs.start(Integer.parseInt(args[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
