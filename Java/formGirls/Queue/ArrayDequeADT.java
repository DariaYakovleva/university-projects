public class ArrayDequeADT {                           

    private static Object[] elements = new Object[2];
    private static int tail = 0;
    private static int head = 0;

// pre: Object x
// post: queue = queue' + x
    public static void addLast(ArrayDequeADT deque, Object x) {
        ensureCapacityTail(deque, deque.tail + 1);
        deque.elements[deque.tail] = x;  
        deque.tail++;                        
    } 

// pre: Object x
// post: queue = queue' + x
    public static void addFirst(ArrayDequeADT deque, Object x) {
        ensureCapacityHead(deque, deque.head - 1);
        deque.elements[deque.head - 1] = x;  
        deque.head--;                        
    } 
                                                                                        
// pre: capacity = tail + 1
// post: if (size > elements.length) {size = (elements.length + 1) * 2} else {const}    
    private static void ensureCapacityTail(ArrayDequeADT deque, int capacity) {
        if ((capacity == deque.head) || ((deque.elements.length < capacity) && (deque.head == 0))) {
            Object[] e = new Object[(deque.elements.length + 1) * 2];
            int t = 0;
            int i = 0;
            for (i = deque.head; i < deque.elements.length; i++) {
                e[t] = deque.elements[i];  
                t++;
            }       
	    for (i = 0; i < deque.head; i++) {
                e[t] = deque.elements[i];  
                t++;
            }
            deque.elements = e;
            deque.head = 0;
            deque.tail = t;
        } else {
            if (deque.elements.length < capacity) {
                deque.tail = 0;  
            }      
        }           
    }

// pre: capacity = head - 1
// post: if (size > elements.length) {size = (elements.length + 1) * 2} else {const}     
    private static void ensureCapacityHead(ArrayDequeADT deque, int capacity) {
        if ((capacity == deque.tail) || ((capacity < 0) && (deque.tail == deque.elements.length))) {
            Object[] e = new Object[(deque.elements.length + 1) * 2];
            int t = 0;
            int i = 0;
            for (i = deque.head; i < deque.elements.length; i++) {
                e[t] = deque.elements[i];  
                t++;
            }       
	    for (i = 0; i < deque.head; i++) {
                e[t] = deque.elements[i];  
                t++;
            }
            deque.elements = e;
            deque.head = 0;
            deque.tail = t;
        } else {
            if (capacity < 0) {
                deque.head = deque.elements.length - 1;  
            }      
        }           
    }
                      
// pre: !isEmpty
// post: queue = queue - queue[head] && head++
// result = queue[head]  
    public static Object removeFirst(ArrayDequeADT deque) {
        assert (deque.tail != deque.head);     //запускать с -ea
        Object result = deque.elements[deque.head];
        deque.elements[deque.head] = null;
        if (deque.head + 1 < deque.elements.length) {
            deque.head++;
        } else {
            deque.head = 0;
        }
        return result; 
    }

// pre: !isEmpty
// post: queue = queue - queue[tail] && tail++
// result = queue[tail]  
    public static Object removeLast(ArrayDequeADT deque) {
        assert (deque.tail != deque.head);     //запускать с -ea
        Object result = deque.elements[deque.tail - 1];
        elements[deque.tail - 1] = null;
        if (deque.tail - 1 > 0) {
            deque.tail--;
        } else {
            deque.tail = elements.length;
        }
        return result; 
    }


// pre: !isEmpty
// post: const
// result = queue[head]        
    public static Object peekFirst(ArrayDequeADT deque) {
        assert (deque.head != deque.tail);
        return deque.elements[deque.head];
    }


// pre: !isEmpty
// post: const
// result = queue[tail]        
    public static Object peekLast(ArrayDequeADT deque) {
        assert (deque.head != deque.tail);
        return deque.elements[deque.tail - 1];
    }


// pre: -
// post: const
// result = queue.length
    public static int size(ArrayDequeADT deque) {
        if (deque.tail > deque.head) {
            return deque.tail - deque.head;
        } else {
            return deque.elements.length - (deque.head - deque.tail);
        }
    }


// pre: -
// post: const 
// if (queue.length == 0) {result = true} else {result = false}  
    public static boolean isEmpty(ArrayDequeADT deque) {
        return deque.tail == deque.head;
    } 
 
}                          
                                   
