public class Power<T extends MyNumber<T>> extends BinaryOperations<T> {
  
    public Power(Expression3<T> e1, Expression3<T> e2) {
       super(e1, e2);      
    }

    protected T getAns(T a, T b) throws Exception3 {
    	return (T) a.pow(b);
    } 
}