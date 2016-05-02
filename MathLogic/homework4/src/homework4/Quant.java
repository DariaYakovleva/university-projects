package homework4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Quant implements Expression {
	public static final char FORALL = '@';
	public static final char EXISTS = '?';
	char quant;
	Expression x;
	Expression e1;
	public Quant(char quant, Expression x, Expression e1) {
		this.quant = quant;
		this.x = x;
		this.e1 = e1;
	}

	public String printExp() {
//		return "(" + quant + x.printExp() + e1.printExp() + ")";
		return "(" + quant + x.printExp() + "(" + e1.printExp() + "))";
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Quant))
			return false;
		Quant c = (Quant)b;
		return quant == c.quant && x.equalTree(c.x) && e1.equalTree(c.e1);
	} 
	
	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		if (!(b instanceof Quant))
			return false;
		Quant c = (Quant)b;
		return quant == c.quant && x.almostEqualTree(c.x, list) && e1.almostEqualTree(c.e1, list);
	} 
	
	public boolean freeEntry(Variable xx) {
		Variable cur = (Variable) x;
		if (cur.c.equals(xx.c)) {
			return false;
		} else {
			return e1.freeEntry(xx);
		}
	}
	
	public Expression getExpr() {
		return e1;
	}
	public char getQuant() {
		return quant;
	}
	public Variable getVar() {
		return (Variable)x;
	}
	
	public Expression replaceVar(Variable from, Expression to) {
		Variable cur = (Variable) x;
		if (!cur.c.equals(from.c)) {
			return new Quant(quant, x, e1.replaceVar(from, to));
		}
		return new Quant(quant, x, e1);
	}
	
	
	public Map<String, Expression> getVariables(Expression b) {
		Map<String, Expression> res = new HashMap<>();
		if (!(b instanceof Quant))
			return res;
		Quant c = (Quant)b;
		res.putAll(x.getVariables(c.x));
		res.putAll(e1.getVariables(c.e1));
		return res;
	}
	
	public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants) {
		quants.add(x.printExp());
		boolean tmp = e1.haveBoundEntry(xx, vars, quants);
		quants.remove(x.printExp());
		return tmp;
	}
	

}
