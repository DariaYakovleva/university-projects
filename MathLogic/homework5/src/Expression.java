
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Expression {      
    public boolean equalTree(Expression b); 
    public boolean almostEqualTree(Expression b, Map<String, Expression> list); 
    public String printExp();
    public boolean calculate(List<String> var, int mask);
    public List<Expression> getProof(List<String> var, int mask) throws IOException;
    public Tree buildTree(boolean want, Tree tree, Set<Expression> dontWant);
    public boolean checkTree(Tree tree);
}
