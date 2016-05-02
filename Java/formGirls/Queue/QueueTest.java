public class QueueTest {
    public static void main(String[] args) {          
        //ArrayQueueSingleton stack = new ArrayQueueSingleton();    
        for (int i = 0; i < 8; i++) {
            ArrayDequeSingleton.addFirst(i);
        }                    
        for (int i = 0; i < 4; i++) {
            ArrayDequeSingleton.addLast(i);
        }                    
        for (int i = 0; i < 5; i++) {
            System.out.println(ArrayDequeSingleton.removeLast());
        }                   
   
        while (!ArrayDequeSingleton.isEmpty()) {            
            System.out.println(ArrayDequeSingleton.removeFirst());
        }
    }
}