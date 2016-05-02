public abstract class BinaryOperations<T extends MyNumber<T>> implements Expression3 <T> {
    private Expression3<T> e1, e2;

    protected BinaryOperations(Expression3<T> e1, Expression3<T> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
   
    protected abstract T getAns(T a, T b) throws Exception3;

    public T evaluate(T x, T y, T z) throws Exception3 { 
		return getAns(e1.evaluate(x, y, z), e2.evaluate(x, y, z));
    }  

}