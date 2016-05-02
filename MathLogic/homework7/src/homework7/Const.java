package homework7;

import java.util.ArrayList;

public class Const implements Expression {
	Integer x;
	public Const(Integer e1) {
		this.x = e1;
	}
	
	public Polinom evaluate() {
		Polinom a = new Polinom(new ArrayList<Mpair>(), new Const(x));
		return a;
	}

	public int compare(Expression b) {
		if (!(b instanceof Const))
			return 2;
		Const c = (Const)b;
		return x.compareTo(c.x);
	}
	
	public String printExp() {
		return x.toString();
	}
	
	public Integer getValue() {
		return x;
	}
}
