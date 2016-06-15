import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Token;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static String print(GrammarParser.Node ver, String pref) {
        String res = Integer.toString(ver.res);
        String add = "";
        for (int i = 0; i < res.length(); i++) add += " ";
//        pref += add;
        for (int i = 0; i < ver.children.size(); i++) {
            if (i + 1 < ver.children.size()) {
                add = "|" + add;
            } else {
                add += " ";
            }
            GrammarParser.Node ch = ver.children.get(i);
            if (i == 0) {
                res += " -> " + print(ch, pref + add);
            } else {
                res += pref + "| -> " + print(ch, pref + add);
            }
            if (i + 1 < ver.children.size()) res += "\n";
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
        MyParser parser = new MyParser();
        try {
//            parser.parse("myGrammar.g4", "start");
            GrammarParser result = new GrammarParser();
            GrammarParser.Node res = result.parse("input.in");
            System.err.println(res.res);
//            System.err.println(print(res, ""));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
