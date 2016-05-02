public class Multiply extends BinaryOperations {
  
    public Multiply(Expression3 e1, Expression3 e2) {
       super(e1, e2);      
    }

    protected int getAns(int a, int b) throws OverException {
    	Long res = ((long) a) * ((long) b);
    	if ((res > Integer.MAX_VALUE) || (res < Integer.MIN_VALUE)) {
    		throw new OverException("Overflow");
    	}
        return a * b;
    } 
}