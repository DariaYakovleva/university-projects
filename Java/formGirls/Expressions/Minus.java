public class Minus implements Expression3 {
    private Expression3 e1;

    private Minus(Expression3 e1) {
        this.e1 = e1;
    } 
                                                             
    public double evaluate(double x, double y, double z) {
        return -e1.evaluate(x, y, z);  
    }  
             
}
