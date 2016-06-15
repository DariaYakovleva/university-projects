import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Homework3 {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("tests/HW3/task3.in"));
		System.setOut(new PrintStream("tests/HW3/task3.out"));
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		List<Expression> pp = findSubstitution(s);
		System.err.println("EXP = " + pp.get(0).printExp());
		System.err.println("VAR = " + pp.get(1).printExp());
		System.err.println("SUB = " + pp.get(2).printExp());
		boolean have = false;
		List<String> free = pp.get(0).freeVariables(new ArrayList<String>());
		for (String v : free) {
			if (v.equals(pp.get(1).printExp())) {
				have = true;
				break;
			}
		}
		if (!have) {
			System.out.println("Ќет свободы дл€ подстановки переменной " + pp.get(1).printExp());
			System.err.println("Ќет свободы дл€ подстановки переменной " + pp.get(1).printExp());
		} else {
			Expression res = pp.get(0).substitution(new ArrayList<Long>(), pp.get(1), pp.get(2));
			System.out.println(res.printExp());
			System.err.println(res.printExp());
		}
		in.close();
	}
	
	private static List<Expression> findSubstitution(String s) {
			int start = s.indexOf('[');
			int end = s.indexOf(':');
			Expression expr = LambdaParser.parse(s.substring(0, start));
			Expression var = LambdaParser.parse(s.substring(start + 1, end));
			Expression sub = LambdaParser.parse(s.substring(end + 2, s.length() - 1));
			List<Expression> res = new ArrayList<>();
			res.add(expr);
			res.add(var);
			res.add(sub);
			return res;
	}
	
}
