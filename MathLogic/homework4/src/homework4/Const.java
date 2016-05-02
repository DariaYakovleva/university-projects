package homework4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Const implements Expression {
	Expression e1;
	boolean zero;
	public Const(int a) {
		if (a == 0) {
			zero = true;
			e1 = null;
		} else {
			System.out.println("problem Const");
		}
	}
	
	public Const(Expression e1) {
		this.e1 = e1;
		zero = false;
	}

	public String printExp() {
		if (zero) {
			return "0";
		}
		return e1.printExp() + "'";
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Const))
			return false;
		Const c = (Const)b;
		if (zero != c.zero) return false;
		if (zero) return true;
		return e1.equalTree(c.e1);
	} 
	
	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		if (!(b instanceof Const))
			return false;
		Const c = (Const)b;
		if (zero != c.zero) return false;
		if (zero) return true;
		return e1.almostEqualTree(c.e1, list);
	} 
	
	public boolean freeEntry(Variable xx) {
		if (zero) return false;
		return e1.freeEntry(xx);
	}
	public Expression replaceVar(Variable from, Expression to) {
		if (zero) return new Const(0);
		return new Const(e1.replaceVar(from, to));
	}
	public Map<String, Expression> getVariables(Expression b) {
		Map<String, Expression> res = new HashMap<>();
		if (!(b instanceof Const))
			return res;
		Const c = (Const)b;
		if (zero || c.zero) return res;
		res.putAll(e1.getVariables(c.e1));
		return res;
	}
	
	public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants) {
		if (zero) return false;
		return e1.haveBoundEntry(xx, vars, quants);
	}
}
