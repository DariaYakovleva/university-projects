public class Divide<T extends MyNumber<T>> extends BinaryOperations<T> {
  
    public Divide(Expression3<T> e1, Expression3<T> e2) {
       super(e1, e2);      
    }
	protected T getAns(T a, T b) throws Exception3 {
		return (T) a.divide(b);
	}
}