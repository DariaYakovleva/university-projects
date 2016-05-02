import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
}