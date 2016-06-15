import java.io.IOException;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {
        System.err.println("Hello, world!");
        MyParser parser = new MyParser();
        try {
            parser.parse("myGrammar.g4", "start");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
