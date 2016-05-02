package homework4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakeExpr {
	public static final String[] AXIOMS = { "A->(B->A)",
		"(A->B)->((A->B->C)->(A->C))", "A&B->A", "A&B->B", "A->(B->(A&B))",
		"A->(A|B)", "B->(A|B)", "(A->C)->((B->C)->((A|B)->C))",
		"(A->B)->((A->!B)->!A)", "!!A ->A", "@xF(x)->F(a), F(a)->?xF(x)",
		"a=b->a'=b'", "a=b->a=c->b=c", "a'=b'->a=b", "!a'=0", "a+b'=(a+b)'", "a+0=a",
		"a*0=0", "a*b'=a*b+a" };
	//, "F(x=0)&@x(F->F(x=x'))->F"
	public List<Expression> statements = new ArrayList<>();
	public String curError = "";
	Expression alpha1;

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
		List<Expression> ded1 = (new Deduction(state1, cont)).getDeduction();
		String state2 = (new Implication(a, b)).printExp() + " |- " +
				(new Implication(new Not(b), new Not(a))).printExp();
		List<Expression> ded2 = (new Deduction(state2, ded1)).getDeduction();
		ded2.add(new Implication(new Not(b), new Not(a)));
		return ded2;
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
		// F(0)&@x(F(x)->F(x'))->F(x)
		if (a instanceof Implication) {
			Expression left = ((Implication) a).e1;
			Expression F = ((Implication) a).e2;
			if (left instanceof And) {
				Expression right = ((And) left).e2;
				if (right instanceof Quant) {
					Variable x = (Variable)(((Quant) right).x);
					Expression axiom9 = new Implication(new And(F.replaceVar(x, new Const(0)),
							new Quant(Quant.FORALL, x, new Implication(F, F.replaceVar(x, new Const(x))))), F);
					if (a.equalTree(axiom9)) return 99;
				}
			}
		}
		//@xF(x)->F(x = y)
		if (a instanceof Implication) {
			Implication b = (Implication)a;
			if (b.leftArg() instanceof Quant) {
				Quant q = (Quant)b.leftArg();
				if (q.getQuant() == Quant.FORALL) {
					Expression arg2 = b.rightArg();
					Variable var = q.getVar(); 
					if (alpha1 != null && alpha1.freeEntry(var)) {
						if (curError.isEmpty()) curError = "используется правило с квантором по переменной " + var.printExp() +
								" входящей свободно в допущение " + alpha1.printExp();
					} else {
						Expression arg1 = q.getExpr();
						Expression arg12 = arg1.replaceVar(var, new Variable("QQ99"));
						if (almostEqualT(arg12, arg2)) {
							Map<String, Expression> comp = arg12.getVariables(arg2);
							boolean ok = true;
							Set<String> keys = comp.keySet();
							for (String key: keys) {
								if (!key.equals("QQ99")) {
									ok &= key.equals(comp.get(key).printExp());
								}
							}
							if (ok) {
								Expression replace = comp.get("QQ99");
								if (replace == null) return 10;
								Set<String> vars = replace.getVariables(replace).keySet(); 
								if (!arg12.haveBoundEntry(new Variable("QQ99"), vars, new ArrayList<>())) {
									Expression arg13 = arg12.replaceVar(new Variable("QQ99"), replace);
									if (equalT(arg13, arg2)) return 10;
								} else {
									if (curError.isEmpty()) curError = "терм " + replace.printExp() +
											" не свободен для подстановки в формулу " + arg1.printExp() +
											" вместо переменной " + var.printExp();
								}
							}
						}
					}
				}
			}
			//F(x = y)->?xF(x)
			if (b.rightArg() instanceof Quant) {
				Quant q = (Quant)b.rightArg();
				if (q.getQuant() == Quant.EXISTS) {
					Expression arg2 = b.leftArg();
					Variable var = q.getVar(); 
					if (alpha1 != null && alpha1.freeEntry(var)) {
						if (curError.isEmpty()) curError = "используется правило с квантором по переменной " + var.printExp() +
								" входящей свободно в допущение " + alpha1.printExp();
					} else {
						Expression arg1 = q.getExpr();
						Expression arg12 = arg1.replaceVar(var, new Variable("QQ99"));
						if (almostEqualT(arg12, arg2)) {
							Map<String, Expression> comp = arg12.getVariables(arg2);
							boolean ok = true;
							Set<String> keys = comp.keySet();
							for (String key: keys) {
								if (!key.equals("QQ99")) {
									ok &= key.equals(comp.get(key).printExp());
								}
							}
							if (ok) {
								Expression replace = comp.get("QQ99");
								if (replace == null) return 11;
								Set<String> vars = replace.getVariables(replace).keySet();
								if (!arg12.haveBoundEntry(new Variable("QQ99"), vars, new ArrayList<>())) {
									Expression arg13 = arg12.replaceVar(new Variable("QQ99"), replace);
									if (equalT(arg13, arg2)) return 11;
								} else {
									if (curError.isEmpty()) curError = "терм " + replace.printExp() +
											" не свободен для подстановки в формулу " + arg1.printExp() +
											" вместо переменной " + var.printExp();
								}
							}
						}
					}
				}
			}

		}
		return -1;
	}

	public List<Integer> modusPonens(int pos, Expression a, List<Expression> expr) {
		if (a == null)
			return null;
		for (int i = 0; i < pos; i++) {
			Expression cur = expr.get(i);
			if (cur instanceof Implication) {
				if (equalT(((Implication) cur).rightArg(), a)) {
					for (int j = 0; j < pos; j++) {
						if (equalT(((Implication) cur).leftArg(), expr.get(j))) {
							List<Integer> pair = new ArrayList<>();
							pair.add(j);
							pair.add(i);
							return pair;
						}
					}
				}
			}
		}
		return null;
	}

	public int forAllRule(Expression a, List<Expression> expr) {
		if (a == null)
			return -1;
		if (!(a instanceof Implication))
			return -1;
		Implication st = (Implication)a;
		if (!(st.rightArg() instanceof Quant))
			return -1;
		if (!(((Quant)st.rightArg()).getQuant() == Quant.FORALL)) 
			return -1;
		if (alpha1 != null && alpha1.freeEntry(((Quant)st.rightArg()).getVar())) {
			if (curError.isEmpty()) curError = "используется правило с квантором по переменной " + ((Quant)st.rightArg()).getVar().printExp() +
					" входящей свободно в допущение " + alpha1.printExp();
			return -1;
		}
		Expression rightArg = ((Quant)st.rightArg()).getExpr();
		if (st.leftArg().freeEntry(((Quant)st.rightArg()).getVar())) {
			if (curError.isEmpty()) curError = "переменная " + ((Quant)st.rightArg()).getVar().printExp() + " входит свободно в формулу " + st.leftArg().printExp();
			return -1;
		}
		Expression findExp = new Implication(st.leftArg(), rightArg);
		for (int i = 0; i < expr.size(); i++) {
			Expression cur = expr.get(i);
			if (equalT(findExp, cur)) {
				return i;
			}
		}
		return -1;
	}

	public int existsRule(Expression a, List<Expression> expr) {
		if (a == null)
			return -1;
		if (!(a instanceof Implication))
			return -1;
		Implication st = (Implication)a;
		if (!(st.leftArg() instanceof Quant))
			return -1;
		if (!(((Quant)st.leftArg()).getQuant() == Quant.EXISTS)) 
			return -1;
		if (alpha1 != null && alpha1.freeEntry(((Quant)st.leftArg()).getVar())) {
			if (curError.isEmpty()) curError = "используется правило с квантором по переменной " + ((Quant)st.leftArg()).getVar().printExp() +
					" входящей свободно в допущение " + alpha1.printExp();
			return -1;
		}
		Expression leftArg = ((Quant)st.leftArg()).getExpr();
		if (st.rightArg().freeEntry(((Quant)st.leftArg()).getVar())) {
			if (curError.isEmpty()) curError = "переменная " + ((Quant)st.leftArg()).getVar().printExp() + " входит свободно в формулу " + st.rightArg().printExp();
			return -1;
		}
		Expression findExp = new Implication(leftArg, st.rightArg());
		for (int i = 0; i < expr.size(); i++) {
			Expression cur = expr.get(i);
			if (equalT(findExp, cur)) {
				return i;
			}
		}
		return -1;
	}

	public String printExp(Expression a) {
		if (a == null)
			return null;
		return a.printExp();
	}
}
