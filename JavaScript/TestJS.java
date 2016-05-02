import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

/**
* Created by Niyaz Nigmatullin on 4/28/14.
*/
public class TestJS {


    static final Random RNG = new Random(58L);

    public static class IO {
        public void print(String message) {
            System.out.print(message);
        }

        public void println(String message) {
            System.out.println(message);
        }
    }

    public static class Result {
        public double res;
    }

    static boolean verbose = false;
    static final String VERSION = "1.2";

    public static void main(String[] args) throws ScriptException, IOException {
        boolean onlyEasy = false;
        for (String e : args) {
            if (e.equals("-v")) {
                verbose = true;
            }
            if (e.equals("-easy")) {
                onlyEasy = true;
            }
        }
        if (!onlyEasy) {
            System.out.println("For testing only easy task, use [-easy] parameter");
        } else {
            System.out.println("Testing only easy task");
        }
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.put("io", new IO());
        Result result = new Result();
        engine.put("result", result);
        engine.eval(
                "println = function(message) { io.println(message); };" +
                        "print = function(message) { io.print (message); };"
        );
        engine.eval(new InputStreamReader(new FileInputStream("eval.js"), "UTF-8"));
        engine.eval("var expr;");
        final int TESTS = 50;
        System.out.println("testing easy (" + TESTS + " tests)");
        for (int i = 0; i < TESTS; i++) {
            System.out.print((i + 1) + " ");
            if (!testExpression(engine, result, Generator.genExpression(1, i / 20 + 1), false)) {
                return;
            }
        }
        System.out.println();
        if (!onlyEasy) {
            System.out.println("testing hard (" + TESTS + " tests)");
            for (int i = 0; i < TESTS; i++) {
                System.out.print((i + 1) + " ");
                if (!testExpression(engine, result, Generator.genExpression(1, i / 20 + 1), true)) {
                    return;
                }
            }
            System.out.println();
        } else {
            System.out.print("only easy ");
        }
        System.out.println("tests passed, version = " + VERSION);
    }

    static final int MAXVALUE = 8;

    static boolean testExpression(ScriptEngine engine, Result result, Test test, boolean hard) {
        String expr = hard ? ("parse(\"" + test.polish + "\")") : test.expr;
        //System.out.println(expr);
        if (verbose) {
            System.out.println();
            System.out.println(expr);
        }
        try {
            engine.eval("expr = " + expr + ";");
        } catch (ScriptException e) {
            System.out.println();
            System.out.println("Exception happened while evaluating expression:");
            System.out.println(expr);
            e.printStackTrace();
            return false;
        }
        for (int i = 0; i <= MAXVALUE; i++) {
            for (int j = 0; j <= MAXVALUE; j++) {
                for (int k = 0; k <= MAXVALUE; k++) {
                    try {
                        engine.eval("result.res = expr(" + i + "," + j + "," + k + ");");
                    } catch (ScriptException e) {
                        System.out.println();
                        System.out.println("Exception happened while calculating function:");
                        System.out.println(expr);
                        e.printStackTrace();
                        return false;
                    }
                    double correct = test.answer[i][j][k];
                    double res = result.res;
                    boolean bad = true;
                    if (correct == res) {
                        bad = false;
                    } else if (Double.isNaN(correct)) {
                        if (Double.isNaN(res)) {
                            bad = false;
                        }
                    } else if (Double.isInfinite(correct)) {
                        if (correct != res) {
                            bad = false;
                        }
                    } else if (Double.isNaN(res) || Double.isInfinite(res)) {

                    } else if (Math.abs(correct - res) / Math.max(1., Math.abs(correct)) < 1e-4) {
                        bad = false;
                    }
                    if (bad) {
                        System.out.println(String.format("expected %f, found %f", correct, res));
                        System.out.println(String.format("%s(%d,%d,%d)", expr, i, j, k));
                        return false;
                    }
                }
            }
        }
        return true;
    }


    static class Generator {

        static double[][][] add(double[][][] x, double[][][] y) {
            double[][][] ret = new double[x.length][][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new double[x[i].length][];
                for (int j = 0; j < x[i].length; j++) {
                    ret[i][j] = new double[x[i][j].length];
                    for (int k = 0; k < x[i][j].length; k++) {
                        ret[i][j][k] = x[i][j][k] + y[i][j][k];
                    }
                }
            }
            return ret;
        }


        static double[][][] subtract(double[][][] x, double[][][] y) {
            double[][][] ret = new double[x.length][][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new double[x[i].length][];
                for (int j = 0; j < x[i].length; j++) {
                    ret[i][j] = new double[x[i][j].length];
                    for (int k = 0; k < x[i][j].length; k++) {
                        ret[i][j][k] = x[i][j][k] - y[i][j][k];
                    }
                }
            }
            return ret;
        }

        static double[][][] multiply(double[][][] x, double[][][] y) {
            double[][][] ret = new double[x.length][][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new double[x[i].length][];
                for (int j = 0; j < x[i].length; j++) {
                    ret[i][j] = new double[x[i][j].length];
                    for (int k = 0; k < x[i][j].length; k++) {
                        ret[i][j][k] = x[i][j][k] * y[i][j][k];
                    }
                }
            }
            return ret;
        }

