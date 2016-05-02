package homework7;

public class Mpair {
	public Const a;
	public Polinom b;
	Mpair(Const a, Polinom b) {
		this.a = a;
		this.b = b;
	}
	public Mpair(Mpair p) {
		a = p.a;
		b = p.b;
	}
	public Const getCnst() {
		return a;
	}
	public Polinom getExp() {
		return b;
	}
	public Const second() {
		return a;
	}
	public Polinom first() {
		return b;
	}
}
