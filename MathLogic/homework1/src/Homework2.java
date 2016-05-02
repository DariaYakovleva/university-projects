import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;


public class Homework2 {


	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("proof0.in"));
		System.setIn(new FileInputStream("input.txt"));
		Scanner in = new Scanner(System.in);
		System.setOut(new PrintStream("output.txt"));
		String state = in.nextLine();
		List<Expression> exprs = new ArrayList<>();
		while (in.hasNext()) {
			String s = in.nextLine();
			exprs.add((new ExpressionParser(s)).res);
		}	
		List<Expression> res = (new Deduction(state, exprs)).getStatements();
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i).printExp());
		}
		in.close();
	}
}