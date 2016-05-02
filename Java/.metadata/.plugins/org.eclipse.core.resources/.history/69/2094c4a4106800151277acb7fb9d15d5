package homework7;

public class W implements Expression {
	String w = "w";
	public W() {
	}
	
	public Polinom evaluate() {
		Polinom a = new Polinom();
		a.push(new Const(1), new Const(1));
		return a;
	}

	public int compare(Expression b) {
		if (b instanceof W) {
			return 0;
		}
		return -1;
	}
	
	public String printExp() {
		return w;
	}

}
