import java.util.LinkedList;
import java.util.Random;

/**
* @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
* @version $$Id$$
*/
public class ArrayDequeTest {
    public static void main(String[] args) {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        checkAssert();
        System.out.println("Testing ArrayDequeSingleton");
        testSingleDequeue(new ArrayDequeSingletonAdapter());
        System.out.println("OK");

        System.out.println("Testing ArrayDequeADT");
        testSingleDequeue(new ArrayDequeADTAdapter());
        testMultiQueue(new ArrayDequeADTAdapter(), new ArrayDequeADTAdapter());
        System.out.println("OK");

        System.out.println("Testing ArrayDeque");
        testSingleDequeue(new ArrayDequeAdapter());
        testMultiQueue(new ArrayDequeAdapter(), new ArrayDequeAdapter());
        System.out.println("OK");
    }

    private static void checkAssert() {
        boolean assertsEnabled = false;
        assert assertsEnabled = true;
        if (!assertsEnabled) {
            throw new AssertionError("You should enable assertions by running 'java -ea ArrayDequeTest'");
        }
    }

    private static void testSingleDequeue(Dequeue dequeue) {
        testJustCreated(dequeue);
        testSingleton(dequeue);
        testFIFOOrder(dequeue);
        testLIFOOrder(dequeue);
        testBackFIFOOrder(dequeue);
        testBackLIFOOrder(dequeue);
        testPreconditions(dequeue);
        for (int i : new int[]{10, 100, 10_000, 100_000}) {
            testCapacity(dequeue, i);
        }
        for (int i : new int[]{10, 100, 10_000, 100_000}) {
            testRandom(dequeue, i);
        }
    }

    private static void testRandom(Dequeue dequeue, int operations) {
        final Random random = new Random(2457023587203587234L);
        final LinkedList list = new LinkedList();
        System.out.println(" Testing random operations (operations = " + operations + ")");
        for (int i = 0; i < operations; i++) {
            randomOp(dequeue, list, random);
        }
        while (!list.isEmpty()) {
            assertEquals(dequeue.removeFirst(), list.removeFirst(), "Dequeue");
        }
    }

    private static void randomOp(Dequeue dequeue, LinkedList list, Random random) {
        assertEquals(dequeue.isEmpty(), list.isEmpty(), "isEmpty");
        assertEquals(dequeue.size(), list.size(), "size");
        if (!list.isEmpty()) {
            assertEquals(dequeue.peekFirst(), list.peekFirst(), "peekFirst");
            assertEquals(dequeue.peekLast(), list.peekLast(), "peekLast");
        }
        if (list.isEmpty() || random.nextBoolean()) {
            Object element = random.nextInt();
            if (random.nextBoolean()) {
                dequeue.addLast(element);
                list.addLast(element);
            } else {
                dequeue.addFirst(element);
                list.addFirst(element);
            }
        } else {
            if (random.nextBoolean()) {
                assertEquals(dequeue.removeFirst(), list.removeFirst(), "removeFirst");
            } else {
                assertEquals(dequeue.removeLast(), list.removeLast(), "removeLast");
            }
        }
    }

    private static void testCapacity(Dequeue dequeue, int size) {
        System.out.println(" Testing capacity (size = " + size + ")");
        for (int i = 0; i < size; i++) {
            assertEquals(dequeue.size(), i, "Dequeue size");
            dequeue.addLast("hello");
        }
        for (int i = 0; i < size; i++) {
            assertEquals(dequeue.size(), size - i, "Dequeue size");
            assertEquals(dequeue.peekFirst(), "hello", "Illegal removeFirst content");
            assertEquals(dequeue.removeFirst(), "hello", "Illegal removeFirst content");
        }
    }

    private static void testPreconditions(Dequeue dequeue) {
        System.out.println(" Testing removeFirst methods preconditions");
        testPeekFirstPrecondition(dequeue);
        testPeekLastPrecondition(dequeue);
        testRemoveFirstPrecondition(dequeue);
        testRemoveLastPrecondition(dequeue);
    }

    private static void testPeekFirstPrecondition(Dequeue dequeue) {
        boolean ok = false;
        try {
            dequeue.peekFirst();
        } catch (AssertionError e) {
            ok = true;
        }
        assert ok : "Precondition of peekFirst() should be checked";
    }

    private static void testPeekLastPrecondition(Dequeue dequeue) {
        boolean ok = false;
        try {
            dequeue.peekLast();
        } catch (AssertionError e) {
            ok = true;
        }
        assert ok : "Precondition of peekLast() should be checked";
    }

    private static void testRemoveFirstPrecondition(Dequeue dequeue) {
        boolean ok = false;
        try {
            dequeue.removeFirst();
        } catch (AssertionError e) {
            ok = true;
        }
        assert ok : "Precondition of removeFirst() should be checked";
    }

    private static void testRemoveLastPrecondition(Dequeue dequeue) {
        boolean ok = false;
        try {
            dequeue.removeLast();
        } catch (AssertionError e) {
            ok = true;
        }
        assert ok : "Precondition of removeLast() should be checked";
    }

    private static void testFIFOOrder(Dequeue dequeue) {
        System.out.println(" Testing removeFirst element order");
        dequeue.addLast("a");
        dequeue.addLast("b");
        assertEquals(dequeue.removeFirst(), "a", "removeFirst must return elements in the order of addition (FIFO)");
        assertEquals(dequeue.removeFirst(), "b", "removeFirst must return elements in the order of addition (FIFO)");
        assertTrue(dequeue.isEmpty(), "Dequeue should be empty");
    }

