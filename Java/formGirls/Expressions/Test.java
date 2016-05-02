public class Test {
    public static void main(String[] args) {

        double x = Double.parseDouble(args[0]);          

        double ans = new Add(
                         new Subtract(
                             new Multiply(
                                 new Variable("x"), 
                                 new Variable("x")
                             ), 
                             new Multiply(
                                 new Const(2),
                                 new Variable("x")
                             )
                          ),
                          new Const(1)     
                     ).evaluate(x, 0, 0);
                                     
        System.out.println(ans);
    }
}