import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ExpressionParser<T extends MyNumber<T>> {
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
	Expression3<T> res;
	T tmp;
	private boolean ok(String s) {
		try {
			Long.parseLong(s);
		} catch (Exception e) {
			try {
				Double.parseDouble(s);
			} catch (Exception ee) {
				try {
					new BigInteger(s);
				} catch (Exception eee) {
					return false;
				}
			}
			return true;
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
				s.add("@");
				return;
			}
			String a = "";
			while ((i < s1.length()) &&
					((Character.isDigit(s1.charAt(i)) || (s1.charAt(i) == '.') 
							|| (s1.charAt(i) == 'E') || (s1.charAt(i) == '-')))) {
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
							if (s1.charAt(i) == 'f') {
								s.add("f");
								i += 2;
							}
							s.add("" + s1.charAt(i));
						}
					}
				}
			}
			i++;
		}
		s.add("@");
	}

	private Expression3<T> multiplier() throws ParseException {

		Expression3<T> a;
		if (ok(s.get(nextperm))) {
			if (tmp instanceof MyInteger) {
				a = new Const(new MyInteger(Integer.parseInt(s.get(nextperm))));
			} else
			if (tmp instanceof MyDouble) {
				a = new Const(new MyDouble(Double.parseDouble(s.get(nextperm))));
			} else {
				a = new Const(new MyBigInteger(new BigInteger(s.get(nextperm))));
			}
			nextperm++;
			if (ok(s.get(nextperm))) {
				throw new ParseException("incorrect number");
			}
		} else {
			if ((s.get(nextperm).equals("x")) || (s.get(nextperm).equals("y"))
					|| (s.get(nextperm).equals("z"))) {
				a = new Variable<T>(s.get(nextperm));
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
						a = new Log<T>(expr());
					} else {
						if (s.get(nextperm).equals(SUB)) {
							nextperm++;
							a = new Minus<T>(poww());
						} else {
							if (s.get(nextperm).equals(TIL)) {
								nextperm++;
								a = new Not<T>(poww());
							} else {
								if (s.get(nextperm).equals(EXC)) {
									nextperm++;
									a = new Abs<T>(poww());
								} else {
									if ((s.get(nextperm).equals("f"))) {
										//make_new_function
										//begin

										class Factorial<T extends MyNumber<T>> implements Expression3<T> {
										    private Expression3<T> e1;

										    public Factorial(Expression3<T> e1) {
										        this.e1 = e1;
										    } 
										    Function<MyInteger> F = new Function<MyInteger>() {
										    	public MyInteger function(MyInteger c) {
										    		int res = 1;
										    		for (int i = 1; i < c.a; i++) {
										    			res *= i;
										    		}
										    		return new MyInteger(res);
										    		
										    	};
										    };
										    MyInteger c = new MyInteger(new MyInteger(2), F);
										   
										   /* 
												MyBigInteger BigInt2  = new MyBigInteger(BigInteger.ZERO) {
										    	protected MyBigInteger factorial() {
										    		BigInteger res = BigInteger.valueOf(1);
										    		for (BigInteger i = BigInteger.valueOf(1);
										    				i.equals(a); i.add(BigInteger.valueOf(1))) {
										    			res.multiply(i);
										    		}
										    		return new MyBigInteger(res);
										    	}
										    };*/
										    public T evaluate(T x, T y, T z) throws Exception3 {
										    	return e1.evaluate(x, y, z).function();
										    }
										             
										}

										a = new Factorial<T>(poww());
										
									} else {
										throw new ParseException("incorrect multiply");
									}
								}
							}
						}
					}
				}
			}
		}
		return a;
	}

	private Expression3<T> poww() throws ParseException {
		Expression3<T> a = multiplier();
		if (s.get(nextperm).equals(POW)) {
			nextperm++;
			a = new Power<T>(a, poww());
		}
		return a;
	}

	private Expression3<T> summand() throws ParseException {
		Expression3<T> a = poww();
		while ((s.get(nextperm).equals(MUL))
				|| (s.get(nextperm).equals(DIV) || (s.get(nextperm).equals(POW)))) {
			if (s.get(nextperm).equals(MUL)) {
				nextperm++;
				a = new Multiply<T>(a, poww());
			} else if (s.get(nextperm).equals(DIV)) {
				nextperm++;
				a = new Divide<T>(a, poww());
			} else {
				nextperm++;
				a = new Power<T>(a, poww());
			}
		}
		return a;
	}

	private Expression3<T> expr() throws ParseException {
		Expression3<T> a = summand();
		while ((s.get(nextperm).equals(PLUS)) || (s.get(nextperm).equals(SUB))) {
			if (s.get(nextperm).equals(PLUS)) {
				nextperm++;
				a = new Add<T>(a, summand());
			} else {
				nextperm++;
				a = new Subtract<T>(a, summand());
			}
		}
		return a;
	}

	private ExpressionParser(String a, T t) throws ParseException {
		i = 0;
		tmp = t;
		nextperm = 0;
		lexem(a);
		res = expr();
		if (nextperm + 1 != s.size()) {
			throw new ParseException("extra characters at the end of the line");
		}
	}

	static public <E extends MyNumber<E>> Expression3<E> parse(String a, E t) throws ParseException {
		return new ExpressionParser<E>(a, t).res;
	}
}
