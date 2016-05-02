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
	private final String POW = "^";
	private final String LOG = "$";

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
						if (s1.charAt(i) == 'l') {
							s.add("$");
							i += 1;
						} else {
							s.add("" + s1.charAt(i));
						}
					}
				}
			}
			i++;
		}
		s.add(".");
	}

	private Expression3 multiplier() throws ParseException {

		Expression3 a;
		if (ok(s.get(nextperm))) {
			long tmp = Long.parseLong(s.get(nextperm));
			if ((tmp > Integer.MAX_VALUE) || (tmp < Integer.MIN_VALUE)) {
				throw new ParseException("too long number");
			}
			a = new Const((int) tmp);
			nextperm++;
			if (ok(s.get(nextperm))) {
				throw new ParseException("incorrect number");
			}
		} else {
			if ((s.get(nextperm).equals("x")) || (s.get(nextperm).equals("y"))
					|| (s.get(nextperm).equals("z"))) {
				a = new Variable(s.get(nextperm));
				nextperm++;
			} else {
				if (s.get(nextperm).equals(OPEN)) {
					nextperm++;
					a = expr();
					if (!s.get(nextperm).equals(")")) {
						throw new ParseException("missing right bracket");
					}
					nextperm++;
				} else {
					if (s.get(nextperm).equals(LOG)) {
						nextperm++;
						a = new Log(expr());
					} else {
						if (s.get(nextperm).equals(SUB)) {
							nextperm++;
							a = new Minus(poww());
						} else {
							if (s.get(nextperm).equals(TIL)) {
								nextperm++;
								a = new Not(poww());
							} else {
								if (s.get(nextperm).equals(EXC)) {
									nextperm++;
									a = new Abs(poww());
								} else {
									throw new ParseException("incorrect multiply");
								}
							}
						}
					}
				}
			}
		}
		return a;
	}

	private Expression3 poww() throws ParseException {
		Expression3 a = multiplier();
		if (s.get(nextperm).equals(POW)) {
			nextperm++;
			a = new Power(a, poww());
		}
		return a;
	}

	private Expression3 summand() throws ParseException {
		Expression3 a = poww();
		while ((s.get(nextperm).equals(MUL))
				|| (s.get(nextperm).equals(DIV) || (s.get(nextperm).equals(POW)))) {
			if (s.get(nextperm).equals(MUL)) {
				nextperm++;
				a = new Multiply(a, poww());
			} else if (s.get(nextperm).equals(DIV)) {
				nextperm++;
				a = new Divide(a, poww());
			} else {
				nextperm++;
				a = new Power(a, poww());
			}
		}
		return a;
	}

	private Expression3 expr() throws ParseException {
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

	private ExpressionParser(String a) throws ParseException {
		i = 0;
		nextperm = 0;
		lexem(a);
		res = expr();
		if (nextperm + 1 != s.size()) {
			throw new ParseException("extra characters at the end of the line");
		}
	}

	public static Expression3 parse(String a) throws ParseException {
		return new ExpressionParser(a).res;
	}
}
