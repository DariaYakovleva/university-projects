public class DequeTest {
    public static int getMax(ArrayDeque d) {
        int k, result;
        result = (int)d.removeFirst();
        d.addLast(result);
        for (int i = 0; i < d.size() - 1; i++) {
            k = (int)d.removeFirst();
            if (k > result) {
                result = k;
            }
            d.addLast(k);
        }
        return result;
    } 


    public static void main(String[] args) {          
        ArrayDeque d1 = new ArrayDeque();          
  //      System.out.println(ArrayDequeADT.isEmpty(d1)); 
   //     System.out.println(ArrayDequeADT.isEmpty(d2)); 
          
        for (int i = 0; i < 8; i++) {
          //  d1.addFirst(i);
        } 
 
        int x = getMax(d1);                                                 
        System.out.println(x); 
//        System.out.println(ArrayDequeADT.size(d2)); 

//        System.out.println(ArrayDequeADT.peekLast(d1)); 
//        System.out.println(ArrayDequeADT.peekLast(d2)); 
  
   
    }
}