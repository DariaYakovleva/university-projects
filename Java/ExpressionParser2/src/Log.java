public class Log implements Expression3 {
    private Expression3 e1;

    public Log(Expression3 e1) {
        this.e1 = e1;
    } 
                                                             
    public int evaluate(int x, int y, int z) throws Exception3 {
    	
    	int tmp = e1.evaluate(x, y, z);
    	if (tmp <= 0) {
    		throw new LogException("Incorrect argument in Log");
    	}
    	return (int) Math.floor(Math.log(tmp) / Math.log(2));  
    }  
             
}
