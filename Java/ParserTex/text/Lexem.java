import java.util.*;
import java.io.*;

public class Lexem{
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("formal-lang.xml"));
		//Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter("res.txt");
		//PrintWriter out = new PrintWriter(System.out);
	//	init();
		while (in.hasNext()) {
			String s = in.next();
			String lex = "";
			boolean f = false;
			for (int i = 0; i < s.length(); i++) {
				if (f) {
					if ((s.charAt(i) >= 'a') && (s.charAt(i) <= 'z')) {
						lex += s.charAt(i);
					} else {
						out.println(lex);
						f = false;
					}
				} 
				if ((s.charAt(i) == '<') && (s.charAt(i + 1) != '/')) {
					f = true;
				}
			}
			
		}
		out.close();
	}
}
