public class Test<T extends MyNumber<T>> {
	public static void main(String[] args) {

		try {
			Expression3<MyInteger> ans = ExpressionParser.parse("x^5 + y*lb(x^2 + 3)");
			for (int i = 0; i < 1; i++) {
				System.out.print(i + " : ");
				for (int j = 0; j < 1; j++) {
					test(ans, new MyInteger(i), new MyInteger(j), new MyInteger(0));
				}
				System.out.println();
			}
		} catch (ParseException e) {
			System.out.print(e.getMessage());
		}
	}

	public static void test(Expression3<MyInteger> ans, MyInteger x, MyInteger y, MyInteger z) {
		try {
			System.out.print(ans.evaluate(x, y, z) + " ");
		} catch (Exception3 e) {
			System.out.println(e.getMessage());
		}
	}

}