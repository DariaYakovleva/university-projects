
public class Not<T extends MyNumber<T>> implements Expression3<T> {
	private Expression3<T> a;
    public Not(Expression3<T> e) {
       this.a = e;      
    }

    public T evaluate(T x, T y, T z) throws Exception3 {
        return (T) a.evaluate(x, y, z).not();
    } 
}
