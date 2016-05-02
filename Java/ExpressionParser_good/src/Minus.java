public class Minus implements Expression3 {
    private Expression3 e1;

    public Minus(Expression3 e1) {
        this.e1 = e1;
    } 
                                                             
    public int evaluate(int x, int y, int z) {
        return -e1.evaluate(x, y, z);  
    }  
             
}