        static double[][][] divide(double[][][] x, double[][][] y) {
            double[][][] ret = new double[x.length][][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new double[x[i].length][];
                for (int j = 0; j < x[i].length; j++) {
                    ret[i][j] = new double[x[i][j].length];
                    for (int k = 0; k < x[i][j].length; k++) {
                        ret[i][j][k] = x[i][j][k] / y[i][j][k];
                    }
                }
            }
            return ret;
        }

        static double[][][] log(double[][][] z) {
            double[][][] ret = new double[z.length][][];
            for (int i = 0; i < z.length; i++) {
                ret[i] = new double[z[i].length][];
                for (int j = 0; j < z[i].length; j++) {
                    ret[i][j] = new double[z[i][j].length];
                    for (int k = 0; k < z[i][j].length; k++) {
                        ret[i][j][k] = Math.log(z[i][j][k]);
                    }
                }
            }
            return ret;
        }

        static double[][][] negate(double[][][] z) {
            double[][][] ret = new double[z.length][][];
            for (int i = 0; i < z.length; i++) {
                ret[i] = new double[z[i].length][];
                for (int j = 0; j < z[i].length; j++) {
                    ret[i][j] = new double[z[i][j].length];
                    for (int k = 0; k < z[i][j].length; k++) {
                        ret[i][j][k] = -(z[i][j][k]);
                    }
                }
            }
            return ret;
        }

        static double[][][] abs(double[][][] z) {
            double[][][] ret = new double[z.length][][];
            for (int i = 0; i < z.length; i++) {
                ret[i] = new double[z[i].length][];
                for (int j = 0; j < z[i].length; j++) {
                    ret[i][j] = new double[z[i][j].length];
                    for (int k = 0; k < z[i][j].length; k++) {
                        ret[i][j][k] = Math.abs(z[i][j][k]);
                    }
                }
            }
            return ret;
        }

        static Test genExpression(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genExpression(depth + 1, coefficient);
                Test t2 = genSummand(depth, coefficient);
                if (RNG.nextBoolean()) {
                    return new Test("add(" + t1.expr + "," + t2.expr + ")", t1.polish + " " + t2.polish + " +", add(t1.answer, t2.answer));
                } else {
                    return new Test("subtract(" + t1.expr + "," + t2.expr + ")", t1.polish + " " + t2.polish + " -", subtract(t1.answer, t2.answer));
                }
            } else {
                return genSummand(depth, coefficient);
            }
        }

        static Test genSummand(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genSummand(depth + 1, coefficient);
                Test t2 = genFactor(depth, coefficient);
                if (RNG.nextBoolean()) {
                    return new Test("multiply(" + t1.expr + "," + t2.expr + ")", t1.polish + " " + t2.polish + " *", multiply(t1.answer, t2.answer));
                } else {
                    return new Test("divide(" + t1.expr + "," + t2.expr + ")", t1.polish + " " + t2.polish + " /", divide(t1.answer, t2.answer));
                }
            } else {
                return genFactor(depth, coefficient);
            }
        }

        static Test genFactor(int depth, int coefficient) {
            return genValue(depth, coefficient);
        }

        static Test genValue(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t = genExpression(depth + 1, coefficient);
                int type = RNG.nextInt(2);
                if (type == 0) {
                    return new Test("abs(" + t.expr + ")", t.polish + " abs", abs(t.answer));
                } else {
                    return new Test("log(" + t.expr + ")", t.polish + " log", log(t.answer));
                }
            } else if (RNG.nextBoolean()) {
                int z = Math.abs(RNG.nextInt());
                double[][][] ret = new double[MAXVALUE + 1][MAXVALUE + 1][MAXVALUE + 1];
                for (int i = 0; i <= MAXVALUE; i++) {
                    for (int j = 0; j <= MAXVALUE; j++) {
                        Arrays.fill(ret[i][j], z);
                    }
                }
                return new Test("cnst(" + z + ")", "" + z, ret);
            } else {
                int id = RNG.nextInt(3);
                double[][][] ret = new double[MAXVALUE + 1][MAXVALUE + 1][MAXVALUE + 1];
                for (int i = 0; i <= MAXVALUE; i++) {
                    for (int j = 0; j <= MAXVALUE; j++) {
                        for (int k = 0; k <= MAXVALUE; k++) {
                            ret[i][j][k] = (id == 0 ? i : id == 1 ? j : k);
                        }
                    }
                }
                return new Test("variable(\"" + "xyz".charAt(id) + "\")", "xyz".charAt(id) + "", ret);
            }
        }

        static boolean makeNewBranch(int depth, int coefficient) {
            return RNG.nextInt(depth + coefficient) < coefficient;
        }
    }

    static class Test {
        String expr;
        String polish;
        double[][][] answer;

        Test(String expr, String polish, double[][][] answer) {
            this.expr = expr;
            this.polish = polish;
            this.answer = answer;
        }
    }
}


