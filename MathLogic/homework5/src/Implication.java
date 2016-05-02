import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Implication implements Expression {

	Expression e1;
	Expression e2;
	public Implication(Expression e1, Expression e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public Expression leftArg() {
		return e1;
	}
	
	public Expression rightArg() {
		return e2;
	}
	
	public String printExp() {
		return "(" + e1.printExp() + ") -> (" + e2.printExp() + ")";
	}

	public boolean calculate(List<String> var, int mask) {
		return (!e1.calculate(var, mask)) | e2.calculate(var, mask);
	}

	public List<Expression> getProof(List<String> var, int mask) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("axioms/implication.txt"));
		String ss = "";
		List<Expression> proof = new ArrayList<Expression>();
		proof.addAll(e1.getProof(var, mask));
		proof.addAll(e2.getProof(var, mask));
		if (!calculate(var, mask)) {
			ss = "100";
		} else {
			if (!e2.calculate(var, mask)) {
				ss = "001";
			} else {
				ss = "011";
			}
		}
		String tmp = "";
		while (!tmp.equals(ss)) {
			tmp = in.readLine();
		}
		tmp = in.readLine();
		while (!tmp.equals(ss)) {
			tmp = tmp.replaceAll("A", "QQ99");
			tmp = tmp.replaceAll("B", "WW88");
			tmp = tmp.replaceAll("QQ99", "(" + e1.printExp() + ")");
			tmp = tmp.replaceAll("WW88", "(" + e2.printExp() + ")");
			proof.add(ExpressionParser.parse(tmp));
			tmp = in.readLine();
		}
		in.close();
		return proof;
	}

	public boolean equalTree(Expression b) {
		if (!(b instanceof Implication))
			return false;
		Implication c = (Implication)b;
		return e1.equalTree(c.e1) && e2.equalTree(c.e2);
	}
	public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
		if (!(b instanceof Implication))
			return false;
		Implication c = (Implication)b;
		return e1.almostEqualTree(c.e1, list) && e2.almostEqualTree(c.e2, list);
	}

	public Tree buildTree(boolean want, Tree tree, Set<Expression> dontWant) {
		Tree child = new Tree(new HashSet<Tree>(), tree.variables);
		if (want) {
			for (Expression v: dontWant) {
				if (v.almostEqualTree(this, new HashMap<String, Expression>()))
					return null;
			}
			if (e1.buildTree(true, child, dontWant) != null) {
				if (!dfs2(child, dontWant)) return null;
			} else {
				boolean d = true;
				for (Expression v: dontWant) {
					if (v.almostEqualTree(e1, new HashMap<String, Expression>()))
						d = false;
				}
				dontWant.add(e1);
				child = new Tree(new HashSet<Tree>(), tree.variables);
				if (e1.buildTree(false, child, dontWant) == null) {
					return null;
				}
				if (d) dontWant.remove(e1);
			}
		} else {
			if (e1.buildTree(true, child, dontWant) == null) {
				return null;
			} else {
				if (!dfs3(child, dontWant)) return null;
			}
		}
		tree.addChild(child);
		return tree;
	}

	private boolean dfs(Tree tree, Expression a, Expression b) {
		if (a.checkTree(tree)) {
			if (!b.checkTree(tree)) return false;
		}
		for (Tree child: tree.children) {
			if (!dfs(child, a, b)) return false;
		}
		return true;
	}

	private boolean dfs2(Tree tree, Set<Expression> dontWant) {
		Set<Tree> ch = new HashSet<Tree>(tree.children);
		if (e1.checkTree(tree)) {
			if (e2.buildTree(true, tree, dontWant) == null) return false;
		}
		for (Tree child: ch) {
			if (!dfs2(child, dontWant)) return false;
		}
		return true;
	}

	private boolean dfs3(Tree tree, Set<Expression> dontWant) {
		Set<Tree> ch = new HashSet<Tree>(tree.children);
		if (e1.checkTree(tree)) {
			if (e2.buildTree(false, tree, dontWant) != null) return true;
		}
		for (Tree child: ch) {
			if (dfs3(child, dontWant)) return true;
		}
		return false;
	}


	public boolean checkTree(Tree tree) {
		return dfs(tree, e1, e2);
	}
}