package homework7;

import java.util.ArrayList;


public class Summand implements Expression {
	Expression e1;
	Expression e2;
	public Summand(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Polinom evaluate() {
		Polinom a = e1.evaluate();
		Polinom b = e2.evaluate();
		if (a.isAtom() && b.isAtom()) {
			return new Polinom(new ArrayList<Mpair>(), new Const(a.p.x + b.p.x));
		}
		int exp = a.getExp().compare(b.getExp());
		if (exp < 0) return b;
		Polinom p = new Polinom();
		if (exp == 0) {
			p.push(a.getExp(), new Const(a.getCnst().x + b.getCnst().x));
			return p.add(b.rest());
		}
		p.push(a.getExp(), a.getCnst());
		return p.add((new Summand(a.rest(), b)).evaluate());
	}

	public int compare(Expression b) {
		Polinom a = evaluate();
		Polinom bb = b.evaluate();
		return a.compare(bb);
	}
	
	public String printExp() {
		return "(" + e1.printExp() + "+" + e2.printExp() + ")";
	}

}
