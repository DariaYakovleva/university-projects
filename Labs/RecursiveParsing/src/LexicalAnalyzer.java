import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Daria on 19.03.2016.
 */

enum Token {
    COMMA, SEMICOLON, STAR, NAME, END;
    //,;*a $
}

public class LexicalAnalyzer {

    String input;
    int curPos;
    Token curToken;
    char curChar;

    public LexicalAnalyzer(String input) {
        this.input = input + "$";
        curPos = 0;
        nextChar();
    }

    void nextChar() {
        curChar = input.charAt(curPos);
        curPos++;
    }

    public Token curToken() {
        return curToken;
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
        if (curChar == '*') {
            curToken = Token.STAR;
            nextChar();
        } else if (curChar == ';') {
            curToken = Token.SEMICOLON;
            nextChar();
        } else if (curChar == ',') {
            curToken = Token.COMMA;
            nextChar();
        } else if (curChar == '$') {
            curToken = Token.END;
        } else {
            String type = "" + curChar;
            while (next() && !isBlank(curChar) && curChar != ',' && curChar != ';') {
                nextChar();
                type += curChar;
            }
            curToken = Token.NAME;
        }
//        System.err.println(curToken);
    }
}
