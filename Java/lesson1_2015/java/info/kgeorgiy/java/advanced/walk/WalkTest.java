package info.kgeorgiy.java.advanced.walk;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WalkTest {
    protected static final Path DIR = Paths.get("__Test__Walk__");
    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    protected static final Random random = new Random(23084701432182342L);

    @Rule
    public TestName name = new TestName();

    @Test
    public void test01_oneEmptyFile() throws IOException {
        test(randomFiles(1, 0));
    }

    @Test
    public void test02_tenEmptyFiles() throws IOException {
        test(randomFiles(10, 0));
    }

    @Test
    public void test03_missingFiles() throws IOException {
        final Map<String, Integer> files = randomFiles(3, 0);
        files.put(randomFileName(), 0);
        files.put(randomFileName(), 0);
        files.put(randomFileName(), 0);
        test(files);
    }

    @Test
    public void test04_errorReading() throws IOException {
        final Map<String, Integer> files = randomFiles(3, 0);
        files.put(DIR.toString() + "..", 0);
        files.put(DIR.toString() + "@", 0);
        test(files);
    }

    @Test
    public void test05_smallRandomFiles() throws IOException {
        test(randomFiles(1, 100));
    }

    @Test
    public void test06_mediumRandomFiles() throws IOException {
        test(randomFiles(10, 100));
    }

    @Test
    public void test07_largeRandomFiles() throws IOException {
        test(randomFiles(10, 1_000_000));
    }

    @Test
    public void test08_chineseSupport() throws IOException {
        ALPHABET = "\u8acb\u554f\u4f60\u7684\u7a0b\u5e8f\u652f\u6301\u4e2d\u570b";
        test(randomFiles(10, 1));
    }

    protected void test(final Map<String, Integer> files) {
        test(files.keySet(), files);
    }

    protected void test(final Collection<String> inputs, final Map<String, Integer> files) {
        final Path inputFile = DIR.resolve(name.getMethodName() + ".in");
        final Path outputFile = DIR.resolve(name.getMethodName() + ".out");
        try {
            Files.write(inputFile, generateInput(inputs).getBytes("UTF-8"));
        } catch (final IOException e) {
            throw new AssertionError("Cannot write input file " + inputFile);
        }
        run(inputFile, outputFile);
        try {
            for (final String line : Files.readAllLines(outputFile, Charset.forName("UTF-8"))) {
                final String[] parts = line.split(" ");
                Assert.assertEquals("Invalid line format\n" + line, 2, parts.length);
                Assert.assertTrue("Unexpected file " + parts[1], files.containsKey(parts[1]));
                Assert.assertEquals("Wrong hash", String.format("%08x", files.remove(parts[1])), parts[0]);
            }
        } catch (final IOException e) {
            throw new AssertionError("Cannot write output file " + outputFile);
        }

        Assert.assertTrue("Some files missing: \n    " + String.join("\n    ", files.keySet()), files.isEmpty());
    }

    private void run(final Path inputFile, final Path outputFile) {
        final String className = System.getProperty("cut");
        Assert.assertTrue("Class name not specified", className != null);
        final Method method;
        try {
            method = Class.forName(className).getMethod("main", String[].class);
        } catch (final ClassNotFoundException e) {
            throw new AssertionError("Cannot load class " + className, e);
        } catch (final NoSuchMethodException e) {
            throw new AssertionError("Cannot find method main(String[]) of class " + className, e);
        }
        try {
            method.invoke(null, new Object[]{new String[]{inputFile.toString(), outputFile.toString()}});
        } catch (final IllegalAccessException e) {
            throw new AssertionError("Cannot call main(String[]) of class " + className, e);
        } catch (final InvocationTargetException e) {
            throw new AssertionError("Error thrown", e);
        }
    }

    private String generateInput(final Collection<String> files) {
        final StringWriter stringWriter = new StringWriter();
        final PrintWriter writer = new PrintWriter(stringWriter);
        files.forEach(writer::println);
        writer.close();
        return stringWriter.toString();
    }

    private Map<String, Integer> randomFiles(final int n, final int maxL) throws IOException {
        final Path dir = DIR.resolve(name.getMethodName());
        return randomFiles(n, maxL, dir);
    }

    protected Map<String, Integer> randomFiles(final int n, final int maxL, final Path dir) throws IOException {
        Files.createDirectories(dir);
        final Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            final Path file = dir.resolve(randomFileName());
            final byte[] bytes = new byte[random.nextInt(maxL + 1)];
            random.nextBytes(bytes);
            Files.write(file, bytes);
            result.put(file.toString(), hash(bytes));
        }
        return result;
    }

    protected String randomFileName() {
        final StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 30; j++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    private int hash(final byte[] bytes) {
        int h = 0x811c9dc5;
        for (final byte b : bytes) {
            h = (h * 0x01000193) ^ (b & 0xff);
        }
        return h;
    }
}
