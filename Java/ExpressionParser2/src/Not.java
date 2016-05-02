
public class Not implements Expression3 {
	private Expression3 a;
    public Not(Expression3 e) {
       this.a = e;      
    }

    public int evaluate(int x, int y, int z) throws Exception3 {
        return ~a.evaluate(x, y, z);
    } 
}
