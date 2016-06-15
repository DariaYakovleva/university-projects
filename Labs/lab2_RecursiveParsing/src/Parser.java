import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Daria on 30.03.2016.
 */

class Tree {
    String node;
    List<Tree> children;
    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }
    public Tree(String node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    public String print(String pref) {
        String res = node;
        String add;
        for (int i = 0; i < children.size(); i++) {
            if (i + 1 < children.size()) {
                add = "|    ";
            } else {
                add = "     ";
            }
            Tree ch = children.get(i);
            if (i == 0) {
                res += " -> " + ch.print(pref + add);
            } else {
                res += pref + "| -> " + ch.print(pref + add);
            }
            if (i + 1 < children.size()) res += "\n";
        }
        return res;
    }
}
public class Parser {
    LexicalAnalyzer lex;

    Tree parse(String input) throws ParseException {
        lex = new LexicalAnalyzer(input);
        lex.nextToken();
        return S();
    }

    Tree S() throws ParseException {
        switch (lex.curToken()) {
            case NAME:
                //D
                Tree sub = D();
                return new Tree("S", sub);
            case END:
                //eps
                return new Tree("S", new Tree("$"));
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at S ");
        }
    }
    Tree D() throws ParseException {
        switch (lex.curToken()) {
            case NAME:
                //T
                Tree sub = T();
                //A
                Tree cont = A();
                //D
                Tree cont2 = D();
                return new Tree("D", sub, cont, cont2);
            case END:
                //eps
                return new Tree("D", new Tree("$"));
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at D ");
        }
    }
    Tree T() throws ParseException {
        switch (lex.curToken()) {
            case NAME:
                lex.nextToken();
                return new Tree("T", new Tree("a"));
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at S ");
        }
    }
    Tree A() throws ParseException {
        switch (lex.curToken()) {
            case STAR:
                Tree cont = B();
                return new Tree("A", cont);
            case NAME:
                cont = B();
                return new Tree("A", cont);
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at A ");
        }
    }
    Tree B() throws ParseException {
        switch (lex.curToken()) {
            case STAR:
                Tree sub = V();
                Tree cont = C();
                return new Tree("B", sub, cont);
            case NAME:
                sub = V();
                cont = C();
                return new Tree("B", sub, cont);
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at B ");
        }
    }
    Tree V() throws ParseException {
        switch (lex.curToken()) {
            case STAR:
                lex.nextToken();
                Tree cont = V();
                return new Tree("V", new Tree("*"), cont);
            case NAME:
                lex.nextToken();
                return new Tree("V", new Tree("a"));
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at V ");
        }
    }

    Tree C() throws ParseException {
        switch (lex.curToken()) {
            case COMMA:
                lex.nextToken();
                Tree cont = V();
                Tree cont2 = C();
                return new Tree("C", new Tree(","), cont, cont2);
            case SEMICOLON:
                lex.nextToken();
                return new Tree("C", new Tree(";"));
            default:
                throw new AssertionError(lex.curToken.toString() + " isnt first at C ");
        }
    }
}
