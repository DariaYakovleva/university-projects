import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.*;


public class MyParser {

    class Rule {
        public Rule(String name, String attrs) {
            this.name = name;
            this.attrs = attrs;
        }
        public String toString() {
            return name + " " + attrs;
        }
        String name;
        String attrs;
    }

    class Node {

        public Node(String name, int type) {
            this.name = name;
            children = new ArrayList<>();
            codes = new ArrayList<>();
            this.type = type;
        }

        List< List<Rule> > children;
        List<String> codes;
        String attrs = "";
        String name;
        int type;
        String llocal = "";
        String rreturn = "";
        String iinit = "";
        String aafter = "";

        void addChild(List<Rule> lst, String code) {
            children.add(lst);
            codes.add(code);
        }

        boolean hasEps() {
            if (name.compareTo("''") == 0) return true;
            for (List<Rule> ch: children) {
                if (ch.size() == 1 && terminals.containsKey(ch.get(0).name)) return (ch.get(0).name.compareTo("EPS") == 0);
            }
            return false;
        }
    }

    final int TERM = 1;
    final int NONTERM = 0;

    Map<String, Set<Character>> first;
    Map<String, Set<Character>> follow;
    Map<String, Node> terminals;
    Map<String, Node> nonTerminals;
    String start;
    String varRes = "";
    String varAttrs = "";
    Map<String, String> tokens = new HashMap<>();


    String fixCode(String code) {
        String res = "";
        int pos = 0;
        while (pos < code.length()) {
            if (code.charAt(pos) != '$') {
                res += code.charAt(pos);
                pos++;
            } else {
                pos++;
                String tmp = "";
                while (code.charAt(pos) >= 'a' && code.charAt(pos) <= 'z') {
                    tmp += code.charAt(pos);
                    pos++;
                }
                if (varAttrs.contains(tmp)) res += tmp;
                else if (varRes.contains(tmp)) res += "res." + tmp;
                else res += "t";
            }
        }
        return res;
    }

    void init(GrammarsGrammarParser.StartContext s) {
        terminals = new HashMap<>();
        nonTerminals = new HashMap<>();

        //terminals
        for (GrammarsGrammarParser.TerminalContext t: s.terminal()) {
            String name = t.UPPERNAME().getText();
            Node tt = new Node(name, TERM);
            for (TerminalNode str: t.STR()) {
                List<Rule> ch = new ArrayList<>();
                ch.add(new Rule(str.getText(), ""));
                tt.addChild(ch, "");
                tokens.put(str.getText(), t.UPPERNAME().getSymbol().getText());
            }

            terminals.put(name, tt);
        }
        List<Rule> ch = new ArrayList<>();
        ch.add(new Rule("'$'", ""));
        Node end = new Node("END", TERM);
        end.addChild(ch, "");
        terminals.put("END", end);

        //non-terminals
        for (GrammarsGrammarParser.Rule1Context r: s.rule1()) {
            String name = r.LOWERNAME().getText();
            Node tt = new Node(name, NONTERM);
            if (r.init() != null) tt.iinit = r.init().code().BLOCK().getText();
            if (r.llocal() != null) tt.llocal = r.llocal().params().BLOCK2().getText();
            if (r.after() != null) tt.aafter = r.after().code().BLOCK().getText();
            if (r.rreturn() != null) tt.rreturn = r.rreturn().params().BLOCK2().getText();
            if (r.params() != null) tt.attrs = r.params().getText();
            for (GrammarsGrammarParser.StateContext to: r.state()) {
                List<Rule> lst = new ArrayList<>();
                for (GrammarsGrammarParser.State2Context st: to.state2()) {
                    String code = "";
                    String attrs = "";
                    if (st.BLOCK2() != null) attrs = st.BLOCK2().getText();
                    lst.add(new Rule(st.nnext().getText(), attrs));
                }
                String code = "";
                if (to.code() != null) code = to.code().getText();
                tt.addChild(lst, code);
            }
            nonTerminals.put(name, tt);
        }
    }



    boolean findFirst(String var) {
        if (terminals.containsKey(var)) {
            return false;
        }
        if (!first.containsKey(var)) first.put(var, new HashSet<Character>());
        int cnt = first.get(var).size();
        for (List<Rule> rule: nonTerminals.get(var).children) {
            for (Rule to1: rule) {
                String to = to1.name;
                if (!first.containsKey(to)) findFirst(to);
                first.get(var).addAll(first.get(to));
                if (nonTerminals.containsKey(to) && !nonTerminals.get(to).hasEps()) break;
                if (terminals.containsKey(to) && !terminals.get(to).hasEps()) break;
            }
        }
        return (cnt != first.get(var).size());
    }

