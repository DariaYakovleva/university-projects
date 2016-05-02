import java.math.BigInteger;


public class GenericParser<T extends MyNumber<T>> {
		public static void main(String[] args) {
				try {
				if (args[0].equals("-i")) {
			
						Expression3<MyInteger> ans = ExpressionParser.parse(args[1], new MyInteger(1), args[0]);
						for (Integer i = 5; i <= 5; i++) {
						for (Integer j = 5; j <= 5; j++) {
							try {
							//test(ans, new MyInteger(i), new MyInteger(j), new MyInteger(0));
							System.out.print(ans.evaluate(new MyInteger(i), 
									new MyInteger(j), new MyInteger(0)).getValue() + " ");
							} catch (Exception3 e) {
								System.out.print("error ");
							}
					}
				}
				}
				if (args[0].equals("-d")) {
					Expression3<MyDouble> ans = ExpressionParser.parse(args[1], new MyDouble(1.0), args[0]);
					for (double i = -100; i <= 100; i += 1) {
						for (double j = -100; j <= 100; j += 1) {
							try {
							System.out.print(ans.evaluate(new MyDouble(i), 
									new MyDouble(j), new MyDouble(0.0)).getValue() + " ");
							//test(ans, new MyDouble(i), new MyDouble(j), new MyDouble(0.0));
							} catch (Exception3 e) {
								System.out.print("error ");
							} 
						}
					} 
				}
				if (args[0].equals("-bi")) {
					Expression3<MyBigInteger> ans = ExpressionParser.parse(args[1], new MyBigInteger(BigInteger.valueOf(1)), args[0]);
					
					for (int i = -100; i <= 100; i++) {
						for (int j = -100; j <= 100; j++) {
							try {
							System.out.print(ans.evaluate(new MyBigInteger(BigInteger.valueOf(i)), 
									new MyBigInteger(BigInteger.valueOf(j)), 
									new MyBigInteger(BigInteger.valueOf(0))).getValue() + " ");
							//test(ans, new MyBigInteger(BigInteger.valueOf(i)),
								//	new MyBigInteger(BigInteger.valueOf(j)), 
									//new MyBigInteger(BigInteger.valueOf(0)));
							} catch (Exception3 e) {
								System.out.print("error ");
							}
						}
					}
					
					}
				
				} catch (ParseException e) {
					System.out.print("error ");
				}
			
		}

		public <T extends MyNumber<T>>void test(Expression3<T> ans, T x, T y, T z) {
			try {
				System.out.print(ans.evaluate(x, y, z) + " ");
			} catch (Exception3 e) {
				System.out.println("error");
			}
		}
}
