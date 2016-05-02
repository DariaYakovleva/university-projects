package homework4;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Expression {     
    public boolean equalTree(Expression b); 
    public boolean almostEqualTree(Expression b, Map<String, Expression> list); 
    public String printExp();    
    public boolean freeEntry(Variable xx); // есть ли свободные вхождения xx
    public Expression replaceVar(Variable from, Expression to); //замена всех свободных вхождений
    public Map<String, Expression> getVariables(Expression b); //полный список переменных и их замен в b
    public boolean haveBoundEntry(Variable xx, Set<String> vars, List<String> quants); //входят ли переменные хх свободно при подстановке
}
