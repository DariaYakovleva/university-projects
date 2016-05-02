import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {

	private final String PLUS = "+";
	private final String SUB = "-";
	private final String OPEN = "(";
	private final String MUL = "*";
	private final String DIV = "/";
	private final String EXC = "!";
	private final String TIL = "~";

	private List<String> s = new ArrayList<String>();
	private int i = 0;
	private int nextperm = 0;
	Expression3 res;

	private boolean ok(String s) {
		try {
			Long.parseLong(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void lexem(String s1) {
		while (i < s1.length()) {
			boolean f = false;
			while ((i < s1.length()) && (Character.isWhitespace(s1.charAt(i)))) {
				i++;
			}
			if (i == s1.length()) {
				s.add(".");
				return;
			}
			String a = "";
			while ((i < s1.length()) && (Character.isDigit(s1.charAt(i)))) {
				a += s1.charAt(i);
				i++;
				f = true;
			}
			if (f) {
				s.add(a);
				i--;
			} else {

				if ((s1.charAt(i) == 'x') || (s1.charAt(i) == 'y')
						|| (s1.charAt(i) == 'z')) {
					s.add("" + s1.charAt(i));
				} else {
					if (s1.charAt(i) == 'a') {
						s.add("!");
						i += 2;
					} else {
						s.add("" + s1.charAt(i));
					}
				}
			}
			i++;
		}
		s.add(".");
	}

	private Expression3 multiplier() {
		// assert !good;
		Expression3 a;
		if (ok(s.get(nextperm))) {
			a = new Const((int) (Long.parseLong(s.get(nextperm))));
			nextperm++;
		} else {
			if ((s.get(nextperm).equals("x")) || (s.get(nextperm).equals("y"))
					|| (s.get(nextperm).equals("z"))) {
				a = new Variable(s.get(nextperm));
				nextperm++;
			} else {
				if (s.get(nextperm).equals(OPEN)) {
					nextperm++;
					a = expr();
					nextperm++;
				} else {
					if (s.get(nextperm).equals(SUB)) {
						nextperm++;
						a = new Minus(multiplier());
					} else {
						if (s.get(nextperm).equals(TIL)) {
							nextperm++;
							a = new Not(multiplier());
						} else {
							if (s.get(nextperm).equals(EXC)) {
								nextperm++;
								a = new Abs(multiplier());
							} else {
								a = null;
							}
						}
					}
				}
			}
		}
		return a;
	}

	private Expression3 summand() {
		// assert !good;
		Expression3 a = multiplier();
		while ((s.get(nextperm).equals(MUL)) || (s.get(nextperm).equals(DIV))) {
			if (s.get(nextperm).equals(MUL)) {
				nextperm++;
				a = new Multiply(a, multiplier());
			} else {
				nextperm++;
				a = new Divide(a, multiplier());
			}
		}
		return a;
	}

	private Expression3 expr() {
		Expression3 a = summand();
		while ((s.get(nextperm).equals(PLUS)) || (s.get(nextperm).equals(SUB))) {
			if (s.get(nextperm).equals(PLUS)) {
				nextperm++;
				a = new Add(a, summand());
			} else {
				nextperm++;
				a = new Subtract(a, summand());
			}
		}
		return a;
	}

	private ExpressionParser(String a) {
		i = 0;
		nextperm = 0;
		lexem(a);
		res = expr();
	}

	public static Expression3 parse(String a) {
		return new ExpressionParser(a).res;
	}

}
