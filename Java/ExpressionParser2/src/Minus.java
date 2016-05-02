public class Minus implements Expression3 {
    private Expression3 e1;

    public Minus(Expression3 e1) {
        this.e1 = e1;
    } 
                                                             
    public int evaluate(int x, int y, int z) throws Exception3 {
    	long res = -e1.evaluate(x, y, z);
    	if ((res > Integer.MAX_VALUE) || (res < Integer.MIN_VALUE)) {
    		throw new OverException("Overflow");
    	}
    	return (int) res;  
    }  
           
}
