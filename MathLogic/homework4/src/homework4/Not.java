package homework4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Not implements Expression {
  
	Expression e1;
    public Not(Expression e1) {
    	this.e1 = e1;
    }
    
    public String printExp() {
		return "!" + "(" + e1.printExp() + ")";
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
    public boolean freeEntry(Variable xx) {
		return e1.freeEntry(xx);
	}
    
    public Expression replaceVar(Variable from, Expression to) {
		return new Not(e1.replaceVar(from, to));
	}
	public Map<String, Expression> getVariables(Expression b) {
		Map<String, Expression> res = new HashMap<>();
		if (!(b instanceof Not))
			return res;
		Not c = (Not)b;
		res.putAll(e1.getVariables(c.e1));
		return res;
	}
	
	public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants) {
		return e1.haveBoundEntry(xx, vars, quants);
	}
}