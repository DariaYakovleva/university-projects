import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Or implements Expression {
  
	Expression e1;
	Expression e2;
    public Or(Expression e1, Expression e2) {
    	this.e1 = e1;
    	this.e2 = e2;
    }
    
    public String printExp() {
		return "(" + e1.printExp() + ") | (" + e2.printExp() + ")";
	}
    
    public boolean calculate(List<String> var, int mask) {
    	return e1.calculate(var, mask) | e2.calculate(var, mask);
    }
    
    public List<Expression> getProof(List<String> var, int mask) throws IOException {
    	BufferedReader in = new BufferedReader(new FileReader("axioms/or.txt"));
		String ss = "";
		List<Expression> proof = new ArrayList<Expression>();
		proof.addAll(e1.getProof(var, mask));
		proof.addAll(e2.getProof(var, mask));
		if (!calculate(var, mask)) {
			ss = "000";
		} else {
			if (!e1.calculate(var, mask)) {
				ss = "011";
			} else {
				ss = "101";
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
    	if (!(b instanceof Or))
    		return false;
    	Or c = (Or)b;
        return e1.equalTree(c.e1) && e2.equalTree(c.e2);
    } 
    public boolean almostEqualTree(Expression b, Map<String, Expression> list) {
    	if (!(b instanceof Or))
    		return false;
    	Or c = (Or)b;
        return e1.almostEqualTree(c.e1, list) && e2.almostEqualTree(c.e2, list);
    }

	public Tree buildTree(boolean want, Tree tree, Set<Expression> dontWant) {
//		Tree child = tree.addChild();
		if (want) {
			for (Expression v: dontWant) {
				if (v.almostEqualTree(this, new HashMap<String, Expression>()))
					return null;
			}
			if (e1.buildTree(true, tree, dontWant) == null &&
					e2.buildTree(true, tree, dontWant) == null) return null;
		} else {
			if (e1.buildTree(false, tree, dontWant) == null ||
					e2.buildTree(false, tree, dontWant) == null) return null;
		}
		return tree;
	}
	public boolean checkTree(Tree tree) {
		return e1.checkTree(tree) || e2.checkTree(tree);
	}
}