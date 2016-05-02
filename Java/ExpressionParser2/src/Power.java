public class Power extends BinaryOperations {
  
    public Power(Expression3 e1, Expression3 e2) {
       super(e1, e2);      
    }

    protected int getAns(int a, int b) throws Exception3 {
    
    	if (b < 0) {
    		throw new PowerException("Incorrect argument in Power");
    	}
    	long res = 1;
    	long aa = (long) a;
    	long bb = (long) b;
    	while (bb > 0) {
    		
    		if (bb % 2 == 1) {
    			if ((aa > Integer.MAX_VALUE) || (aa < Integer.MIN_VALUE)) {
        			throw new OverException("Overflow");
        		}
    			res *= aa;
        		if ((res > Integer.MAX_VALUE) || (res < Integer.MIN_VALUE)) {
        			throw new OverException("Overflow");
        		}
    		}
    		if ((aa > Integer.MAX_VALUE) || (aa < Integer.MIN_VALUE)) {
    			throw new OverException("Overflow");
    		}
    		aa *= aa;
    		bb >>= 1;
    	}
    	return (int) res;
    } 
}