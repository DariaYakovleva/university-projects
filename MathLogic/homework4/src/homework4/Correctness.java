package homework4;


import java.util.ArrayList;
import java.util.List;


public class Correctness extends MakeExpr{
	public Correctness(List<Expression> s, List<Expression> hyp, Expression alpha) {
		statements = new ArrayList<Expression>(hyp);
		if (alpha != null) {
			statements.add(alpha);
		}
		statements.addAll(s);
		this.alpha1 = alpha;
		this.hyp = new ArrayList<Expression>(hyp);
		if (alpha != null) {
			this.hyp.add(alpha);
		}
	}
	List<String> errors = new ArrayList<>();
	List<String> proof = new ArrayList<>();
	List<Expression> hyp;
	
	int compWithHyp(Expression exp) {
		for (int i = 0; i < hyp.size(); i++) {
			if (equalT(hyp.get(i), exp)) return i;
		}
		return -1;
	}
	
	boolean goCheck() {
		for (int i = 0; i < statements.size(); i++) {
			Expression exp = statements.get(i);
//			System.out.println("i="+i+" " + exp.printExp());
			int ax = compWithAx(exp);
			int hx = compWithHyp(exp);
			if (ax != -1 || hx != -1) {
				if (ax != -1) {
					proof.add("(" + (i + 1) + ") " + exp.printExp() + " (Сх.акс. " + (ax + 1) + ")");
				} else {
					proof.add("(" + (i + 1) + ") " + exp.printExp() + " (hyp)");
				}
			} else {
				List<Integer> mp = modusPonens(i, exp, statements);
				if (mp != null) {
					proof.add("(" + (i + 1) + ") " + exp.printExp() + " (M.P. "
							+ (mp.get(0) + 1) + ", " + (mp.get(1) + 1) + ")");
				} else {
					int fa = forAllRule(exp, statements);
					if (fa != -1) {
						proof.add("(" + (i + 1) + ") " + exp.printExp() + " (FORALL " + (fa + 1) + ")");
					} else {
						int ex = existsRule(exp, statements);
						if (ex != -1) {
							proof.add("(" + (i + 1) + ") " + exp.printExp() + " (EXISTS " + (ex + 1) + ")");
						} else {
							statements.set(i, null);
							proof.add("(" + (i + 1) + ") " + exp.printExp() + " Не доказано");
							errors.add("Вывод некорректен начиная с формулы номер " + (i + 1));
							if (!curError.isEmpty()) {
								errors.add(": " + curError);
								curError = "";
							}
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public List<String> getStatements() {
		return proof;
	}
	
	public boolean isCorrect() {
		return goCheck();
	}
	
	public List<String> getErrors() {
		return errors;
	}
}
