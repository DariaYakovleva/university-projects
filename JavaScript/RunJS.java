import javax.script.*;
import java.io.*;

public class RunJS {
    public static class IO {
        public void print(String message) {
            System.out.print(message);
        }
        public void println(String message) {
            System.out.println(message);
        }
    }
    public static void main(String[] args) throws ScriptException, IOException {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.put("io", new IO());
        engine.eval(
            "println = function(message) { io.println(message); };" +
            "print = function(message) { io.print (message); };"
        );

        engine.eval(new InputStreamReader(new FileInputStream("eval.js"), "UTF-8"));
    }
}
