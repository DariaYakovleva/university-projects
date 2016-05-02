public class Const<T extends MyNumber<T>> implements Expression3<T> {
    private final T a;
                            
    public Const(T x) {
        a = x;
    }

    public T evaluate(T x, T y, T z) {
        return (T) a;
    }
              
}