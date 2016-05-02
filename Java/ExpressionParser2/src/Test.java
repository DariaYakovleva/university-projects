public class Test {
	public static void main(String[] args) {

		try {
			Expression3 ans = new Variable("x")/*.parse("x^5 + y*lb(x^2 + 3)")*/;
			for (int i = 0; i < 10; i++) {
				System.out.print(i + " : ");
				for (int j = 0; j < 10; j++) {
					ans.evaluate(i, j, 0);
					test(ans, i, j, 0);
				}
				System.out.println();
			}
		} catch (ParseException e) {
			System.out.print(e.getMessage());
		}
	}

	public static void test(Expression3 ans, int x, int y, int z) {
		try {
			System.out.print(ans.evaluate(x, y, z) + " ");
		} catch (Exception3 e) {
			System.out.println(e.getMessage());
		}
	}

}