public class Multiply<T extends MyNumber<T>> extends BinaryOperations<T> {
  
    public Multiply(Expression3<T> e1, Expression3<T> e2) {
       super(e1, e2);      
    }

    protected T getAns(T a, T b) {
        return (T) a.mul(b);
    } 
}