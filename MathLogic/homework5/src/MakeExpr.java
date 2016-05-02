import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.util.Pair;

public class MakeExpr {
	public static final String[] AXIOMS = { "A->(B->A)",
			"(A->B)->((A->B->C)->(A->C))", "A&B->A", "A&B->B", "A->(B->(A&B))",
			"A->(A|B)", "B->(A|B)", "(A->C)->((B->C)->((A|B)->C))",
			"(A->B)->((A->!B)->!A)", "!!A->A", };
	
	public List<Expression> statements = new ArrayList<>();

	void init() {
		for (int i = 0; i < AXIOMS.length; i++) {
			statements.add(ExpressionParser.parse(AXIOMS[i]));
		}
	}
	
	
	public static List<Expression> makeContraposition(Expression a, Expression b) {
		List<Expression> cont = new ArrayList<>();
		cont.add(new Implication(new Implication(a, b), new Implication(new Implication(a, new Not(b)), new Not(a))));
		cont.add(new Implication(new Implication(a, new Not(b)), new Not(a)));
		cont.add(new Implication(new Not(b), new Implication(a, new Not(b))));
		cont.add(new Implication(a, new Not(b)));
		cont.add(new Not(a));
		String state1 = (new Implication(a, b)).printExp() + ", " + (new Not(b)).printExp() + " |- " +
		(new Not(a)).printExp();
		List<Expression> ded1 = (new Deduction(state1, cont)).getStatements();
		String state2 = (new Implication(a, b)).printExp() + " |- " +
				(new Implication(new Not(b), new Not(a))).printExp();
		List<Expression> ded2 = (new Deduction(state2, ded1)).getStatements();
		ded2.add(new Implication(new Not(b), new Not(a)));
		return ded2;
	}

	
	public static List<Expression> merging(Expression alpha, Expression gamma) {
		List<Expression> res = new ArrayList<>();
		res.add(new Implication(gamma, new Or(gamma, new Not(gamma))));
		res.addAll(makeContraposition(gamma, new Or(gamma, new Not(gamma))));
		res.add(new Implication(new Not(gamma), new Or(gamma, new Not(gamma))));
		res.addAll(makeContraposition(new Not(gamma), new Or(gamma, new Not(gamma))));
		res.add(new Implication(new Implication(new Not(new Or(gamma, new Not(gamma))), new Not(gamma)),
				new Implication(new Implication(new Not(new Or(gamma, new Not(gamma))), new Not(new Not(gamma))),
						new Not(new Not(new Or(gamma, new Not(gamma)))))));
		res.add(new Implication(new Implication(new Not(new Or(gamma, new Not(gamma))), new Not(new Not(gamma))),
				new Not(new Not(new Or(gamma, new Not(gamma))))));
		res.add(new Not(new Not(new Or(gamma, new Not(gamma)))));
		res.add(new Implication(new Not(new Not(new Or(gamma, new Not(gamma)))), new Or(gamma, new Not(gamma))));
		res.add(new Or(gamma, new Not(gamma)));
		res.add(new Implication(new Implication(gamma, alpha), new Implication(new Implication(new Not(gamma), alpha),
				new Implication(new Or(gamma, new Not(gamma)), alpha))));
		res.add(new Implication(new Implication(new Not(gamma), alpha),
				new Implication(new Or(gamma, new Not(gamma)), alpha)));
		res.add(new Implication(new Or(gamma, new Not(gamma)), alpha));
		res.add(alpha);
		return res;
	}
	
	public static List<Expression> aToNNA(Expression a) {
		List<Expression> res = new ArrayList<>();
		Expression nna = new Not(new Not(a));
		res.add(new Implication(nna, a));
		res.addAll(MakeExpr.makeContraposition(nna, a));
		res.add(new Implication(a, new Implication(new Not(a), a)));
		res.add(new Implication(new Not(a), a));
		res.addAll(MakeExpr.makeContraposition(new Not(a), a));
		res.add(new Implication(new Implication(new Not(a), nna),
				new Implication(new Implication(new Not(a), new Not(nna)), nna)));
		res.add(new Implication(new Implication(new Not(a), new Not(nna)), nna));
		res.add(nna);
		return res;
	}
	
	public static List<Expression> aToA(Expression a) {
		List<Expression> res = new ArrayList<>();
		res.add(new Implication(a, new Implication(a, a)));
		res.add(new Implication(new Implication(a, new Implication(a, a)), 
				new Implication(new Implication(a, new Implication(new Implication(a, a), a)), new Implication(a, a))));
		res.add(new Implication(new Implication(a, new Implication(new Implication(a, a), a)), new Implication(a, a)));
		res.add(new Implication(a, new Implication(new Implication(a, a), a)));
		res.add(new Implication(a, a));
		return res;
	}
	
	public static boolean equalT(Expression a, Expression b) {
		if (a == null || b == null)
			return false;
		return a.equalTree(b);
	}

	public boolean almostEqualT(Expression a, Expression b) {
		if (a == null || b == null)
			return false;
		Map<String, Expression> list = new HashMap<>();
		return a.almostEqualTree(b, list);
	}

	public int compWithAx(Expression a) {
		for (int i = 0; i < AXIOMS.length; i++) {
			if (almostEqualT(ExpressionParser.parse(AXIOMS[i]), a)) {
				return i;
			}
		}
		return -1;
	}
	public int compWithState(Expression a) {
		if (a == null)
			return -1;
		for (int i = 0; i < statements.size(); i++) {
			if (almostEqualT(statements.get(i), a)) {
				return i;
			}
		}
		return -1;
	}

	public static Pair<Integer, Integer> modusPonens(Expression a, List<Expression> expr) {
		if (a == null)
			return null;
		for (int i = 0; i < expr.size(); i++) {
			Expression cur = expr.get(i);
			if (cur instanceof Implication) {
				if (equalT(((Implication) cur).rightArg(), a)) {
					for (int j = 0; j < expr.size(); j++) {
						if (equalT(((Implication) cur).leftArg(), expr.get(j))) {
							return new Pair<Integer, Integer>(j, i);
						}
					}
				}
			}
		}
		return null;
	}
	
	public String printExp(Expression a) {
		if (a == null)
			return null;
		return a.printExp();
	}
}
