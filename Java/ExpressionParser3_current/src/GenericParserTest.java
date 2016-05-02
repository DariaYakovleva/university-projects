import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Niyaz Nigmatullin on 4/21/14.
 */
public class GenericParserTest {

    static final Random RNG = new Random(58L);

    public static void main(String[] args) {
        {
            System.err.println("check TL");
            Test t = new Generator(0).genExpression(1, 10);
            int toAdd = 777777;
            int[] pair = new int[t.expr.length()];
            List<Integer> stack = new ArrayList<Integer>();
            int count = 0;
            int[] id = new int[t.expr.length()];
            for (int i = 0; i < pair.length; i++) {
                char c = t.expr.charAt(i);
                if (c == '(') {
                    stack.add(i);
                    id[i] = count;
                    ++count;
                } else if (c == ')') {
                    int j = stack.remove(stack.size() - 1);
                    pair[i] = j;
                    pair[j] = i;
                    id[j] = id[i];
                }
            }
            int[] d = new int[count];
            int sum = 0;
            for (int i = 0; i < count; i++) {
                d[i] = RNG.nextInt(toAdd / count) + 1;
                sum += d[i];
            }
            double mul = 1. * toAdd / sum;
            for (int i = 0; i < count; i++) {
                d[i] = (int) Math.round(mul * d[i]);
            }
            StringBuilder sb = new StringBuilder();
            char[] charArray = t.expr.toCharArray();
            for (int index = 0; index < charArray.length; index++) {
                char c = charArray[index];
                if (c == '(') {
                    for (int i = 0; i < d[id[index]]; i++) {
                        sb.append('(');
                    }
                } else if (c == ')') {
                    for (int i = 0; i < d[id[index]]; i++) {
                        sb.append(')');
                    }
                }
                sb.append(c);
            }
            t.expr = sb.toString();
            if (!checkTest(t)) return;
        }
        for (int type = 0; type < 3; type++) {
            Generator g = new Generator(type);
            System.err.println("Testing type: " + type);
            for (int i = 0; i < 30; i++) {
                System.err.println("[" + i + "]");
                if (!checkTest(g.genExpression(1, i / 10 + 3))) return;
            }
        }
    }

