package homework4;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Deduction extends MakeExpr {

	public List<Expression> hypothesis = new ArrayList<>();
	public List<Expression> exprs = new ArrayList<>();
	public Expression alpha;
	public Expression state;

	public void hyParser(String s) {
		int pos = 0;
		s = s.replaceAll("\\s", "");
		if (!s.contains("|-")) {
			alpha = null;
			state = null;
			return;
		}
		int balance = 0;
		while (!s.substring(pos, pos + 2).equals("|-")) {
			String exp = "";
			while ((s.charAt(pos) != ',' || balance > 0) && !s.substring(pos, pos + 2).equals("|-")) {
				exp += s.charAt(pos);
				if (s.charAt(pos) == '(') balance++;
				if (s.charAt(pos) == ')') balance--;
				pos++;
			}
			if (exp.isEmpty()) {
				alpha = null;
				break;
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
//			System.err.println(i);
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
				List<Integer> mp = modusPonens(i, exp, exprs);
				if (mp != null) {
					Expression gj;
					if (exprs.get(mp.get(0)).equalTree(new Implication(exprs.get(mp.get(1)), exp))) {
						gj = exprs.get(mp.get(1));
					} else {
						gj = exprs.get(mp.get(0));
					}
					//(A -> gj) -> ((A -> (gj -> gi)) -> (A -> gi))
					Expression tmp = new Implication(new Implication(alpha, new Implication(gj, exp)), new Implication(alpha, exp));
					statements.add((new Implication(new Implication(alpha, gj), tmp)));
					statements.add(tmp);
					statements.add(new Implication(alpha, exp));
				} else {
					int forall = forAllRule(exp, exprs);
					if (forall != -1) {
						Implication exp2 = (Implication)exp;
						Quant q = (Quant)exp2.rightArg();
						Expression a = alpha;
						Expression c = q.getExpr();
						Expression b = exp2.leftArg();
						Variable d = q.getVar();
						try {
							BufferedReader in = new BufferedReader(new FileReader("deduction/forall.txt"));
							String tmp = "";
							while ((tmp = in.readLine()) != null) {
								tmp = tmp.replaceAll("A", "QQ99");
								tmp = tmp.replaceAll("B", "WW88");
								tmp = tmp.replaceAll("C", "XX77");
								tmp = tmp.replaceAll("d", "ZZ66");
								tmp = tmp.replaceAll("QQ99", "(" + a.printExp() + ")");
								tmp = tmp.replaceAll("WW88", "(" + b.printExp() + ")");
								tmp = tmp.replaceAll("XX77", "(" + c.printExp() + ")");
								tmp = tmp.replaceAll("ZZ66", d.printExp());
								statements.add(ExpressionParser.parse(tmp));
							}
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						int exists = existsRule(exp, exprs);
						if (exists != -1) {
							Implication exp2 = (Implication)exp;
							Quant q = (Quant)exp2.leftArg();
							Expression a = alpha;
							Expression b = q.getExpr();
							Expression c = exp2.rightArg();
							Variable d = q.getVar();
							try {
								BufferedReader in = new BufferedReader(new FileReader("deduction/exists.txt"));
								String tmp = "";
								while (in.ready()) {
									tmp = in.readLine();
									tmp = tmp.replaceAll("A", "QQ99");
									tmp = tmp.replaceAll("B", "WW88");
									tmp = tmp.replaceAll("C", "XX77");
									tmp = tmp.replaceAll("d", "ZZ66");
									tmp = tmp.replaceAll("QQ99", "(" + a.printExp() + ")");
									tmp = tmp.replaceAll("WW88", "(" + b.printExp() + ")");
									tmp = tmp.replaceAll("XX77", "(" + c.printExp() + ")");
									tmp = tmp.replaceAll("ZZ66", d.printExp());
									statements.add(ExpressionParser.parse(tmp));
								}
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
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
		}
	}


	public Deduction(String s, List<Expression> pr) {
		hyParser(s);
		exprs.addAll(pr);
	}

	public Expression getAlpha() {
		return alpha;
	}

	public List<Expression> getHyp() {
		return hypothesis;
	}
	public List<Expression> getDeduction() {
		makeDeduction();
		return statements;
	}

}