    void constructFirst() {
        for (String t: terminals.keySet()) {
            Node tt = terminals.get(t);
            Set<Character> cur = new HashSet<>();
            for (List<Rule> word: tt.children) {
                cur.add(word.get(0).name.charAt(1));
            }
            if (first.containsKey(t)) {
                cur.addAll(first.get(t));
                first.replace(t, cur);
            } else {
                first.put(t, cur);
            }
        }
        boolean changed = true;
        while (changed) {
//            System.err.println("while");
            changed = false;
            for (String nonT: nonTerminals.keySet()) {
                String name = nonT;
                changed |= findFirst(name);
            }
        }
    }

    void constructFollow() {
        boolean changed = true;
        while (changed) {
//            System.err.println("while2");
            changed = false;
            for (String var: nonTerminals.keySet()) {
                for (List<Rule> rule: nonTerminals.get(var).children) {
                    for (int i = 0; i < rule.size() - 1; i++) {
                        String b = rule.get(i).name;
                        if (!follow.containsKey(b)) follow.put(b, new HashSet<Character>());
                        int cnt = follow.get(b).size();
                        String c = rule.get(i + 1).name;
                        follow.get(b).addAll(first.get(c));
                        if (cnt != follow.get(b).size()) changed = true;
                    }
                    String a = var;
                    if (!follow.containsKey(a)) follow.put(a, new HashSet<Character>());
                    for (int i = rule.size() - 1; i >= 0; i--) {
                        String b = rule.get(i).name;
                        if (!follow.containsKey(b)) follow.put(b, new HashSet<Character>());
                        int cnt = follow.get(b).size();
                        follow.get(b).addAll(follow.get(a));
                        if (cnt != follow.get(b).size()) changed = true;
                        if (nonTerminals.containsKey(b) && !nonTerminals.get(b).hasEps()) break;
                        if (terminals.containsKey(b) && !terminals.get(b).hasEps()) break;

                    }
                }
            }
        }
        for (String s: follow.keySet()) {
            follow.get(s).add('$');
        }
    }


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
//        System.err.println("tokens " + Arrays.toString(lex.getAllTokens().toArray()));
        GrammarsGrammarParser parser = new GrammarsGrammarParser(tokens);
        first = new HashMap<>();
        follow = new HashMap<>();

        GrammarsGrammarParser.StartContext t = parser.start();
        init(t);
        System.out.println("TERMS");
        for (String term: terminals.keySet()) {
            System.out.println(term + " " + terminals.get(term).name + Arrays.toString(terminals.get(term).children.toArray()));
        }
        System.out.println("NTERMS");
        for (String term: nonTerminals.keySet()) {
            System.out.println(term + " " + nonTerminals.get(term).name + Arrays.toString(nonTerminals.get(term).children.toArray()));
        }
        System.out.println("FIRST");
        constructFirst();
        for (String key: first.keySet()) {
            System.out.println(key + ": " + Arrays.toString(first.get(key).toArray()));
        }

        System.out.println("FOLLOW");
        constructFollow();
        for (String key: follow.keySet()) {
            System.out.println(key + ": " + Arrays.toString(follow.get(key).toArray()));
        }

        createParser();
    }


    void createParser() throws IOException, ParseException {
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("src/GrammarParser.java"), "UTF-8");
        createHeaders(out);
        createStruct(out);
        createMainFunction(out);
