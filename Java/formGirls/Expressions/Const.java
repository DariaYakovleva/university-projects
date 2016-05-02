public class Const implements Expression3 {
    private double a;
                            
    public Const(double x) {
        a = x;
    }

    public double evaluate(double x, double y, double z) {
        return a;
    }
              
}