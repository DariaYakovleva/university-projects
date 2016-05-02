import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Not implements Expression {
  
	Expression e1;
    public Not(Expression e1) {
    	this.e1 = e1;
    }
    
    public String printExp() {
		return "!" + "(" + e1.printExp() + ")";
	}
    
    public boolean calculate(List<String> var, int mask) {
    	return !e1.calculate(var, mask);
    }

    public List<Expression> getProof(List<String> var, int mask) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader("axioms/not.txt"));
		String ss = "";
		List<Expression> proof = new ArrayList<Expression>();
		proof.addAll(e1.getProof(var, mask));
		if (!calculate(var, mask)) {
			ss = "11";
		} else {
			ss = "00";
		}
		String tmp = "";
		while (!tmp.equals(ss)) {
			tmp = in.readLine();
		}
		tmp = in.readLine();
		while (!tmp.equals(ss)) {
			tmp = tmp.replaceAll("A", "QQ99");
			tmp = tmp.replaceAll("QQ99", "(" + e1.printExp() + ")");
			proof.add(ExpressionParser.parse(tmp));
			tmp = in.readLine();
		}
		in.close();
		return proof;
	}
    
    public boolean equalTree(Expression b) {
    	if (!(b instanceof Not))
    		return false;
    	Not c = (Not)b;
        return e1.equalTree(c.e1);
    } 
    public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
    	if (!(b instanceof Not))
    		return false;
    	Not c = (Not)b;
        return e1.almostEqualTree(c.e1, list);
    }

	public Tree buildTree(boolean want, Tree tree, Set<Expression> dontWant) {
		if (want) {
			boolean d = true;
			for (Expression v: dontWant) {
				if (v.almostEqualTree(this, new HashMap<String, Expression>()))
					return null;
				if (v.almostEqualTree(e1, new HashMap<String, Expression>()))
					d = false;
			}
			dontWant.add(e1);
			if (e1.buildTree(false, tree, dontWant) == null) return null;
			if (d) dontWant.remove(e1);
		} else {
			Tree child = new Tree(new HashSet<Tree>(), tree.variables);
			tree.addChild(child);
			if (e1.buildTree(true, child, dontWant) == null) return null;
		}
		return tree;
	}

	private boolean dfs(Tree tree, Expression a) {
		if (a.checkTree(tree)) {
			return true;
		}
		for (Tree child: tree.children) {
			if (dfs(child, a)) return true;
		}
		return false;
	}

	public boolean checkTree(Tree tree) {
		return !dfs(tree, e1);
	}

}