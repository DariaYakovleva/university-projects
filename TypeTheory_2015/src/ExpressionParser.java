import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 25.01.2016.
 */
public class ExpressionParser {
    private final char EQ = '=';
    private final char OPEN = '(';
    private final char QUOTE = '\'';
    private final char COMMA = ',';
    private String lexem;
    private int nextterm;
    Expression res;
    private List<String> variables = new ArrayList<String>();


    private Expression variable() {
        Expression a;
        String val = "";
        while ((lexem.charAt(nextterm) >= 'a' && lexem.charAt(nextterm) <= 'z') ||
                (lexem.charAt(nextterm) >= '0' && lexem.charAt(nextterm) <= '9')
                || (lexem.charAt(nextterm) == QUOTE)){
            val += lexem.charAt(nextterm);
            nextterm++;
        }
        a = new Variable(val);
        if (!variables.contains(val)) variables.add(val);
        return a;
    }

    private Expression function() {
        Expression a;
        String name = "";
        while ((lexem.charAt(nextterm) >= 'a' && lexem.charAt(nextterm) <= 'z') ||
                (lexem.charAt(nextterm) >= '0' && lexem.charAt(nextterm) <= '9')
                || (lexem.charAt(nextterm) == QUOTE)){
            name += lexem.charAt(nextterm);
            nextterm++;
        }
        if (lexem.charAt(nextterm) != OPEN) {
            System.err.println("No open bracket after function");
        }
        nextterm++;
        List<Expression> vars = new ArrayList();
        vars.add(term());
        while (lexem.charAt(nextterm) == COMMA) {
            nextterm++;
            vars.add(term());
        }
        nextterm++;
    return new Function(name, vars);
}


    private Expression term() {
        if (lexem.charAt(nextterm) >= 'a' && lexem.charAt(nextterm) <= 'h') {
            return function();
        } else if (lexem.charAt(nextterm) >= 'i' && lexem.charAt(nextterm) <= 'z'){
            return variable();
        } else {
            System.err.println("MDA not found term " + lexem.charAt(nextterm));
            return null;
        }
    }

    private Expression expr() {
        Expression a = term();
        if (lexem.charAt(nextterm) != EQ)
            System.err.println("MDA not found =");
        nextterm++;
        Expression b = term();
        return new Equation(a, b);
    }

    ExpressionParser(String a) {
        nextterm = 0;
        lexem = a.replaceAll("\\s", "").concat("$");
        res = expr();
    }

    public List<String> getVariables() {
        return variables;
    }

    public static Expression parse(String a) {
        return (new ExpressionParser(a)).res;
    }

}