//        createNextToken(out);
//        createConsume(out);
        for (String nterm: nonTerminals.keySet()) {
            createFunction(out, nterm);
        }
        createEnd(out);
        out.close();
    }

    void createHeaders(OutputStreamWriter out) throws IOException {
        String res = "import java.util.*;\n";
        res += "import java.io.*;\n";
        res += "import org.antlr.v4.runtime.ANTLRFileStream;\n";
        res += "import org.antlr.v4.runtime.Token;\n";

        res += "\n";
        res += "public class GrammarParser {\n";
        res += "GrammarLexer lex;\n";
        out.write(res);
    }


    void createMainFunction(OutputStreamWriter out) throws IOException {
        String res = "public Node parse(String fileName) throws Exception {\n";
        res += "FileInputStream file = new FileInputStream(fileName);\n" +
                "    Scanner in = new Scanner(file);\n" +
                "    lex = new GrammarLexer(in.next());\n" +
                "    lex.nextToken();\n";
        res += "    return " + start + "();\n";
        res += "}\n";
        out.write(res);
    }

    void createNextToken(OutputStreamWriter out) throws IOException {
        String res = "void nextToken() {\n";
        res += "    if (cnt == tokens.size()) return;\n";
        res += "    curToken = tokens.get(cnt).charAt(0);\n";
        res += "    cnt++;\n";
        res += "}\n";
        out.write(res);
    }

    void createEnd(OutputStreamWriter out) throws IOException {
        String res = "}";
        out.write(res);
    }

    void createStruct(OutputStreamWriter out) throws IOException {
        String res = "class Node {\n" +
                "    Map<String, Node> children;\n" +
                "    String text;\n" +
                "    int res;\n" +
                "    Node(String text) {\n" +
                "        this.text = text;\n" +
                "        children = new HashMap<>();\n" +
                "    }\n" +
                "    void addChild(String nterm, Node x) {\n" +
                "        children.put(nterm, x);\n" +
                "    }\n" +
                "}\n";
        out.write(res);
    }

    void createConsume(OutputStreamWriter out) throws IOException, ParseException {
        String res = "void consume(char c) throws Exception {\n" +
                     "    if (curToken != c) {\n" +
                     "        throw new IOException(\"incorrect symbol, need = \" + lex.curValue + \", found = \" + c);\n    }\n";
        res += "    lex.nextToken();\n";
        res += "}\n";
        out.write(res);
    }

    void createFunction(OutputStreamWriter out, String name) throws IOException {
        Node rule = nonTerminals.get(name);
        Integer num = 0;
        String res = "";
        String attrs = "";
        if (!rule.attrs.isEmpty()) attrs = rule.attrs.substring(1, rule.attrs.lastIndexOf("]"));
        res += "class Node_" + name + " extends Node {\n" +
                "\n" +
                "        Node_" + name + "(String text) {\n" +
                "            super(text);\n" +
                "        }\n";
        int lloc = rule.llocal.lastIndexOf("]");
        int lret = rule.rreturn.lastIndexOf("]");
        if (lloc > 0) res += rule.llocal.substring(1, lloc).replace(",", ";\n").concat(";") + "\n";
        if (lret > 0) res += rule.rreturn.substring(1, lret).replace(",", ";\n").concat(";") + "\n";
        varAttrs = rule.attrs;
        varRes = rule.llocal + "#" + rule.rreturn;
//        res += attrs.replace(",", ";\n").concat(";");
        res += "    }\n";
        res += "Node_" + name + " " + name + "(" + attrs + ") throws Exception {\n" +
                "   Node_" + name + " res = new Node_" + name + "(\"" + name + "\");\n";
        int lenInit = rule.iinit.lastIndexOf("}");
        if (lenInit > 0) res += fixCode(rule.iinit.substring(1, lenInit)) + "\n";
        res+= "   switch (lex.curToken) {\n";
        for (int j = 0; j < rule.children.size(); j++) {
            List<Rule> st = rule.children.get(j);
            for (char c: first.get(st.get(0).name)) { //X1

                String letters = "'" + c + "'";
                if (c == '\'') continue;
                res += "    case " + tokens.get(letters) + ": {\n";
//                res += "        Node t;\n";
                for (int i = 0; i < st.size(); i++) {
                    String val = st.get(i).name; //X_i

                    if (terminals.containsKey(val)) {
                        String cc = terminals.get(val).children.get(0).get(0).name;
//                        String cc= val;

                        res += "        lex.nextToken();\n";
                        res += "        Node t" + num.toString() + " = new Node(\"" + cc + "\");\n";
                        res += "        res.addChild(\"" + cc + "\", t" + num.toString() + ");\n";
                        num++;
                    } else {
                        attrs = "";
                        if (!st.get(i).attrs.isEmpty()) attrs = st.get(i).attrs.substring(1, st.get(i).attrs.lastIndexOf("]"));
                        res += "        Node_" + val + " t" + num.toString() + " = " + val + "(" + attrs + ");\n";
                        res += "        res.addChild(\"" + val + "\", t" + num.toString() + ");\n";
                        num++;
                    }
                }
                if (!rule.codes.get(j).isEmpty()) res += fixCode(rule.codes.get(j).substring(1, rule.codes.get(j).lastIndexOf("}"))) + "\n"; // TODO
                res += "    }\n    break;\n";
            }
        }
        if (first.get(name).contains('\'')) {
            for (char c: follow.get(name)) {
                if (c == '\'') continue;
                String letters = "'" + c + "'";
                res += "    case " + tokens.get(letters) + ": {\n";
//              res += "        res.addChild(Node(EPS));\n";
                res += "    }\n    break;\n";
            }
        }
//         default throw bwe Exception("unexpected letter");
        res += "    }\n";

        int lenAft = nonTerminals.get(name).aafter.lastIndexOf("}");
        if (lenAft > 0) res += nonTerminals.get(name).aafter.substring(1, lenAft).replace("$", "res.") + "\n";
        res += "return res;\n}\n";
        out.write(res);
    }

}
