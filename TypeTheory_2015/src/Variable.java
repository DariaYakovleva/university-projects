import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class Variable extends LambdaTerm {
	String c;
	long id;
	long len;
	public Variable(String a) {
		this.c = a;
		id = getHash(a);
		len = a.length();
	}


	public Expression createCopy() {
		return new Variable(c);
	}
	public String printExp() {
		return c;
	}
	public String printExp2() {
		return printExp();
	}

	public List<String> freeVariables(List<String> cur) {
    	List<String> res = new ArrayList<String>();
    	boolean have = false;
    	for (String v : cur) {
    		if (c.equals(v)) {
    			have = true;
    			break;
    		}
    	}
    	if (!have) res.add(c);
    	return res;
    }
	
	public Expression substitution(List<Long> booked, Expression var, Expression sub) {
		if (id != var.getId()) return new Variable(c);
    	boolean have = false;
    	for (Long v: booked) {
    		if (v == var.getId()) {
    			have = true;
    			break;
    		}
    	}
    	if (have) {
    		return new Variable(c);
    	}
    	return sub.createCopy();
    }

	public Expression substitution2(List<Long> booked, Expression var, Expression sub) {
		if (id != var.getId()) return this;
		boolean have = false;
		for (Long v: booked) {
			if (v == var.getId()) {
				have = true;
				break;
			}
		}
		if (have) {
			return this;
		}
		return sub;
	}

	public void getSetEquations(Map<Expression, Expression> types, List<Expression> equations, List<Integer> cnt) {
		for (Expression exp: types.keySet()) {
			if (this.isEqual(exp)) return;
		}
		types.put(this.createCopy(), new Variable("t" + Integer.toString(cnt.get(0))));
		cnt.set(0, cnt.get(0) + 1);
	}
	public boolean isEqual(Expression b) {
		if (b instanceof Variable) {
			if (((Variable) b).c.compareTo(c) == 0) return true;
		}
		return false;
	}
	public Expression replace(Expression a, Expression b) {
		if (this.isEqual(a)) {
			return b.createCopy();
		}
		return this.createCopy();
	}

	public Expression getNormalForm(Map<Long, Expression> headNormals) {
		return this;
	}
	public Expression getHeadNormalForm(Map<Long, Expression> headNormals) {
		return this;
	}

	public boolean isNormalForm() {
		return true;
	}
	public long getId() {
		return id;
	}
	public long getLen() {
		return len;
	}
}