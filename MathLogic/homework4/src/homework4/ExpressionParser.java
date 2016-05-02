package homework4;
import java.util.ArrayList;
import java.util.List;


public class ExpressionParser {

	private final char IMP = '-';
	private final char OPEN = '(';
	private final char OR = '|';
	private final char AND = '&';
	private final char NOT = '!';
	private final char PLUS = '+';
	private final char MUL = '*';
	private final char FORALL = '@';
	private final char EXISTS = '?';

	private String lexem;
	int nextperm;
	Expression res;
	private List<String> variables = new ArrayList<String>();

	private Expression multiplier() {
		Expression a;
		String val = "";
		if (lexem.charAt(nextperm) >= 'a' && lexem.charAt(nextperm) <= 'z') {
			val += lexem.charAt(nextperm);
			nextperm++;
			while (lexem.charAt(nextperm) >= '0' && lexem.charAt(nextperm) <= '9') {
				val += lexem.charAt(nextperm);
				nextperm++;
			}
			if (lexem.charAt(nextperm) == OPEN) {
				List<Expression> terms = new ArrayList<>();
				nextperm++;
				terms.add(term());
				while (lexem.charAt(nextperm) == ',') {
					nextperm++;
					terms.add(term());
				}
				nextperm++;
				a = new Function(val, terms);
			} else {
				a = new Variable(val);
				if (!variables.contains(val)) variables.add(val);
			}
		} else if (lexem.charAt(nextperm) == OPEN) {
			nextperm++;
			a = term();
			nextperm++;
		} else  if (lexem.charAt(nextperm) == '0') {
			a = new Const(0);
			nextperm++;
		} else {
			a = null;
		}
		while (lexem.charAt(nextperm) == '\'') {
			a = new Const(a);
			//a'
			nextperm++;
		}
		return a;
	}

	private Expression summand() {
		Expression a = multiplier();
		while (lexem.charAt(nextperm) == MUL) {
			nextperm++;
			a = new Multiplier(a, multiplier());
		}
		return a;
	}

	private Expression term() {
		Expression a = summand();
		while (lexem.charAt(nextperm) == PLUS) {
			nextperm++;
			a = new Summand(a, summand());
		}
		return a;
	}

	private Expression predicate() {
		Expression a;
		String val = "";
		List<Expression> terms = new ArrayList<>();
		if (lexem.charAt(nextperm) >= 'A' && lexem.charAt(nextperm) <= 'Z') {
			val += lexem.charAt(nextperm);
			nextperm++;
			while (lexem.charAt(nextperm) >= '0' && lexem.charAt(nextperm) <= '9') {
				val += lexem.charAt(nextperm);
				nextperm++;
			}
			if (lexem.charAt(nextperm) == OPEN) {
				nextperm++;
				terms.add(term());
				while (lexem.charAt(nextperm) == ',') {
					nextperm++;
					terms.add(term());
				}
				nextperm++;
			}
		} else {
			val = "=";
			terms.add(term());
			if (lexem.charAt(nextperm) != '=') {
				return null;
			}
			nextperm++;
			terms.add(term());
		}
		a = new Predicate(val, terms);
		return a;
	}

	private Expression variable() {
		Expression a;
		String val = "";
		val += lexem.charAt(nextperm);
		nextperm++;
		while (lexem.charAt(nextperm) >= '0' && lexem.charAt(nextperm) <= '9') {
			val += lexem.charAt(nextperm);
			nextperm++;
		}
		a = new Variable(val);
		if (!variables.contains(val)) variables.add(val);
		return a;
	}

	private Expression negation() {
		Expression a;
		if (lexem.charAt(nextperm) == OPEN) {
			int step = nextperm;
			a = predicate();
			if (a == null) {
				nextperm = step;
				nextperm++;
				a = expr();
				nextperm++;
			}
		} else if (lexem.charAt(nextperm) == NOT) {
			nextperm++;
			a = new Not(negation());
		} else if (lexem.charAt(nextperm) == FORALL || lexem.charAt(nextperm) == EXISTS) {
			char quant = lexem.charAt(nextperm);
			nextperm++;
			a = new Quant(quant, variable(), negation());
		} else {
			a = predicate();
		}
		return a;
	}

	private Expression conjunction() {
		Expression a = negation();
		while (lexem.charAt(nextperm) == AND) {
			nextperm++;
			a = new And(a, negation());
		}
		return a;
	}

	private Expression disjunction() {
		Expression a = conjunction();
		while (lexem.charAt(nextperm) == OR) {
			nextperm++;
			a = new Or(a, conjunction());
		}
		return a;
	}

	private Expression expr() {
		Expression a = disjunction();
		while (lexem.charAt(nextperm) == IMP) {
			nextperm += 2;
			a = new Implication(a, expr());
		}
		return a;
	}

	ExpressionParser(String a) {
		nextperm = 0;
		lexem = a.replaceAll("\\s", "").concat(".");
		res = expr();
	}

	public List<String> getVariables() {
		return variables;
	}

	public static Expression parse(String a) {
		return (new ExpressionParser(a)).res;
	}

}
