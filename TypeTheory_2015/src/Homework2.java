import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Homework2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("tests/HW2/task2.in"));
		System.setOut(new PrintStream("tests/HW2/task2.out"));
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		Expression exp = LambdaParser.parse(s);
		System.err.println(exp.printExp());
		List<String> res = exp.freeVariables(new ArrayList<String>());
		res.sort(null);
		if (res.isEmpty()) System.err.println("No free variables");
		for (String v : res) {
			System.err.println(v);
			System.out.println(v);
		}
		in.close();
	}
}
