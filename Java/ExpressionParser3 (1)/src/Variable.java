public class Variable<T extends MyNumber<T>> implements Expression3<T> {
    private final String v;
         
    public Variable(String s) {
        if ((s.equals("x")) || (s.equals("y")) || (s.equals("z"))) {
            v = s;
        } else {
            throw new AssertionError("Wrong variable name");
        }
    } 

    public T evaluate(T x, T y, T z) {      
        if (v.equals("x")) {
            return x;
        } else if (v.equals("y")) {
            return y;
        } else if (v.equals("z")) {
            return z;
        }
        throw new AssertionError("Unreachable state");           
    }                                                 

}