    static boolean checkTest(Test test) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        String sType = new String[]{"-i", "-d", "-bi"}[test.type];
        try {
            GenericParser.main(new String[]{sType, test.expr});
        } catch (Throwable e) {
            System.err.println("throwable object has been thrown on test:");
            System.err.println(test.expr);
            e.printStackTrace();
            return false;
        }
        Object[][] ans = test.answer;
        Scanner scanner = new Scanner(new ByteArrayInputStream(out.toByteArray()));
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                if (!scanner.hasNext()) {
                    System.err.println("not enough tokens in output for test:");
                    System.err.println(sType + " " + test.expr);
                    return false;
                }
                String token = scanner.next();
                int xValue = i - 100;
                int yValue = j - 100;
                if (ans[i][j] == null) {
                    if (!token.equals("error")) {
                        System.err.println(String.format("error message expected, x = %d, y = %d, on test:\n", xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                } else if (ans[i][j] instanceof Integer) {
                    int x;
                    try {
                        x = Integer.parseInt(token);
                    } catch (NumberFormatException e) {
                        System.err.println("couldn't parse int: " + token);
                        System.err.println(String.format("x = %d, y = %d\n", xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                    if ((Integer) ans[i][j] != x) {
                        System.err.println(String.format("expected %d, found %d, x = %d, y = %d, on test:", (Integer) ans[i][j], x, xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                } else if (ans[i][j] instanceof Double) {
                    double x;
                    try {
                        x = Double.parseDouble(token);
                    } catch (NumberFormatException e) {
                        System.err.println("couldn't parse double: " + token);
                        System.err.println(String.format("x = %d, y = %d\n", xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                    double value = (Double) ans[i][j];
                    boolean bad = true;
                    if (value == x) {
                        bad = false;
                    } else if (Double.isNaN(value)) {
                        if (Double.isNaN(x)) {
                            bad = false;
                        }
                    } else if (Double.isInfinite(value)) {
                        if (x != value) {
                            bad = false;
                        }
                    } else if (Double.isNaN(x) || Double.isInfinite(x)) {

                    } else if (Math.abs(x - value) / Math.max(1., Math.abs(x)) < 1e-4) {
                        bad = false;
                    }
                    if (bad) {
                        System.err.println(String.format("expected %f, found %f, x = %d, y = %d, on test:", (Double) ans[i][j], x, xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                } else {
                    BigInteger x;
                    try {
                        x = new BigInteger(token);
                    } catch (NumberFormatException e) {
                        System.err.println("couldn't parse BigInteger: " + token);
                        System.err.println(String.format("x = %d, y = %d\n", xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                    if (!ans[i][j].equals(x)) {
                        System.err.println(String.format("expected %d, found %d, x = %d, y = %d, on test:", (BigInteger) ans[i][j], x, xValue, yValue));
                        System.err.println(sType + " " + test.expr);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Generator {

        static Object[][] add(Object[][] x, Object[][] y) {
            Object[][] ret = new Object[x.length][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new Object[x[i].length];
                for (int j = 0; j < x[i].length; j++) {
                    Object a = x[i][j];
                    Object b = y[i][j];
                    if (a == null || b == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        ret[i][j] = (Integer) a + (Integer) b;
                    } else if (a instanceof Double) {
                        ret[i][j] = (Double) a + (Double) b;
                    } else {
                        ret[i][j] = ((BigInteger) a).add((BigInteger) b);
                    }
                }
            }
            return ret;
        }


        static Object[][] subtract(Object[][] x, Object[][] y) {
            Object[][] ret = new Object[x.length][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new Object[x[i].length];
                for (int j = 0; j < x[i].length; j++) {
                    Object a = x[i][j];
                    Object b = y[i][j];
                    if (a == null || b == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        ret[i][j] = (Integer) a - (Integer) b;
                    } else if (a instanceof Double) {
                        ret[i][j] = (Double) a - (Double) b;
                    } else {
                        ret[i][j] = ((BigInteger) a).subtract((BigInteger) b);
                    }
                }
            }
            return ret;
        }

        static Object[][] multiply(Object[][] x, Object[][] y) {
            Object[][] ret = new Object[x.length][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new Object[x[i].length];
                for (int j = 0; j < x[i].length; j++) {
                    Object a = x[i][j];
                    Object b = y[i][j];
                    if (a == null || b == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        ret[i][j] = (Integer) a * (Integer) b;
                    } else if (a instanceof Double) {
                        ret[i][j] = (Double) a * (Double) b;
                    } else {
                        ret[i][j] = ((BigInteger) a).multiply((BigInteger) b);
                    }
                }
            }
            return ret;
        }

        static Object[][] divide(Object[][] x, Object[][] y) {
            Object[][] ret = new Object[x.length][];
            for (int i = 0; i < x.length; i++) {
                ret[i] = new Object[x[i].length];
                for (int j = 0; j < x[i].length; j++) {
                    Object a = x[i][j];
                    Object b = y[i][j];
                    if (a == null || b == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        Integer bInteger = (Integer) b;
                        if (bInteger == 0)
                            ret[i][j] = null;
                        else
                            ret[i][j] = (Integer) a / bInteger;
                    } else if (a instanceof Double) {
                        ret[i][j] = (Double) a / (Double) b;
                    } else {
                        BigInteger val = (BigInteger) b;
                        ret[i][j] = val.signum() == 0 ? null : ((BigInteger) a).divide(val);
                    }
                }
            }
            return ret;
        }


        static Object[][] negate(Object[][] z) {
            Object[][] ret = new Object[z.length][];
            for (int i = 0; i < z.length; i++) {
                ret[i] = new Object[z[i].length];
                for (int j = 0; j < z[i].length; j++) {
                    Object a = z[i][j];
                    if (a == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        ret[i][j] = -(Integer) a;
                    } else if (a instanceof Double) {
                        ret[i][j] = -(Double) a;
                    } else {
                        ret[i][j] = ((BigInteger) a).negate();
                    }
                }
            }
            return ret;

        }

        static Object[][] abs(Object[][] z) {
            Object[][] ret = new Object[z.length][];
            for (int i = 0; i < z.length; i++) {
                ret[i] = new Object[z[i].length];
                for (int j = 0; j < z[i].length; j++) {
                    Object a = z[i][j];
                    if (a == null) {
                        ret[i][j] = null;
                    } else if (a instanceof Integer) {
                        ret[i][j] = Math.abs((Integer) a);
                    } else if (a instanceof Double) {
                        ret[i][j] = Math.abs((Double) a);
                    } else {
                        ret[i][j] = ((BigInteger) a).abs();
                    }
                }
            }
            return ret;
        }

        int type;

        public Generator(int type) {
            this.type = type;
        }

        Test genExpression(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genExpression(depth + 1, coefficient);
                Test t2 = genSummand(depth, coefficient);
                if (RNG.nextBoolean()) {
                    return new Test(t1.expr + " + " + t2.expr, add(t1.answer, t2.answer), type);
                } else {
                    return new Test(t1.expr + " - " + t2.expr, subtract(t1.answer, t2.answer), type);
                }
            } else {
                return genSummand(depth, coefficient);
            }
        }

        Test genSummand(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genSummand(depth + 1, coefficient);
                Test t2 = genFactor(depth, coefficient);
                if (RNG.nextBoolean()) {
                    return new Test(t1.expr + " * " + t2.expr, multiply(t1.answer, t2.answer), type);
                } else {
                    return new Test(t1.expr + " / " + t2.expr, divide(t1.answer, t2.answer), type);
                }
            } else {
                return genFactor(depth, coefficient);
            }
        }

        Test genFactor(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t = genFactor(depth + 1, coefficient);
                return new Test("- " + t.expr, negate(t.answer), type);
            } else {
                return genValue(depth, coefficient);
            }
        }

        Test genValue(int depth, int coefficient) {
            if (makeNewBranch(depth, coefficient)) {
                Test t = genExpression(depth + 1, coefficient);
                if (RNG.nextBoolean()) {
                    return new Test("( " + t.expr + " )", t.answer, type);
                } else {
                    return new Test("abs( " + t.expr + " )", abs(t.answer), type);
                }
            } else if (RNG.nextBoolean()) {
                Object z = type == 0 ? RNG.nextInt() : type == 1 ? (RNG.nextDouble() * Math.pow(10, RNG.nextInt(41) - 20)) : randomBigInteger();
                Object[][] ret = new Object[201][201];
                for (int i = 0; i <= 200; i++) {
                    Arrays.fill(ret[i], z);
                }
                return new Test("" + (z.toString()), ret, type);
            } else {
                int id = RNG.nextInt(2);
                Object[][] ret = new Object[201][201];
                for (int i = 0; i <= 200; i++) {
                    for (int j = 0; j <= 200; j++) {
                        int val = (id == 0 ? i : j) - 100;
                        if (type == 0) {
                            ret[i][j] = val;
                        } else if (type == 1) {
                            ret[i][j] = (double) val;
                        } else {
                            ret[i][j] = BigInteger.valueOf(val);
                        }
                    }
                }
                return new Test("xy".charAt(id) + "", ret, type);
            }
        }

        boolean makeNewBranch(int depth, int coefficient) {
            return RNG.nextInt(depth + coefficient) < coefficient;
        }

        static BigInteger randomBigInteger() {
            StringBuilder sb = new StringBuilder();
            if (RNG.nextBoolean()) sb.append('-');
            int len = RNG.nextInt(50) + 1;
            for (int i = 0; i < len; i++) sb.append(RNG.nextInt(10));
            return new BigInteger(sb.toString());
        }
    }

    static class Test {
        String expr;
        Object[][] answer;
        int type;

        Test(String expr, Object[][] answer, int type) {
            this.expr = expr;
            this.answer = answer;
            this.type = type;
        }
    }

}