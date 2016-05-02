import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Daria on 04.10.2015.
 */
public class Tree {
    Set<Tree> children;
    Set<String> variables;

    public Tree() {
        children = new HashSet<Tree>();
        variables = new HashSet<String>();
    }

    public Tree(Set<Tree> a, Set<String> b) {
        children = new HashSet<Tree>(a);
        variables = new HashSet<String>(b);
    }

    public void addChild(Tree x) {
        children.add(x);
    }
    public void addVar(String x) {
        variables.add(x);
    }

    public int size() {
        return children.size();
    }

    public String printTree(int num) {
        String res = num + "( ";
        for (String v: variables) {
            res += v + " ";
        }
        res += ")\n";
        String spaces = "";
        int tmp = num;
        while (tmp != 0) {
            spaces += "    ";
            tmp /= 10;
        }
        if (!children.isEmpty()) {
            int num2 = 1;
            for (Tree t : children) {
                res += spaces + t.printTree(num * 10 + num2);
                num2++;
            }
        }
        return res;
    }
}
