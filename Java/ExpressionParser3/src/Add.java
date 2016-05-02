public class Add<T extends MyNumber<T>> extends BinaryOperations<T> {
  
    public Add(Expression3<T> e1, Expression3<T> e2) {
       super(e1, e2);      
    }

    protected T getAns(T a, T b) throws ZeroException, OverException  {
        return (T) a.add(b);
    } 
}
