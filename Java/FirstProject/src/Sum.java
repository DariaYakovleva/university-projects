
public class Sum {
	public static void main(String[] args) {
		String a = "";
		for (int i = 0; i < args.length; i++) {
			a = a.concat(args[i] + " ");
		}
		String arr[] = a.split("\\s+");
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			res += Integer.parseInt(arr[i]);
		}
		System.out.println(res);

	}

}
