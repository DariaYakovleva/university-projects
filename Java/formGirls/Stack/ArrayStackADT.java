public class ArrayStackADT {
    //внутренние части - private                        
    //private static Object[] elements = new Object[10];
    private Object[] elements = new Object[10];
    private int size = 0;
                      
    public static void push(ArrayStackADT stack, Object o) {
        ensureCapacity(stack, stack.size + 1);
        stack.elements[stack.size++] = o;     //изначально внутри NULL'ы
    } 

    private static void ensureCapacity(ArrayStackADT stack, int capacity) {
        if (stack.elements.length < capacity) {
            Object[] e = new Object[capacity * 2];
            for (int i = 0; i < stack.size; i++) {
                e[i] = stack.elements[i];  
            }
            stack.elements = e;
        }       
    }

    public static Object pop(ArrayStackADT stack) {
        assert stack.size > 0;     //запускать с -ea
        Object result = stack.elements[stack.size - 1];
        stack.elements[stack.size - 1] = null;
        stack.size--;
        return result;
        
    }
     
    public static Object peek(ArrayStackADT stack) {
        assert stack.size > 0;
        return stack.elements[stack.size - 1];
    }

    public static int size(ArrayStackADT stack) {
        return stack.size;
    }

    public static boolean isEmpty(ArrayStackADT stack) {
        return stack.size == 0;
    } 
 
}
