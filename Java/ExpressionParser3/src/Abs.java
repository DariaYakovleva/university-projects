
public class Abs<T extends MyNumber<T>> implements Expression3<T> {
	private Expression3<T> a;
    public Abs(Expression3<T> e) {
       this.a = e;      
    }

    public T evaluate(T x, T y, T z) throws Exception3 {
    	T res = (T) a.evaluate(x, y, z).abs();
        return res;
    } 
}
