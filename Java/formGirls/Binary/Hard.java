import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @version $$Id$$
 */
public class Hard {
    private static int total = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        Random random = new Random(3850973259783712434L);

        for (int size : new int[]{5, 4, 2, 1, 0, 10, 100}) {
            int[] a = new int[size];
            String[] as = new String[size + 1];
            for (int max : new int[]{5, 4, 2, 1, 0, 10, 100, Integer.MAX_VALUE / 2}) {
                for (int i = 0; i < size; i++) {
                    a[i] = random.nextInt(max * 2 + 1) - max;
                }
                Arrays.sort(a);
                for (int i = 0; i < size; i++) {
                    as[i + 1] = Integer.toString(a[i]);
                }
                for (int i = 0; i < size; i++) {
                    test(a[i], a, as);
                    if (i != 0) {
                        test((a[i - 1] + a[i]) / 2, a, as);
                    }
                }
                test(Integer.MIN_VALUE, a, as);
                test(Integer.MAX_VALUE, a, as);
            }
        }
        System.err.println(String.format("Test run: %d, passed; %d, failed: %d", total, passed, total - passed));
    }

    private static void test(int x, int[] a, String[] as) {
        total++;
        try {
            as[0] = Integer.toString(x);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            BinarySearch.main(as);
            Scanner in = new Scanner(new ByteArrayInputStream(out.toByteArray()));
            int[] expected = solve(a, x);
            int expectedI = expected[0];
            int expectedL = expected[1];
            int actualI = in.nextInt();
            int actualL = in.nextInt();
            if (actualI != expectedI || actualL != expectedL) {
                throw new AssertionError(String.format("Expected i=%d, l=%d, found i=%d, l=%d", expectedI, expectedL, actualI, actualL));
            }
            passed++;
        } catch (Throwable t) {
            System.err.println("Error on input " + Arrays.toString(as));
            t.printStackTrace();
        }
    }

    private static int[] solve(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (x < a[i]) {
                return new int[]{i, 0};
            }
            if (x == a[i]) {
                int j = i;
                while (j < a.length && a[j] == x) {
                    j++;
                }
                return new int[]{i, j - i};
            }
        }
        return new int[]{a.length, 0};
    }
}