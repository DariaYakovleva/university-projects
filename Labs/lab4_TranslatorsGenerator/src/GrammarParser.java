import java.util.*;
import java.io.*;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Token;

public class GrammarParser {
GrammarLexer lex;
class Node {
    Map<String, Node> children;
    String text;
    int res;
    Node(String text) {
        this.text = text;
        children = new HashMap<>();
    }
    void addChild(String nterm, Node x) {
        children.put(nterm, x);
    }
}
public Node parse(String fileName) throws Exception {
FileInputStream file = new FileInputStream(fileName);
    Scanner in = new Scanner(file);
    lex = new GrammarLexer(in.next());
    lex.nextToken();
    return start();
}
class Node_t extends Node {

        Node_t(String text) {
            super(text);
        }
    }
Node_t t() throws Exception {
   Node_t res = new Node_t("t");
   switch (lex.curToken) {
    case INT: {
        Node_f t0 = f();
        res.addChild("f", t0);
        Node_t1 t1 = t1(res.children.get("f").res);
        res.addChild("t1", t1);

    	res.res = res.children.get("t1").res;
    
    }
    break;
    case LB: {
        Node_f t2 = f();
        res.addChild("f", t2);
        Node_t1 t3 = t1(res.children.get("f").res);
        res.addChild("t1", t3);

    	res.res = res.children.get("t1").res;
    
    }
    break;
    }
return res;
}
class Node_f extends Node {

        Node_f(String text) {
            super(text);
        }
    }
Node_f f() throws Exception {
   Node_f res = new Node_f("f");
String cur = lex.curValue;
   switch (lex.curToken) {
    case INT: {
        lex.nextToken();
        Node t0 = new Node("'0'");
        res.addChild("'0'", t0);
     	
	    res.res = Integer.parseInt(cur);
    
    }
    break;
    case LB: {
        lex.nextToken();
        Node t1 = new Node("'('");
        res.addChild("'('", t1);
        Node_expr t2 = expr();
        res.addChild("expr", t2);
        lex.nextToken();
        Node t3 = new Node("')'");
        res.addChild("')'", t3);

    	res.res = res.children.get("expr").res;
    
    }
    break;
    }
return res;
}
class Node_start extends Node {

        Node_start(String text) {
            super(text);
        }
    }
Node_start start() throws Exception {
   Node_start res = new Node_start("start");
   switch (lex.curToken) {
    case INT: {
        Node_exprc t0 = exprc();
        res.addChild("exprc", t0);

		res.res = res.children.get("exprc").res;
	
    }
    break;
    case LB: {
        Node_exprc t1 = exprc();
        res.addChild("exprc", t1);

		res.res = res.children.get("exprc").res;
	
    }
    break;
    }
return res;
}
class Node_exprc extends Node {

        Node_exprc(String text) {
            super(text);
        }
    }
Node_exprc exprc() throws Exception {
   Node_exprc res = new Node_exprc("exprc");
   switch (lex.curToken) {
    case INT: {
        Node_expr t0 = expr();
        res.addChild("expr", t0);

		
		res.res = res.children.get("expr").res;
	
    }
    break;
    case LB: {
        Node_expr t1 = expr();
        res.addChild("expr", t1);

		
		res.res = res.children.get("expr").res;
	
    }
    break;
    }
return res;
}
class Node_expr extends Node {

        Node_expr(String text) {
            super(text);
        }
    }
Node_expr expr() throws Exception {
   Node_expr res = new Node_expr("expr");
   switch (lex.curToken) {
    case INT: {
        Node_t t0 = t();
        res.addChild("t", t0);
        Node_e1 t1 = e1(res.children.get("t").res);
        res.addChild("e1", t1);

		res.res = res.children.get("e1").res;
	
    }
    break;
    case LB: {
        Node_t t2 = t();
        res.addChild("t", t2);
        Node_e1 t3 = e1(res.children.get("t").res);
        res.addChild("e1", t3);

		res.res = res.children.get("e1").res;
	
    }
    break;
    }
return res;
}
class Node_e1 extends Node {

        Node_e1(String text) {
            super(text);
        }
    }
Node_e1 e1(int tt) throws Exception {
   Node_e1 res = new Node_e1("e1");
res.res = tt;
   switch (lex.curToken) {
    case ADD: {
        lex.nextToken();
        Node t0 = new Node("'+'");
        res.addChild("'+'", t0);
        Node_t t1 = t();
        res.addChild("t", t1);
        Node_e1 t2 = e1(res.children.get("t").res + tt);
        res.addChild("e1", t2);

    	res.res = res.children.get("e1").res;
    
    }
    break;
    case SUB: {
        lex.nextToken();
        Node t3 = new Node("'-'");
        res.addChild("'-'", t3);
        Node_t t4 = t();
        res.addChild("t", t4);
        Node_e1 t5 = e1(tt - res.children.get("t").res);
        res.addChild("e1", t5);

    	res.res = res.children.get("e1").res;
    
    }
    break;
    case END: {
    }
    break;
    case RB: {
    }
    break;
    }
return res;
}
class Node_t1 extends Node {

        Node_t1(String text) {
            super(text);
        }
    }
Node_t1 t1(int tt) throws Exception {
   Node_t1 res = new Node_t1("t1");
res.res = tt;
   switch (lex.curToken) {
    case MUL: {
        lex.nextToken();
        Node t0 = new Node("'*'");
        res.addChild("'*'", t0);
        Node_f t1 = f();
        res.addChild("f", t1);
        Node_t1 t2 = t1(res.children.get("f").res * tt);
        res.addChild("t1", t2);

    	res.res = res.children.get("t1").res;
    
    }
    break;
    case END: {
    }
    break;
    case RB: {
    }
    break;
    case ADD: {
    }
    break;
    case SUB: {
    }
    break;
    }
return res;
}
}