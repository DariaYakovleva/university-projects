import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Daria on 26.01.2016.
 */
public class SetEquations {

    static List<Expression> res = new ArrayList();

    public static boolean findSolution(List<Expression> eqs) {
        Stack<Expression> current = new Stack<>();
        for (Expression ex: eqs) {
            current.push(ex);
        }
        while (!current.empty()) {
            Equation cur = (Equation)current.pop();
            if (((cur).getLeft() instanceof Function) && ((cur).getRight() instanceof Variable)) {
                current.push(new Equation(cur.getRight(), cur.getLeft()));
            } else if (((cur).getLeft() instanceof Variable) && ((cur).getRight() instanceof Variable)) {
                if (cur.getLeft().isEqual(cur.getRight())) {
                    continue;
                } else {
                    for (int i = 0; i < current.size(); i++) {
                        current.set(i, current.get(i).substitution(new ArrayList<Long>(), cur.getLeft(), cur.getRight()));
                    }
                    for (int i = 0; i < res.size(); i++) {
                        res.set(i, res.get(i).substitution(new ArrayList<Long>(), cur.getLeft(), cur.getRight()));
                    }
                    res.add(cur);
                }
            } else if (((cur).getLeft() instanceof Function) && ((cur).getRight() instanceof Function)) {
                Function a = (Function)cur.getLeft();
                Function b = (Function)cur.getRight();
                if (a.name.compareTo(b.name) != 0) {
                    System.err.println("!!! " + a.printExp() + " = " + b.printExp());
                    return false;
                }
                for (int i = 0; i < a.getSize(); i++) {
                    current.push(new Equation(a.vars.get(i), b.vars.get(i)));
                }
            } else { //variable = function
                Variable a = (Variable)cur.getLeft();
                Function b = (Function)cur.getRight();
                if (b.getVar(a)) {
                    System.err.println("2!!! " + a.printExp() + " = " + b.printExp());
                    return false;
                }
                res.add(cur);
                for (int i = 0; i < current.size(); i++) {
                    current.set(i, current.get(i).substitution(new ArrayList<Long>(), a, b));
                }
            }
        }
        return true;
    }

    SetEquations(List<Expression> a) {
        if (!findSolution(a)) res.clear();
    }

    public static List<Expression> getSolution(List<Expression> a) {
        return (new SetEquations(a)).res;
    }

}
