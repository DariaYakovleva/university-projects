
public class ExpressionParser {
	
	static int N = 100010;
	static private char plu = '+', minu = '-', open = '(', clos = ')', mul = '*', div = '/';
	static private String[] s = new String[N];
	static private int n = 0, i = 0, nextperm = 0;
	static private boolean good = true;
	static Expression3 res;
	

	private static boolean ok(String s) {
		 try {
	            Long.parseLong(s);
	     } catch (Exception e) {
	            return false;
	       }
	     return true;
	}
	
	private static void lexem(String s1) {
		i = 0;
		n = 0;
		nextperm = 0;
		while (i < s1.length()) {
			boolean f = false;
			while ((i < s1.length()) && (Character.isWhitespace(s1.charAt(i)))) {
				i++;
			}
			if (i == s1.length()) {
				return;
			}
			String a = "";
			while ((i < s1.length()) && (Character.isDigit(s1.charAt(i)))) {
				a += s1.charAt(i);
				i++;
				f = true;
			}
			if (f) {
				s[n] = a;
				i--;
			}
			else {

			if (s1.charAt(i) == plu) {
				s[n] = "" + plu;
			}
			if (s1.charAt(i) == minu) {
				s[n] = "" + minu;
			}
			if (s1.charAt(i) == open) {
				s[n] = "" + open;
			}
			if (s1.charAt(i) == clos) {
				s[n] = "" + clos;
			}
			if (s1.charAt(i) == mul) {
				s[n] = "" + mul;
			}
			if (s1.charAt(i) == div) {
				s[n] = "" + div;
			}
			if ((s1.charAt(i) == 'x') || (s1.charAt(i) == 'y') || (s1.charAt(i) == 'z')) {
				s[n] = "" + s1.charAt(i);
			}
			}
			n++;
			i++;
		}
		s[n] = ".";
		n++;
	}


	private static Expression3 mnoj () {
	//	assert !good;
		Expression3 a;
		if (ok(s[nextperm])) {
			a = new Const((int)(Long.parseLong(s[nextperm])));
			nextperm++;
		}
		else {
			if ((s[nextperm].equals("x")) || (s[nextperm].equals("y")) || (s[nextperm].equals("z"))) {
				a = new Variable(s[nextperm]);
				nextperm++;
			}
			else {
				if (s[nextperm].charAt(0) == open)	{
					nextperm++;
					a = expr();
					if (s[nextperm].charAt(0) != clos) good = false;
					nextperm++;
				}
				else {
					if (s[nextperm].charAt(0) == minu) {
						nextperm++;
						a = new Subtract(new Const(0), mnoj());
					}
					else {
						good = false;
						a = null;
					}
				}
			}
		}
		return a;
	}

	private static Expression3 slag() {
//		assert !good;
		Expression3 a = mnoj();
		while (s[nextperm].charAt(0) == mul)
		{
			nextperm++;
			a = new Multiply(a, mnoj());
		}
		while (s[nextperm].charAt(0) == div)
		{
			nextperm++;
			a = new Divide(a, mnoj());
		}
		return a;
	}
	
	private static Expression3 expr() {
		Expression3 a = slag();
		while ((s[nextperm].charAt(0) == plu) || (s[nextperm].charAt(0) == minu))
		{
			if (s[nextperm].charAt(0) == plu)
			{
				nextperm++;
				a = new Add(a, slag());
			}
			else
			{
				nextperm++;
				a = new Subtract(a, slag());
			}
		}
		return a;
	}
	
	public static Expression3 parse (String a) {
		lexem(a);
		res = expr();
		return res;
	}

	
}
