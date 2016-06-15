

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Daria on 23.02.2016.
 */
public class Homework4 {

    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("tests/HW4/oneminute.in")); //too long
        System.setIn(new FileInputStream("tests/HW4/fibslow2.in"));
//        System.setIn(new FileInputStream("tests/HW4/test10.in"));
        //1 3
        System.setOut(new PrintStream("tests/HW4/test.out"));
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Expression exp = LambdaParser.parse(s);
        System.err.println(exp.printExp());
        Map<Long, Expression> headNormals = new HashMap<Long, Expression>();
        Expression res = exp.getNormalForm(headNormals);
        System.err.println(res.printExp());
        System.out.println(res.printExp());

        in.close();
    }


}
