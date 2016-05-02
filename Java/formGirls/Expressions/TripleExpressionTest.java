/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @version $$Id$$
 */
public class TripleExpressionTest {
    public static void main(String[] args) {
        checkAssert();
        testExpression("10", new Const(10), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return 10;
            }
        });
        testExpression("x", new Variable("x"), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return x;
            }
        });
        testExpression("y", new Variable("y"), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return y;
            }
        });
        testExpression("z", new Variable("z"), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return z;
            }
        });
        testExpression("x+2", new Add(new Variable("x"), new Const(2)), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return x + 2;
            }
        });
        testExpression("2-y", new Subtract(new Const(2), new Variable("y")), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return 2 - y;
            }
        });
        testExpression("3*z", new Multiply(new Const(3), new Variable("z")), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return 3*z;
            }
        });
        testExpression("x/-2", new Divide(new Variable("x"), new Const(-2)), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return -x / 2;
            }
        });
        testExpression("x*y+(z-1)/10", new Add(
                new Multiply(new Variable("x"), new Variable("y")),
                new Divide(new Subtract(new Variable("z"), new Const(1)), new Const(10))), new Expression3() {
            public double evaluate(double x, double y, double z) {
                return x * y + (z - 1) / 10;
            }
        });
        System.out.println("OK");
    }

    private static void testExpression(String description, Expression3 actual, Expression3 expected) {
        System.out.println("Testing " + description);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    assertEquals(String.format("f(%d, %d, %d)", i, j, k), actual.evaluate(i, j, k), expected.evaluate(i, j, k));
                }
            }
        }
    }

    private static void assertTrue(boolean condition, String message) {
        assert condition : message;
    }

    private static void assertEquals(String message, double actual, double expected) {
        assertTrue(Math.abs(actual - expected) < 1e-9, String.format("%s: Expected %f, found %f", message, expected, actual));
    }

    private static void checkAssert() {
        boolean assertsEnabled = false;
        assert assertsEnabled = true;
        if (!assertsEnabled) {
            throw new AssertionError("You should enable assertions by running 'java -ea TripleExpressionTest'");
        }
    }
}
