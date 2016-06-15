import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 19.03.2016.
 */

enum Token {
    ADD, MUL, INT, LB, RB, EVAL, EPS, END, SUB;
}

public class GrammarLexer {

    class MyToken {
        Token token;
        String value;
        MyToken(Token token, String value) {
            this.token = token;
            this.value = value;
        }
        String getValue() {
            return value;
        }
        Token getToken() {
            return token;
        }
    }
    String input;
    int curPos;
    Token curToken;
    List<MyToken> tokens;
    String curValue = "";
    char curChar;

    public GrammarLexer(String input) {
        this.input = input + "$";
        curPos = 0;
        nextChar();
        tokens = new ArrayList<>();
    }


    void nextChar() {
        curChar = input.charAt(curPos);
        curPos++;
    }

    boolean isBlank(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    boolean next() {
        return curPos < input.length();
    }

    public void nextToken() throws ParseException {
        while (next() && isBlank(curChar)) {
            nextChar();
        }
        if (isBlank(curChar)) {
            throw new ParseException("End of input string at position ", curPos);
        }
        curValue = curChar + "";
        if (curChar == '+') {
            curToken = Token.ADD;
            nextChar();
        } else if (curChar == '*') {
            curToken = Token.MUL;
            nextChar();
        } else if (curChar == '(') {
            curToken = Token.LB;
            nextChar();
        } else if (curChar == ')') {
            curToken = Token.RB;
            nextChar();
        } else if (curChar == 'e') {
            curToken = Token.EVAL;
            nextChar();
        } else if (curChar == '-') {
            curToken = Token.SUB;
            nextChar();
        } else {
            curValue = "";
            while (next() && !isBlank(curChar) && curChar >= '0' && curChar <= '9') {
                curValue += curChar;
                nextChar();
            }
            curToken = Token.INT;
        }
        tokens.add(new MyToken(curToken, curValue));
    }
}
