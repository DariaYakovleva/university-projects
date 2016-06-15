import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.pattern.TokenTagToken;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Daria on 02.06.2016.
 */
public class MyParser {


    class Node {
        String attrs;
        public Node(String attrs) {
            this.attrs = attrs;
            children = new ArrayList<>();
            codes = new ArrayList<>();
        }
        List<List<Token>> children;
        List<String> codes;
        void addChild(List<Token> lst, String code) {
            children.add(lst);
            codes.add(code);
        }
    }

    public MyParser() {
    }

    String start;
    Map<Token, Set<Token>> first;
    Map<Token, Set<Token>> follow;
    Map<Token, Node> terms;
    Map<Token, Node> nterms;

    public void parse(String fileName, String start) throws IOException, ParseException {

        ANTLRFileStream input = null;
        try {
            input = new ANTLRFileStream(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start = start;
        GrammarsGrammarLexer lex = new GrammarsGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lex);
        GrammarsGrammarParser parser = new GrammarsGrammarParser(tokens);
        first = new HashMap<>();
        follow = new HashMap<>();
        GrammarsGrammarParser.StartContext t = parser.start();
        init(t);
        System.out.println("TERMS");
        for (Token term: terms.keySet()) {
            System.out.println(term.getText() + " " + Arrays.toString(terms.get(term).children.toArray()));
        }
        System.out.println("NTERMS");
        for (Token term: nterms.keySet()) {
            System.out.println(term.getText() + " " + Arrays.toString(nterms.get(term).children.toArray()));
        }
        System.out.println("FIRST");
        constructFirst();
        for (Token key: first.keySet()) {
            System.out.println(key.getText() + ": " + Arrays.toString(first.get(key).toArray()));
        }
        System.out.println("FOLLOW");
        constructFollow();
        for (Token key: follow.keySet()) {
            System.out.println(key.getText() + ": " + Arrays.toString(follow.get(key).toArray()));
        }
//        createParser();

    }

    void init(GrammarsGrammarParser.StartContext s) {
        terms = new HashMap<>();
        nterms = new HashMap<>();

        //terminals
        for (GrammarsGrammarParser.TerminalContext t: s.terminal()) {
            Token token = t.UPPERNAME().getSymbol();
            Node tt = new Node("");
            for (TerminalNode str: t.STR()) {
                List<Token> ch = new ArrayList<>();
                ch.add(str.getSymbol());
                tt.addChild(ch, "");
            }
            terms.put(token, tt);
        }

        //non-terminals
        for (GrammarsGrammarParser.Rule1Context r: s.rule1()) {
            Token token = r.LOWERNAME().getSymbol();
            Node tt = new Node("");
//            if (r.rreturn() != null) tt.rreturn = r.rreturn().params().BLOCK2().getText();
//            if (r.params() != null) tt.attrs = r.params().getText();
            for (GrammarsGrammarParser.StateContext to: r.state()) {
                List<Token> lst = new ArrayList<>();
                for (GrammarsGrammarParser.State2Context st: to.state2()) {
//                    if (st.BLOCK2() != null) attrs = st.BLOCK2().getText();
                    if (st.nnext().UPPERNAME() != null) lst.add(st.nnext().UPPERNAME().getSymbol());
                    else lst.add(st.nnext().LOWERNAME().getSymbol());
                }
                String code = "";
                if (to.code() != null) code = to.code().getText();
                tt.addChild(lst, code);
            }
            nterms.put(token, tt);
        }

    }

    void constructFirst() {
        for (Token t: terms.keySet()) {
            Set<Token> lst = new HashSet<>();
            lst.add(t);
            first.put(t, lst);
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Token nonT: nterms.keySet()) {
                changed |= findFirst(nonT);
            }
        }
    }

    boolean findFirst(Token token) {
        if (!first.containsKey(token)) first.put(token, new HashSet<>());
        if (terms.containsKey(token)) return false;
        int prevSize = first.get(token).size();
        System.err.println(token.getText() + " " + nterms.get(token));
        for (List<Token> rule: nterms.get(token).children) {
            int pos = 0;
            boolean eps = true;
            while (pos < rule.size() && eps) {
                Token to = rule.get(pos);
                if (!first.containsKey(to)) findFirst(to);
                first.get(token).addAll(first.get(to));
                eps &= haveEps(to);
            }
        }
        return (prevSize != first.get(token).size());
    }

    boolean haveEps(Token token) {
        if (token.getText().compareTo("EPS") == 0) return true;
        if (terms.containsKey(token)) return false;
        for (List<Token> ch: nterms.get(token).children) {
            if (ch.size() == 1 && terms.containsKey(ch.get(0))) return (ch.get(0).getText().compareTo("EPS") == 0);
        }
        return false;
    }

    void constructFollow() {
        boolean changed = true;
        while (changed) {
            changed = false;
            for (Token var: nterms.keySet()) {
                for (List<Token> rule: nterms.get(var).children) {
                    for (int i = 0; i < rule.size() - 1; i++) {
                        Token b = rule.get(i);
                        if (!follow.containsKey(b)) follow.put(b, new HashSet<Token>());
                        int cnt = follow.get(b).size();
                        Token c = rule.get(i + 1);
                        follow.get(b).addAll(first.get(c));
                        if (cnt != follow.get(b).size()) changed = true;
                    }
                    Token a = var;
                    if (!follow.containsKey(a)) follow.put(a, new HashSet<Token>());
                    for (int i = rule.size() - 1; i >= 0; i--) {
                        Token b = rule.get(i);
                        if (!follow.containsKey(b)) follow.put(b, new HashSet<Token>());
                        int cnt = follow.get(b).size();
                        follow.get(b).addAll(follow.get(a));
                        if (cnt != follow.get(b).size()) changed = true;
                        if (!haveEps(b)) break;
                    }
                }
            }
        }
        for (Token s: follow.keySet()) {
            Token end = null;
            for (Token t: terms.keySet()) if (t.getText().compareTo("$") == 0) end = t;
            follow.get(s).add(end);
        }
    }


}
