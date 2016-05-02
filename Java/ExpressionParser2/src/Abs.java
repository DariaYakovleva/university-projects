
public class Abs implements Expression3{
	private Expression3 a;
    public Abs(Expression3 e) {
       this.a = e;      
    }

    public int evaluate(int x, int y, int z) throws Exception3 {
    	long res = Math.abs((long)a.evaluate(x, y, z));
    	if ((res > Integer.MAX_VALUE) || (res < Integer.MIN_VALUE)) {
    		throw new OverException("Overflow");
    	}
        return (int) res;
    } 
}
