import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Daria on 26.01.2016.
 */
public class Homework6 {

//    static List<Expression> res = new ArrayList();

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("tests/HW6/test6.in"));
        System.setOut(new PrintStream("tests/HW6/test.out"));
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Expression res = LambdaParser.parse(s);
        System.err.println("Main expr: " + res.printExp2());
        Map<Expression, Expression> types = new HashMap<>();
        List<Expression> equats = new ArrayList<>();
        List<Integer> cnt = new ArrayList<>();
        cnt.add(0);
        res.getSetEquations(types, equats, cnt);
        System.err.println("System:");
        for (Expression exp: equats) {
            System.err.println(exp.printExp2());
        }
        equats = SetEquations.getSolution(equats);
        if (equats.isEmpty()) {
            System.out.println("Lambda expression has no type");
            System.err.println("Lambda expression has no type");
        }
        System.err.println("Solution:");
        for (Expression exp: equats) {
            System.err.println(exp.printExp2());
        }
        System.err.println("Types:");
        for (Expression exp: types.keySet()) {
            System.err.println(exp.printExp2() + ": " + types.get(exp).printExp2());
        }
        for (Expression exp: equats) {
            Expression a = ((Equation)exp).e1;
            Expression b = ((Equation)exp).e2;
            for (Expression exp2: types.keySet()) {
                types.replace(exp2, types.get(exp2).replace(a, b));
            }
        }
        System.err.println("Result:");
        if (!equats.isEmpty()) {
            for (Expression exp : types.keySet()) {
                if (exp.isEqual(res)) {
                    System.err.println(types.get(exp).printExp2());
                    System.out.println(types.get(exp).printExp2());
                }
            }
        }
        in.close();
    }
}
