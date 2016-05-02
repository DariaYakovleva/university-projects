public class Add extends BinaryOperations {
  
    public Add(Expression3 e1, Expression3 e2) {
       super(e1, e2);      
    }

    protected int getAns(int a, int b) {
        return a + b;
    } 
}