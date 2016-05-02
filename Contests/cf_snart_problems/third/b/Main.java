import java.util.*;

public class Main {
	
	static int n;
	static int k;
	static boolean solve(int v) {
		int res = 0;
		int cur = v;
		while (cur > 0) {
			res += cur;
			cur /= k;
		}
		return (res >= n);
	}
	public static int bs() {
		int l = 0;
		int r = n;
		while (r - l > 1) {
			int m = (r + l) / 2;
			if (!solve(m)) {
				l = m;
			} else {
				r = m;
			}
		}
		return r;
	}
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		k = in.nextInt();

		System.out.println(bs());
	}
}