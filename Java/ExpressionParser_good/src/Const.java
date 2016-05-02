public class Const implements Expression3 {
    private final int a;
                            
    public Const(int x) {
        a = x;
    }

    public int evaluate(int x, int y, int z) {
        return a;
    }
              
}