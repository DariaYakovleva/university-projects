public class StackTest {
    public static void main(String[] args) {          
        Stack stack = new(ArrayStackADT());
        Stack stack2 = new(LinkedStack());
        for (int i = 0; i < 5; i++) {
            Stack.push(stack, i);
        }         
	for (int i = 10; i < 15; i++) {
            Stack.push(stack2, i);
        }                   
   
        while (!Stack.isEmpty(stack)) {            
            System.out.println(Stack.pop(stack));
            System.out.println(Stack.pop(stack2));
        }
    }
}