    private static void testLIFOOrder(Dequeue dequeue) {
        System.out.println(" Testing removeFirst element order");
        dequeue.addFirst("a");
        dequeue.addFirst("b");
        assertEquals(dequeue.removeFirst(), "b", "removeFirst must return elements in the order of addition (LIFO)");
        assertEquals(dequeue.removeFirst(), "a", "removeFirst must return elements in the order of addition (LIFO)");
        assertTrue(dequeue.isEmpty(), "Dequeue should be empty");
    }

    private static void testBackFIFOOrder(Dequeue dequeue) {
        System.out.println(" Testing removeFirst element order");
        dequeue.addFirst("a");
        dequeue.addFirst("b");
        assertEquals(dequeue.removeLast(), "a", "removeFirst must return elements in the order of addition (FIFO)");
        assertEquals(dequeue.removeLast(), "b", "removeFirst must return elements in the order of addition (FIFO)");
        assertTrue(dequeue.isEmpty(), "Dequeue should be empty");
    }

    private static void testBackLIFOOrder(Dequeue dequeue) {
        System.out.println(" Testing removeFirst element order");
        dequeue.addLast("a");
        dequeue.addLast("b");
        assertEquals(dequeue.removeLast(), "b", "removeFirst must return elements in the order of addition (LIFO)");
        assertEquals(dequeue.removeLast(), "a", "removeFirst must return elements in the order of addition (LIFO)");
        assertTrue(dequeue.isEmpty(), "Dequeue should be empty");
    }

    private static void testSingleton(Dequeue dequeue) {
        System.out.println(" Testing singleton dequeue (deque containing single element)");
        dequeue.addLast("a");
        assertTrue(!dequeue.isEmpty(), "Singleton deque must be non-empty");
        assertEquals(dequeue.size(), 1, "Singleton deque must have size 1");
        assertEquals(dequeue.peekFirst(), "a", "peekFirst() for singleton removeFirst should return sole element");
        assertEquals(dequeue.peekLast(), "a", "peekLast() for singleton removeFirst should return sole element");
        assertEquals(dequeue.removeFirst(), "a", "removeFirst() for singleton removeFirst should return sole element");
        assertTrue(dequeue.isEmpty(), "Singleton removeFirst should be empty after removeFirst()");
        assertTrue(dequeue.size() == 0, "Singleton removeFirst should have zero size after removeFirst");
    }

    private static void testJustCreated(Dequeue dequeue) {
        System.out.println(" Testing just created removeFirst");
        assertTrue(dequeue.isEmpty(), "Just created removeFirst should be empty");
        assertTrue(dequeue.size() == 0, "Just created removeFirst should have zero size");
    }

    private static void testMultiQueue(Dequeue dequeue1, Dequeue dequeue2) {
        System.out.println(" Testing two queues at once");
        final Random random = new Random(2457023587203587234L);
        final LinkedList list1 = new LinkedList();
        final LinkedList list2 = new LinkedList();
        for (int i = 0; i < 100_000; i++) {
            if (random.nextBoolean()) {
                randomOp(dequeue1, list1, random);
            } else {
                randomOp(dequeue2, list2, random);
            }
        }
    }

    private static void assertTrue(boolean condition, String message) {
        assert condition : message;
    }

    private static void assertEquals(Object actual, Object expected, String message) {
        assertTrue(
                actual == null && expected == null || actual != null && actual.equals(expected),
                String.format("%s: expected %s, found %s", message, expected, actual)
        );
    }

    private static class ArrayDequeSingletonAdapter implements Dequeue {
        public void addFirst(Object element) { ArrayDequeSingleton.addFirst(element); }
        public void addLast(Object element) { ArrayDequeSingleton.addLast(element); }
        public Object removeFirst() { return ArrayDequeSingleton.removeFirst(); }
        public Object removeLast() { return ArrayDequeSingleton.removeLast(); }
        public Object peekFirst() { return ArrayDequeSingleton.peekFirst(); }
        public Object peekLast() { return ArrayDequeSingleton.peekLast(); }
        public boolean isEmpty() { return ArrayDequeSingleton.isEmpty(); }
        public int size() { return ArrayDequeSingleton.size(); }
    }

    private static class ArrayDequeADTAdapter implements Dequeue {
        private final ArrayDequeADT deque = new ArrayDequeADT();
        public void addFirst(Object element) { ArrayDequeADT.addFirst(deque, element); }
        public void addLast(Object element) { ArrayDequeADT.addLast(deque, element); }
        public Object removeFirst() { return ArrayDequeADT.removeFirst(deque); }
        public Object removeLast() { return ArrayDequeADT.removeLast(deque); }
        public Object peekFirst() { return ArrayDequeADT.peekFirst(deque); }
        public Object peekLast() { return ArrayDequeADT.peekLast(deque); }
        public boolean isEmpty() { return ArrayDequeADT.isEmpty(deque); }
        public int size() { return ArrayDequeADT.size(deque); }
    }

    private static class ArrayDequeAdapter implements Dequeue {
        private final ArrayDeque deque = new ArrayDeque();
        public void addFirst(Object element) { deque.addFirst(element); }
        public void addLast(Object element) { deque.addLast(element); }
        public Object removeFirst() { return deque.removeFirst(); }
        public Object removeLast() { return deque.removeLast(); }
        public Object peekFirst() { return deque.peekFirst(); }
        public Object peekLast() { return deque.peekLast(); }
        public boolean isEmpty() { return deque.isEmpty(); }
        public int size() { return deque.size(); }
    }

    private interface Dequeue {
        void addFirst(Object element);
        void addLast(Object element);
        Object removeFirst();
        Object removeLast();
        Object peekFirst();
        Object peekLast();
        boolean isEmpty();
        int size();
    }
}