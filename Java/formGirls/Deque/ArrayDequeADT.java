public class ArrayDequeADT { 
    private Object[] elements = new Object[10];
    private int size = 0;
    private int s = 10;
    private int last = 0;
    private int first = 0;


    private static int module(int m, int x) {
        return (x % m + m) % m;
    }

    private static void ensureCapacity(ArrayDequeADT deque, int f, int l) {
        if (f > deque.s) {
            deque.last -= deque.s;
            deque.first -= deque.s;
        }
        if (l < 0) {
            deque.last += deque.s;
            deque.first += deque.s;
        }
        if (deque.s == l - f) {
            Object[] e = new Object[(l - f) * 2]; 
            int num = 0;
            for (int i = f; i < l; i++) {
                e[num] = deque.elements[module(deque.s, i)];
                num++;
            }
            deque.elements = e;
            deque.s = (l - f) * 2;
            deque.first = 0;
            deque.last = num;
        }  
    }

    //pre: -
    //post: Your element has been added at the end of the deque; size++;
    //inv: Order of other elements;
    public static void addLast(ArrayDequeADT deque, Object x) {  
        ensureCapacity(deque, deque.first, deque.last);
        deque.size++;
        deque.last++;
        deque.elements[module(deque.s, deque.last - 1)] = x;
    }

    //pre: -
    //post: Your element has been added at the begining of the deque; size++;
    //inv: Order of other elements;
    public static void addFirst(ArrayDequeADT deque, Object x) {
        ensureCapacity(deque, deque.first, deque.last);
        deque.size++;
        deque.first--;
        deque.elements[module(deque.s, deque.first)] = x;
    }

    //pre: size > 0
    //post: Returned element has been removed from the begining of the deque; size--;
    //inv: Order of other elements;
    public static Object removeFirst(ArrayDequeADT deque) {
        assert deque.size > 0; 
        Object result = deque.elements[module(deque.s, deque.first)];
        deque.elements[module(deque.s, deque.first)] = null;
        deque.first++;
        deque.size--;                                      
        return result;
    }

    
    //pre: size > 0
    //post: Returned element has been removed from the end of the deque; size--;
    //inv: Order of other elements;
    public static Object removeLast(ArrayDequeADT deque) {
        assert deque.size > 0; 
        Object result = deque.elements[module(deque.s, deque.last - 1)];
        deque.elements[module(deque.s, deque.last - 1)] = null;
        deque.last--;
        deque.size--; 
        return result;
    }

               
    //pre: size > 0
    //post: Returned element is on the first place in the deque;
    //inv: Order of all elements; size;           
    public static Object peekFirst(ArrayDequeADT deque) {
        assert deque.size > 0;
        return deque.elements[module(deque.s, deque.first)];
    }

    //pre: size > 0
    //post: Returned element is on the last place in the deque;
    //inv: Oreder of all elements; size;
    public static Object peekLast(ArrayDequeADT deque) {
        assert deque.size > 0;
        return deque.elements[module(deque.s, deque.last - 1)];
    }

    //pre: -
    //post: Returned element is size of the deque;
    //inv: Oreder of all elements; size;
    public static int size(ArrayDequeADT deque) {
        return deque.size;
    }

    //pre: -
    //post: Returns "true" if deque is empty and "false" if it's not;
    //inv: Oreder of all elements; size;
    public static boolean isEmpty(ArrayDequeADT deque) {
        return deque.size == 0;
    } 
 
}
