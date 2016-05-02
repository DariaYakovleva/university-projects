public class Minus<T extends MyNumber<T>> implements Expression3<T> {
    private Expression3<T> e1;

    public Minus(Expression3<T> e1) {
        this.e1 = e1;
    } 
                                                             
    public T evaluate(T x, T y, T z) throws Exception3 {
    	return (T) e1.evaluate(x, y, z).minus();  
    }  
           
}
