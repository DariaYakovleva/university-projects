public abstract class BinaryOperations implements Expression3 {
    private Expression3 e1, e2;

    protected BinaryOperations(Expression3 e1, Expression3 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
   
    protected abstract int getAns(int a, int b) throws Exception3;

    public int evaluate(int x, int y, int z) throws Exception3 { 
		return getAns(e1.evaluate(x, y, z), e2.evaluate(x, y, z));
    }  

}