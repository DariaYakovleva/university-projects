public class Log<T extends MyNumber<T>> implements Expression3<T> {
    private Expression3<T> e1;

    public Log(Expression3<T> e1) {
        this.e1 = e1;
    } 
                                                             
    public T evaluate(T x, T y, T z) throws Exception3 {
    	return  (T) e1.evaluate(x, y, z).log();
    }  
             
}
