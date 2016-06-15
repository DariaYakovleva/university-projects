import java.util.List;
import java.util.Map;

public interface Expression {
    public Expression createCopy();
    public String printExp();
    public String printExp2();
    public List<String> freeVariables(List<String> cur);
    public Expression substitution(List<Long> booked, Expression var, Expression sub); //replace with copy
    public Expression substitution2(List<Long> booked, Expression var, Expression sub); //replace without copy
    public void getSetEquations(Map<Expression, Expression> types, List<Expression> equations, List<Integer> cnt);
    public boolean isEqual(Expression b);
    public Expression replace(Expression a, Expression b);
    public Expression getNormalForm(Map<Long, Expression> headNormals);
    public Expression getHeadNormalForm(Map<Long, Expression> headNormals);
    public boolean isNormalForm();
    long getId();
    long getLen();
}

