import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Daria on 25.01.2016.
 */
public class Homework5 {

    static List<Expression> res = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("tests/HW5/test100.in"));
//        System.setIn(new FileInputStream("tests/HW5/nosolution1.in"));
        System.setOut(new PrintStream("tests/HW5/test.out"));
        Scanner in = new Scanner(System.in);
        List<Expression> equations = new ArrayList();
        while (in.hasNext()) {
            String s = in.nextLine();
            equations.add(ExpressionParser.parse(s));
        }
        List<Expression> res = SetEquations.getSolution(equations);
        if (res.isEmpty()) {
            System.out.println("System is inconsistent");
            System.err.println("System is inconsistent");
        } else {
            for (Expression r: res) {
                System.out.println(r.printExp());
                System.err.println(r.printExp());
            }
        }
        in.close();
    }
}
