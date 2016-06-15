import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Lambda extends LambdaTerm {
	Expression e1;
	Expression e2;
	long id;
	long len;
    public Lambda(Expression e1, Expression e2) {
    	this.e1 = e1;
    	this.e2 = e2;
		init();
		//		System.err.println("lam id = " + id + " " + printExp());
	}

	void init() {
		len = e1.getLen() + e2.getLen() + 4;
		id = (id + getHash("(\\") + e1.getId() * power(2)) % MOD;
		id = (id + getHash(".") * power((int)e1.getLen() + 2)) % MOD;
		id = (id + e2.getId() * power((int)e1.getLen() + 3)) % MOD;
		id = (id + getHash(")") * power((int)(e1.getLen() + e2.getLen()) + 3)) % MOD;
	}
    public String printExp() {
		return "(\\" + e1.printExp() + "." + e2.printExp() + ")";
	} 
    public List<String> freeVariables(List<String> cur) {
//    	List<String> res = e1.freeVariables(cur);
    	List<String> res = new ArrayList<>();
    	List<String> curr = new ArrayList<String>(cur);
    	curr.add(e1.printExp());
    	res.addAll(e2.freeVariables(curr));
    	return res;
    }
	public String printExp2() {
		return printExp();
	}
    public Expression substitution(List<Long> booked, Expression var, Expression sub) {
    	List<Long> cur = new ArrayList<Long>(booked);
    	cur.add(e1.getId());
    	return new Lambda(e1.createCopy(), e2.substitution(cur, var, sub));
    }

	public Expression substitution2(List<Long> booked, Expression var, Expression sub) {
		List<Long> cur = new ArrayList<Long>(booked);
		cur.add(e1.getId());
		e2 = e2.substitution2(cur, var, sub);
		init();
		return this;
	}

	public void getSetEquations(Map<Expression, Expression> types, List<Expression> equations, List<Integer> cnt) {
		Map<Expression, Expression> types2 = new HashMap<>(types);
		e1.getSetEquations(types2, equations, cnt);
		e2.getSetEquations(types2, equations, cnt);
		Expression type_e1 = null;
		Expression type_e2 = null;
		for (Expression exp: types2.keySet()) {
			if (this.isEqual(exp)) return;
			if (e1.isEqual(exp)) type_e1 = types2.get(exp).createCopy();
			if (e2.isEqual(exp)) type_e2 = types2.get(exp).createCopy();
		}
		if (type_e1 == null || type_e2 == null) {
			System.err.println("Application e1 or e2 is equal to null");
			return;
		}
		List<Expression> lst = new ArrayList<>();
		lst.add(type_e1);
		lst.add(type_e2);
		types.put(this.createCopy(), new Function("f2", lst));
	}
	public boolean isEqual(Expression b) {
		if (b instanceof Lambda) {
			if (((Lambda) b).e1.isEqual(e1) && ((Lambda) b).e2.isEqual(e2)) return true;
		}
		return false;
	}

	public Expression replace(Expression a, Expression b) {
		if (this.isEqual(a)) {
			return b.createCopy();
		}
		return this.createCopy();
	}

	public Expression createCopy() {
		return new Lambda(e1.createCopy(), e2.createCopy());
	}

	public Expression getNormalForm(Map<Long, Expression> headNormals) {
//		System.err.println("lambda norm");
//		String printStr = this.printExp();
		Expression a = e1;
		Expression b = e2;
		while (!b.isNormalForm()) {
			b = b.getNormalForm(headNormals);
		}
		Expression res = new Lambda(a, b);
		return res;
	}

	public Expression getHeadNormalForm(Map<Long, Expression> headNormals) {
//		System.err.println("lambda head");
		return this;
	}

	public boolean isNormalForm() {
		return e2.isNormalForm();
	}
	public long getId() {
		return id;
	}
	public long getLen() {
		return len;
	}
}