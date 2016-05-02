import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class Homework1 {
	
	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("HW3/input.in"));
//		System.setIn(new FileInputStream("HW1/good2.in"));
		System.setOut(new PrintStream("HW3/output.out"));
//		System.setOut(new PrintStream("output.out"));
		Scanner in = new Scanner(System.in);
		List<Expression> exprs = new ArrayList<>();
		while (in.hasNext()) {
			String s = in.nextLine();
			exprs.add(ExpressionParser.parse(s));
		}
		List<String> res = (new Correctness(exprs)).getStatements();
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
		in.close();
	}
}