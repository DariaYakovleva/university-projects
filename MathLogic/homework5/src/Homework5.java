import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Daria on 03.10.2015.
 */
public class Homework5 {

    public static void main(String[] args) throws IOException {

//		System.setIn(new FileInputStream("input.txt"));
        System.setIn(new FileInputStream("HW5/input.in"));
		System.setOut(new PrintStream("HW5/output.txt"));
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        ExpressionParser myParser = new ExpressionParser(s);
        Expression exp = myParser.res;
        System.err.println(exp.printExp());
        Tree kripke = exp.buildTree(false, new Tree(), new HashSet<Expression>() );
        if (kripke != null) {
            System.err.println(kripke.printTree(1));
            System.err.println(exp.checkTree(kripke));
        }
        if (kripke == null || exp.checkTree(kripke)) {
            System.out.println("Formula obsheznachima");
            System.err.println("Formula obsheznachima");
        } else {
            System.out.println(kripke.printTree(1));
        }
        in.close();
    }
}
