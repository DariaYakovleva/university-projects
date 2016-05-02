import java.util.*;

public class Variable implements Expression {

	String c;

	public Variable(String a) {
		this.c = a;
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Variable))
			return false;
		Variable d = (Variable) b;
		return c.equals(d.c);
	}

	public boolean calculate(List<String> var, int mask) {
		return (mask & (1 << (var.size() - var.indexOf(c) - 1))) != 0;
	}

	public String printExp() {
		return c;
	}

	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		// for (char i:list.keySet())
		if (!list.containsKey(c)) {
			list.put(c, b);
			return true;
		}
		return b.equalTree(list.get(c));
	}

	public List<Expression> getProof(List<String> var, int mask) {
		List<Expression> proof = new ArrayList<Expression>();
		if (calculate(var, mask)) {

		} else {

		}
		return proof;
	}

	public Tree buildTree(boolean want, Tree tree, Set<Expression> dontWant) {
		if (want) {
			for (Expression v : dontWant) {
				if (v.almostEqualTree(this, new HashMap<String, Expression>()))
					return null;
			}
			tree.addVar(c);
		} else {
			if (tree.variables.contains(c)) {
				return null;
			}
		}
		return tree;
	}

	public boolean checkTree(Tree tree) {
		return tree.variables.contains(c);
	}
}