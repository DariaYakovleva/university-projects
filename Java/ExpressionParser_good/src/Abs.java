
public class Abs implements Expression3{
	private Expression3 a;
    public Abs(Expression3 e) {
       this.a = e;      
    }

    public int evaluate(int x, int y, int z) {
        return Math.abs(a.evaluate(x, y, z));
    } 
}
