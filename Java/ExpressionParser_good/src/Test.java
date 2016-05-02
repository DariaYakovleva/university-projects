public class Test {
    public static void main(String[] args) {

        int x = Integer.parseInt(args[0]); 
        int y = Integer.parseInt(args[1]);  
        int z = Integer.parseInt(args[2]);  

        Expression3 ans = ExpressionParser.parse("x / 1269562454 + x - x / ( z ) * - z - z");
        System.out.println(ans.evaluate(x, y, z));
    }
}