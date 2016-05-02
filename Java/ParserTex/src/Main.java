import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;


public class Main {
public static String NAME;
	
	public static void main(String args[]) throws Exception {

//		NAME = args[0];
		NAME = "body3";
		Scanner reader = new Scanner(new FileInputStream("text/" + NAME + ".tex"), "UTF-8");
		Writer out = new OutputStreamWriter(new FileOutputStream("text/" + NAME + ".xml"), "UTF-8");
		ParserTex parser = new ParserTex(reader);
//		PrintWriter out = new PrintWriter("text/" + NAME + ".xml", "UTF-8");
//		PrintWriter out = new PrintWriter("text/" + NAME + ".xml");
		List<String> result = parser.getResult();
		for (int i = 0; i < result.size(); i++) {
			out.append(result.get(i));
		}
		reader.close();
		out.close();
	}
}
 