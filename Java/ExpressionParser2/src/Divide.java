public class Divide extends BinaryOperations {
  
    public Divide(Expression3 e1, Expression3 e2) {
       super(e1, e2);      
    }

    protected int getAns(int a, int b) throws ZeroException, OverException {
    	
    	if (b == 0) {
    		throw new ZeroException("Division by zero");
    	}
    	Long res = ((long) a) / ((long) b);
    	if ((res > Integer.MAX_VALUE) || (res < Integer.MIN_VALUE)) {
    		throw new OverException("Overflow");
    	}
    	return a / b;
    } 
}