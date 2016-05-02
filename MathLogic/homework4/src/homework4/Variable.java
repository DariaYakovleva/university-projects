package homework4;
import java.util.*;

public class Variable implements Expression {

	String c;

	public Variable(String a) {
		this.c = a;
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Variable))
			return false;
		Variable d = (Variable) b;
		return c.equals(d.c);
	}
	
	public String printExp() {
		return c;
	}

	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		if (!list.containsKey(c)) {
			list.put(c, b);
			return true;
		}
		return b.equalTree(list.get(c));
	}
	
	public boolean freeEntry(Variable xx) {
		if (c.equals(xx.c))
			return true;
		return false;
	}
	
	public Expression replaceVar(Variable from, Expression to) {
		if (c.equals(from.c)) {
			return to;
		}
		return new Variable(c);
	}
	
	public Map<String, Expression> getVariables(Expression b) {
		Map<String, Expression> res = new HashMap<>();
		res.put(c, b);
		return res;
	}
	
	public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants) {
		if (!c.equals(xx.c)) return false;
		for (int i = 0; i < quants.size(); i++) {
			if (vars.contains(quants.get(i))) return true;
		}
		return false;
	}
}