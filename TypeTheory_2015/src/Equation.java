import java.util.List;
import java.util.Map;

/**
 * Created by Daria on 25.01.2016.
 */
public class Equation implements Expression {

    Expression e1;
    Expression e2;
    public Equation(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String printExp() {
        return e1.printExp() + " = " + e2.printExp();
    }
    public String printExp2() {
        return printExp();
    }

    public List<String> freeVariables(List<String> cur) {
        List<String> res = e1.freeVariables(cur);
        res.addAll(e2.freeVariables(cur));
        return res;
    }

    public Expression substitution(List<Long> booked, Expression var, Expression sub) {
        return new Equation(e1.substitution(booked, var, sub), e2.substitution(booked, var, sub));
    }

    public Expression substitution2(List<Long> booked, Expression var, Expression sub) {
        e1 = e1.substitution2(booked, var, sub);
        e2 = e2.substitution2(booked, var, sub);
        return this;
    }
    public Expression getLeft() {
        return e1;
    }
    public Expression getRight() {
        return e2;
    }
    public void getSetEquations(Map<Expression, Expression> types, List<Expression> equations, List<Integer> cnt) {
        e1.getSetEquations(types, equations, cnt); //?? do I need that?
        e2.getSetEquations(types, equations, cnt);
    }
    public boolean isEqual(Expression b) {
        if (b instanceof Equation) {
            if (((Equation) b).e1.isEqual(e1) && ((Equation) b).e2.isEqual(e2)) return true;
        }
        return false;
    }

    public Expression replace(Expression a, Expression b) {
        if (this.isEqual(a)) {
            return b.createCopy();
        }
        return this.createCopy();
    }

    public Expression createCopy() {
        return new Equation(e1.createCopy(), e2.createCopy());
    }

    public Expression getNormalForm(Map<Long, Expression> headNormals) {
        return this.createCopy();
    }
    public Expression getHeadNormalForm(Map<Long, Expression> headNormals) {
        return this.createCopy();
    }
    public boolean isNormalForm() {
        return true;
    }
    public long getId() {
        return -1;
    }
    public long getLen() {
        return -1;
    }
}
