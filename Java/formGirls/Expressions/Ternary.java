public class Ternary implements Expression3 {
    private Expression3 e1, e2, e3;

    private Ternary(Expression3 e1, Expression3 e2, Expression3 e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    } 
                                                             
    public double evaluate(double x, double y, double z) {
        if (e1.evaluate(x, y, z) == 0) { 
            return e2.evaluate(x, y, z);
        } else {
            return e3.evaluate(x, y, z);
        }
    }  
             
}
