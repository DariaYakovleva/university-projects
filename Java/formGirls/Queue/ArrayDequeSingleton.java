public class ArrayDequeSingleton {                           

    private static Object[] elements = new Object[2];
    private static int tail = 0;
    private static int head = 0;

// pre: Object x
// post: queue = queue' + x
    public static void addLast(Object x) {
        ensureCapacityTail(tail + 1);
        elements[tail] = x;  
        tail++;                        
    } 

// pre: Object x
// post: queue = queue' + x
    public static void addFirst(Object x) {
        ensureCapacityHead(head - 1);
        elements[head - 1] = x;  
        head--;                        
    } 
                                                                                        
// pre: capacity = tail + 1
// post: if (size > elements.length) {size = (elements.length + 1) * 2} else {const}    
    private static void ensureCapacityTail(int capacity) {
        if ((capacity == head) || ((elements.length < capacity) && (head == 0))) {
            Object[] e = new Object[(elements.length + 1) * 2];
            int t = 0;
            int i = 0;
            for (i = head; i < elements.length; i++) {
                e[t] = elements[i];  
                t++;
            }       
	    for (i = 0; i < head; i++) {
                e[t] = elements[i];  
                t++;
            }
            elements = e;
            head = 0;
            tail = t;
        } else {
            if (elements.length < capacity) {
                tail = 0;  
            }      
        }           
    }

// pre: capacity = head - 1
// post: if (size > elements.length) {size = (elements.length + 1) * 2} else {const}     
    private static void ensureCapacityHead(int capacity) {
        if ((capacity == tail) || ((capacity < 0) && (tail == elements.length))) {
            Object[] e = new Object[(elements.length + 1) * 2];
            int t = 0;
            int i = 0;
            for (i = head; i < elements.length; i++) {
                e[t] = elements[i];  
                t++;
            }       
	    for (i = 0; i < head; i++) {
                e[t] = elements[i];  
                t++;
            }
            elements = e;
            head = 0;
            tail = t;
        } else {
            if (capacity < 0) {
                head = elements.length - 1;  
            }      
        }           
    }
                      
// pre: !isEmpty
// post: queue = queue - queue[head] && head++
// result = queue[head]  
    public static Object removeFirst() {
        assert (tail != head);     //запускать с -ea
        Object result = elements[head];
        elements[head] = null;
        if (head + 1 < elements.length) {
            head++;
        } else {
            head = 0;
        }
        return result; 
    }

// pre: !isEmpty
// post: queue = queue - queue[tail] && tail++
// result = queue[tail]  
    public static Object removeLast() {
        assert (tail != head);     //запускать с -ea
        Object result = elements[tail - 1];
        elements[tail - 1] = null;
        if (tail - 1 > 0) {
            tail--;
        } else {
            tail = elements.length;
        }
        return result; 
    }


// pre: !isEmpty
// post: const
// result = queue[head]        
    public static Object peekFirst() {
        assert (head != tail);
        return elements[head];
    }


// pre: !isEmpty
// post: const
// result = queue[tail]        
    public static Object peekLast() {
        assert (head != tail);
        return elements[tail - 1];
    }


// pre: -
// post: const
// result = queue.length
    public static int size() {
        if (tail > head) {
            return tail - head;
        } else {
            return elements.length - (head - tail);
        }
    }


// pre: -
// post: const 
// if (queue.length == 0) {result = true} else {result = false}  
    public static boolean isEmpty() {
        return tail == head;
    } 
 
}                          