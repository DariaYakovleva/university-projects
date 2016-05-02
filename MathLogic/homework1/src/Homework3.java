import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Homework3 extends MakeExpr {

	public static List< List<Expression> > proof = new ArrayList<>(); 
	static String s;
	static List<String> variables;
	static int cntV;

	static void goLexem(int mask, int k) {
		int first = mask << k;
		String firstS = "";
		for (int i = 0; i < cntV - k + 1; i++) {
			if ((first & (1 << (cntV - 1 - i))) > 0) {
				firstS += variables.get(i);
			} else {
				firstS += "!" + variables.get(i);
			}
			if (i + 1 < cntV - k + 1) {
				firstS += ", ";
			} else {
				firstS += " |- ";
			}
		}
		firstS += s;
		int second = ((mask << 1) + 1) << (k - 1);
		String secondS = "";
		for (int i = 0; i < cntV - k + 1; i++) {
			if ((second & (1 << (cntV - 1 - i))) > 0) {
				secondS += variables.get(i);
			} else {
				secondS += "!" + variables.get(i);
			}
			if (i + 1 < cntV - k + 1) {
				secondS += ", ";
			} else {
				secondS += " |- ";
			}
		}
		secondS += s;
		List<Expression> merge = new ArrayList<>();
		merge.addAll((new Deduction(firstS, proof.get(first))).getStatements());
//		System.out.println("    END " + firstS + " " + proof.get(first).size());
		merge.addAll((new Deduction(secondS, proof.get(second))).getStatements());
//		System.out.println("    END " + secondS + " " + proof.get(second).size());
		merge.addAll(merging((new ExpressionParser(s)).res, (new ExpressionParser(variables.get(cntV - k))).res));
//		System.out.println("    END MERGING");
		proof.get(first).clear();
		proof.get(second).clear();
		proof.get(first).addAll(merge);
//		System.out.println("    ADD " + proof.get(first).size());
	}

	public static void main(String[] args) throws IOException {

//		System.setIn(new FileInputStream("input.txt"));
		System.setIn(new FileInputStream("HW3/true6.in"));
		System.setOut(new PrintStream("HW3/input.in"));
//		System.setOut(new PrintStream("output.txt"));
		Scanner in = new Scanner(System.in);
		s = in.nextLine();
		ExpressionParser myParser = new ExpressionParser(s);
		Expression exp = myParser.res;
		variables = myParser.getVariables();
		cntV = variables.size();
		boolean bad = false;
		// 0 = !A, 1 = A
		for (int mask = 0; mask < (1 << cntV); mask++) {
			if (!exp.calculate(variables, mask)) {
				bad = true;
				System.out.print("Высказывание ложно при ");
				for (int i = 0; i < cntV; i++) {
					System.out.print(variables.get(i) + "=");
					if ((mask & (1 << (cntV - i - 1))) > 0) {
						System.out.print("И");
					} else {
						System.out.print("Л");
					}
					if (i + 1 == cntV) {
						System.out.println();
					} else {
						System.out.print(", ");
					}
				}
				break;
			} else {
				List<Expression> curProof = exp.getProof(variables, mask);
				proof.add(curProof);
			}
		}
		if (!bad) {
//			System.out.println("goLexem");
			for (int shl = 1; shl <= cntV; shl++) {
				for (int mask = 0; mask < (1 << (cntV - shl)); mask++) {
//					System.out.println("goLexem " + shl + " " + mask);
					goLexem(mask, shl);
				}
			}
//			System.out.println("print");
			for (int i = 0; i < proof.get(0).size(); i++) {
				System.out.println(proof.get(0).get(i).printExp());
			}
			for (int i = 1; i < proof.size(); i++) {
				if (proof.get(i).size() != 0) System.out.println("!!! = " + i);
			}

		}
		in.close();
	}
}
