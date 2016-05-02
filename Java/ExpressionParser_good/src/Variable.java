public class Variable implements Expression3 {
    private final String v;
         
    public Variable(String s) {
        if ((s.equals("x")) || (s.equals("y")) || (s.equals("z"))) {
            v = s;
        } else {
            throw new AssertionError("Wrong variable name");
        }
    } 

    public int evaluate(int x, int y, int z) {      
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