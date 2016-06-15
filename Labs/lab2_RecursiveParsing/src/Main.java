import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Hello");
        Scanner reader = new Scanner(new FileInputStream("input.in"));
        String s = reader.nextLine();
        try {
            Tree tree = new Parser().parse(s);
            System.out.print(tree.print(""));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
