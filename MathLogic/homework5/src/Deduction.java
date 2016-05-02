import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


public class Deduction extends MakeExpr {

	public List<Expression> hypothesis = new ArrayList<>();
	public List<Expression> exprs = new ArrayList<>();
	public Expression alpha;
	public Expression state;
	
	public void hyParser(String s) {
		int pos = 0;
		s = s.replaceAll("\\s", "");
		while (!s.substring(pos, pos + 2).equals("|-")) {
			String exp = "";
			while (s.charAt(pos) != ',' && !s.substring(pos, pos + 2).equals("|-")) {
				exp += s.charAt(pos);
				pos++;
			}
			if (s.charAt(pos) == ',') {
				hypothesis.add(ExpressionParser.parse(exp));
				pos++;
			} else {
				alpha = ExpressionParser.parse(exp);
			}
			exprs.add(ExpressionParser.parse(exp));
		}
		state = ExpressionParser.parse(s.substring(pos + 2));
	}
	
	public int compWithHyp(Expression a) {
		for (int i = 0; i < hypothesis.size(); i++) {
			if (equalT(hypothesis.get(i), a)) {
				return i;
			}
		}
		return -1;
	}
	
	public void makeDeduction() {
		for (int i = 0; i < exprs.size(); i++) {
			Expression exp = exprs.get(i);
			int ax = compWithAx(exp);
			int hyp = compWithHyp(exp);
			if (ax != -1) {
				statements.add(exp);
				statements.add(new Implication(exp, new Implication(alpha, exp)));
				statements.add(new Implication(alpha, exp));
			} else if (hyp != -1) {
				statements.add(new Implication(exp, new Implication(alpha, exp)));
				statements.add(new Implication(alpha, exp));
			} else if (equalT(alpha, exp)) {
				statements.addAll(aToA(alpha));
			} else {
				Pair<Integer, Integer> mp = modusPonens(exp, exprs);
				if (mp != null) {
					Expression gj;
					if (exprs.get(mp.getKey()).equalTree(new Implication(exprs.get(mp.getValue()), exp))) {
						gj = exprs.get(mp.getValue());
					} else {
						gj = exprs.get(mp.getKey());
					}
					//(A -> gj) -> ((A -> (gj -> gi)) -> (A -> gi))
					Expression tmp = new Implication(new Implication(alpha, new Implication(gj, exp)), new Implication(alpha, exp));
					statements.add((new Implication(new Implication(alpha, gj), tmp)));
					statements.add(tmp);
					statements.add(new Implication(alpha, exp));
				} else {
					System.out.println("null" + alpha.printExp() + "==" + exp.printExp());
					System.out.println("BEGIN========");
					System.out.println(alpha.printExp() + "|-" + state.printExp());
					for (int h = 0; h < exprs.size(); h++) {
						System.out.println(exprs.get(h).printExp());
					}
					System.out.println("END========");
				} 
			}
		}
	}

	
	public Deduction(String s, List<Expression> pr) {
		hyParser(s);
		exprs.addAll(pr);
//		System.out.println("BEGIN========");
//		System.out.println(s);
//		System.out.println("alpha=" + alpha.printExp());
//		for (int i = 0; i < exprs.size(); i++) {
//			System.out.println(exprs.get(i).printExp());
//		}
//		System.out.println("END========");
	}
	
	public List<Expression> getStatements() {
		makeDeduction();
		return statements;
	}

}
