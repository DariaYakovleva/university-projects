import java.util.Random;

/**
* @author Niyaz Nigmatullin
*/

public class ParserTest {

    static final Random RNG = new Random(58L);

    public static void main(String[] args) {
        checkAssert();
        testExpression("10", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return 10;
            }
        });
        testExpression("x", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return x;
            }
        });
        testExpression("y", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return y;
            }
        });
        testExpression("z", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return z;
            }
        });
        testExpression("x+2", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return x + 2;
            }
        });
        testExpression("2-y", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return 2 - y;
            }
        });
        testExpression(" 3* z ", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return 3 * z;
            }
        });
        testExpression("x/ - 2", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return -x / 2;
            }
        });
        testExpression("x*y+(z-1 )/10", new Expression3() {
            public int evaluate(int x, int y, int z) {
                return x * y + (z - 1) / 10;
            }
        });
        testExpression("-(-(-\t\t-5 + 16 *x*y) + 1 * z) -(((-11)))", new Expression3() {
            @Override
            public int evaluate(int x, int y, int z) {
                return -(-(5 + 16 * x * y) + z) + 11;
            }
        });
        testExpression("" + Integer.MIN_VALUE, new Expression3() {
            public int evaluate(int x, int y, int z) {
                return Integer.MIN_VALUE;
            }
        });
        testExpression("x--y--z", new Expression3() {
            @Override
            public int evaluate(int x, int y, int z) {
                return x + y + z;
            }
        });
        testExpression("((2+2))-0/(--2)*555", new Expression3() {
            @Override
            public int evaluate(int x, int y, int z) {
                return 4;
            }
        });
        testExpression("x-x+y-y+z-(z)", new Expression3() {
            @Override
            public int evaluate(int x, int y, int z) {
                return 0;
            }
        });
        {
            String s = "";
            for (int i = 0; i < 1000; i++) s += "(";
            s += "x + y + (-10*-z)";
            for (int i = 0; i < 1000; i++) s += ")";
            testExpression(s, new Expression3() {
                @Override
                public int evaluate(int x, int y, int z) {
                    return x + y + 10 * z;
                }
            });
        }
        testExpression("2*" + Integer.MAX_VALUE, new Expression3() {
            @Override
            public int evaluate(int x, int y, int z) {
                return 2 * Integer.MAX_VALUE;
            }
        });
        System.out.println("Testing random tests #1");
        final int TESTS1 = 2000;
        for (int it = 0; it < TESTS1; it++) {
            if (it % 100 == 0) {
                System.out.println("Completed " + it + " out of " + TESTS1);
            }
            genTests1(it / 5 + 2);
        }
        System.out.println("Testing random tests #2");
        final int TESTS2 = 777;
        for (int it = 0; it < TESTS2; it++) {
            if (it % 100 == 0) {
                System.out.println("Completed " + it + " out of " + TESTS2);
            }
            genTests2(it / 50 + 1);
        }
        System.out.println("OK");
    }

    private static void genTests1(int depth) {
        int x = RNG.nextInt();
        int y = RNG.nextInt();
        int z = RNG.nextInt();
        Test test = generate(new int[]{x, y, z}, depth);
        tryTest(x, y, z, test);
    }

    private static void genTests2(int coefficient) {
        int x = RNG.nextInt();
        int y = RNG.nextInt();
        int z = RNG.nextInt();
        Test test = Generator.genExpression(1, coefficient, new int[]{x, y, z});
        tryTest(x, y, z, test);
    }

    private static void tryTest(int x, int y, int z, Test test) {
        try {
            try {
                int answer = ExpressionParser.parse(test.expr).evaluate(x, y, z);
                assertTrue(test.answer != null, "division by zero error expected");
                assertEquals(String.format("f(%d, %d, %d)", x, y, z), answer, test.answer);
            } catch (ArithmeticException e) {
                assertTrue(test.answer == null, "no division by zero in this expression");
            }
        } catch (Throwable e) {
            System.out.println("Failed test: " + test.expr);
            throw e;
        }
    }

    static Test generate(int[] vars, int depth) {
        if (depth == 0) {
            if (RNG.nextBoolean()) {
                int id = RNG.nextInt(3);
                return new Test("xyz".charAt(id) + "", vars[id]);
            } else {
                int val = RNG.nextInt();
                return new Test(val + "", val);
            }
        }
        int operator = RNG.nextInt(6);
        if (operator == 5) {
            Test t1 = generate(vars, RNG.nextInt(depth));
            return new Test("(" + t1.expr + ")", t1.answer);
        } else if (operator == 0) {
            Test t1 = generate(vars, RNG.nextInt(depth));
            return new Test("-(" + t1.expr + ")", t1.answer == null ? null : -t1.answer);
        } else {
            Test t1 = generate(vars, RNG.nextInt(depth));
            Test t2 = generate(vars, RNG.nextInt(depth));
            if (operator == 1) {
                return new Test("(" + t1.expr + ") + (" + t2.expr + ")",
                        t1.answer == null || t2.answer == null ? null : t1.answer + t2.answer);
            } else if (operator == 2) {
                return new Test("(" + t1.expr + ") - (" + t2.expr + ")",
                        t1.answer == null || t2.answer == null ? null : t1.answer - t2.answer);
            } else if (operator == 3) {
                return new Test("(" + t1.expr + ") * (" + t2.expr + ")",
                        t1.answer == null || t2.answer == null ? null : t1.answer * t2.answer);
            } else {
                return new Test("(" + t1.expr + ") / (" + t2.expr + ")",
                        t1.answer == null || t2.answer == null || t2.answer.intValue() == 0 ? null : t1.answer / t2.answer);
            }
        }
    }

    static class Generator {
        static Test genExpression(int depth, int coefficient, int[] vars) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genExpression(depth + 1, coefficient, vars);
                Test t2 = genSummand(depth, coefficient, vars);
                if (RNG.nextBoolean()) {
                    return new Test(t1.expr + " + " + t2.expr,
                            t1.answer == null || t2.answer == null ? null : t1.answer + t2.answer);
                } else {
                    return new Test(t1.expr + " - " + t2.expr,
                            t1.answer == null || t2.answer == null ? null : t1.answer - t2.answer);
                }
            } else {
                return genSummand(depth, coefficient, vars);
            }
        }

        static Test genSummand(int depth, int coefficient, int[] vars) {
            if (makeNewBranch(depth, coefficient)) {
                Test t1 = genSummand(depth + 1, coefficient, vars);
                Test t2 = genFactor(depth, coefficient, vars);
                if (RNG.nextBoolean()) {
                    return new Test(t1.expr + " * " + t2.expr,
                            t1.answer == null || t2.answer == null ? null : t1.answer * t2.answer);
                } else {
                    return new Test(t1.expr + " / " + t2.expr,
                            t1.answer == null || t2.answer == null || t2.answer.intValue() == 0 ? null : t1.answer / t2.answer);
                }
            } else {
                return genFactor(depth, coefficient, vars);
            }
        }

        static Test genFactor(int depth, int coefficient, int[] vars) {
            if (makeNewBranch(depth, coefficient)) {
                Test t = genFactor(depth + 1, coefficient, vars);
                if (RNG.nextBoolean()) {
                    return new Test("- " + t.expr, t.answer == null ? null : -t.answer);
                } else {
                    return new Test("~ " + t.expr, t.answer == null ? null : ~t.answer);
                }
            } else {
                return genValue(depth, coefficient, vars);
            }
        }

        static Test genValue(int depth, int coefficient, int[] vars) {
            if (makeNewBranch(depth, coefficient)) {
                Test t = genExpression(depth + 1, coefficient, vars);
                if (RNG.nextBoolean()) {
                    return new Test("( " + t.expr + " )", t.answer == null ? null : t.answer);
                } else {
                    return new Test("abs( " + t.expr + " )", t.answer == null ? null : Math.abs(t.answer));
                }
            } else if (RNG.nextBoolean()) {
                int value = RNG.nextInt();
                return new Test("" + value, value);
            } else {
                int id = RNG.nextInt(3);
                return new Test("xyz".charAt(id) + "", vars[id]);
            }
        }

        static boolean makeNewBranch(int depth, int coefficient) {
            return RNG.nextInt(depth + coefficient) < coefficient;
        }
    }

    static class Test {
        String expr;
        Integer answer;

        Test(String expr, Integer answer) {
            this.expr = expr;
            this.answer = answer;
        }
    }

    private static void testExpression(String description, Expression3 expected) {
        System.out.println("Testing: " + description);
        Expression3 actual = ExpressionParser.parse(description);
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

    private static void assertEquals(String message, int actual, int expected) {
        assertTrue(actual == expected, String.format("%s: Expected %d, found %d", message, expected, actual));
    }

    private static void checkAssert() {
        boolean assertsEnabled = false;
        assert assertsEnabled = true;
        if (!assertsEnabled) {
            throw new AssertionError("You should enable assertions by running 'java -ea ParserTestHard'");
        }
    }
}

