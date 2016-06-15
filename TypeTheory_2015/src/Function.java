import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Daria on 25.01.2016.
 */
public class Function implements Expression {
    String name;
    List<Expression> vars;
    public Function(String e1, List<Expression> e2) {
        this.name = e1;
        this.vars = new ArrayList(e2);
    }

    public String printExp() {
        String res = name + "(";
        for (Expression x: vars) {
            res += x.printExp() + ", ";
        }
        res = res.substring(0, res.length() - 2);
        res += ")";
        return res;
    }

    public String printExp2() {
        if (vars.size() == 1) {
            return vars.get(0).printExp2();
        }
        String res = "(" + vars.get(0).printExp2() + "->" + vars.get(1).printExp2() + ")";
        for (int i = 2; i < vars.size(); i++) {
            res = "(" + res + " -> " + vars.get(i).printExp2() + ")";
        }
        return res;
    }

    public List<String> freeVariables(List<String> cur) {
        List<String> res = new ArrayList();
        for (Expression x: vars) {
            res.addAll(x.freeVariables(cur));
        }
        return res;
    }

    public Expression substitution(List<Long> booked, Expression var, Expression sub) {
        ArrayList<Expression> nvars = new ArrayList();
        for (Expression x: vars) {
            nvars.add(x.substitution(booked, var, sub));
        }
        return new Function(name, nvars);
    }

    public Expression substitution2(List<Long> booked, Expression var, Expression sub) {
        ArrayList<Expression> nvars = new ArrayList();
        for (Expression x: vars) {
            nvars.add(x.substitution2(booked, var, sub));
        }
        vars = nvars;
        return this;
    }

    public int getSize() {
        return vars.size();
    }

    public boolean getVar(Variable v) {
        for (Expression ex: vars) {
            if (ex instanceof Variable) {
                Variable cur = (Variable)ex;
                if (cur.c.compareTo(v.c) == 0) return true;
            } else {
                Function cur = (Function)ex;
                if (cur.getVar(v)) return true;
            }
        }
        return false;
    }

    public void getSetEquations(Map<Expression, Expression> types, List<Expression> equations, List<Integer> cnt) {
        boolean have = false;
        for (Expression exp: types.keySet()) {
            if (this.isEqual(exp)) return;
        }
        types.put(this.createCopy(), new Variable("t" + Integer.toString(cnt.get(0))));
        cnt.set(0, cnt.get(0) + 1);
    }

    public boolean isEqual(Expression b) {
        if (!(b instanceof Function)) {
            return false;
        }
        if (((Function) b).name.compareTo(name) != 0) return false;
        List<Expression> vvv = ((Function) b).vars;
        for (int i = 0; i < vvv.size(); i++) {
            if (!vvv.get(i).isEqual(vars.get(i))) return false;
        }
        return true;
    }

    public Expression replace(Expression a, Expression b) {
        if (this.isEqual(a)) {
            return b.createCopy();
        }
        List<Expression> newVars = new ArrayList<>();
        for (Expression vv: vars) {
            newVars.add(vv.replace(a, b));
        }
        return new Function(name, newVars);
    }

    public Expression createCopy() {
        return new Function(name, new ArrayList(vars));
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

