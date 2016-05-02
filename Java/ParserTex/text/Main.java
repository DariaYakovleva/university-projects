import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class Main {

	public static String NAME;

	public static void main(String args[]) throws Exception {

		NAME = args[0];
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		spf.setValidating(true);
		SAXParser parser = spf.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		reader.setErrorHandler(new MyErrorHandler());
		parser.parse(NAME + ".xml", new MySAXHandler());
	}
}

class MyErrorHandler implements ErrorHandler {
	public void warning(SAXParseException e) throws SAXException {
		show("Warning", e);
		throw (e);
	}

	public void error(SAXParseException e) throws SAXException {
		show("Error", e);
		throw (e);
	}

	public void fatalError(SAXParseException e) throws SAXException {
		show("Fatal Error", e);
		throw (e);
	}

	private void show(String type, SAXParseException e) {
		System.out.println(type + ": " + e.getMessage());
		System.out.println("Line " + e.getLineNumber() + " Column "
				+ e.getColumnNumber());
		System.out.println("System ID: " + e.getSystemId());
	}
}

class MySAXHandler extends DefaultHandler {

	boolean book = false;
	static int N = 10;
	Map<String, Integer> names = new HashMap<String, Integer>();
	boolean[] now = new boolean[N];
	int numMod = 1;
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

	public void startDocument() {
		// System.out.println("Start document: ");
		names.put("Course", 0);
		names.put("location", 1);
		names.put("author", 2);
		names.put("enter", 3);
		names.put("competencies", 4);
		names.put("goal", 4);
		names.put("resources", 5);
		names.put("info", 6);
		names.put("tech", 6);
		names.put("main", 7);
		names.put("book", 8);
		names.put("control", 9);
		
		
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<String>());
			now[i] = false;
		}
		now[names.get("main")] = true;
		list.get(names.get("competencies")).add("### Выход");
		list.get(names.get("enter")).add("### Вход");
		list.get(names.get("author")).add("### Автор");
		list.get(names.get("info")).add("### Ресурсы");
		list.get(names.get("location")).add("### Содержание");
		list.get(names.get("Course")).add("# Курс ");
		list.get(names.get("resources")).add("### Основные источники ");
	}

	public void endDocument() {
		
		try {
			
			PrintWriter out = new PrintWriter(Main.NAME + ".markdown", "UTF-8");
			
			//File books = new File("bibliotemp.bib");
			//FileWriter booksW;
			//booksW = new FileWriter(books, true);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("bibliotemp.bib", true), "UTF-8");
	        BufferedWriter booksW = new BufferedWriter(writer);
	       
			for (int i = 0; i < N; i++) {

				//out.println();

				for (int j = 0; j < list.get(i).size(); j++) {
					if (i == names.get("Course")) {
						out.print(list.get(i).get(j));
						
					} else if (i == names.get("book")) {
						booksW.append(list.get(i).get(j) + "\n");
						
					} else {
						out.println(list.get(i).get(j));
					}
				}
				
				out.println();
			}
			booksW.close();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qname, Attributes attr) {

		if (names.get(localName) != null) {
			now[names.get(localName)] = true;
			if (localName.equals("book")) {
				list.get(names.get("book")).add("\n@BOOK{effective1, ");
			}
			now[names.get("main")] = false;
		}
		String str = "";
		String[] curBook = new String[5];
		
		if ((localName.equals("module"))) {

			str = "\n## Семестр ; " + numMod + " ; ";
			numMod++;

		} else if (localName.equals("topic")) {

			str = "\n### Раздел ; ";

		} else if (localName.equals("lecture")) {

			str = "\n#### Лекция ; 1 ; ";

		} else if ((localName.equals("practice")) || (localName.equals("laboratory"))) {

			str = "\n#### Лабораторная ; 1 ; ";
		} 
		for (int i = 0; i < attr.getLength(); i++) {
			if (now[names.get("book")]) {
				//list.get(names.get("book")).add(attr.getQName(i) + " = " + attr.getValue(i));
				if (attr.getQName(i).equals("id")) {
					curBook[0] = "author" + " = " + "{" + attr.getValue(i) + "},";
				}
				if (attr.getQName(i).equals("caption")) {
					curBook[1] = "title" + " = " + "{" + attr.getValue(i) + "},";
				}
				if (attr.getQName(i).equals("publisher")) {
					curBook[2] = "publisher" + " = " + "{" + attr.getValue(i) + "},";
				}
				if (attr.getQName(i).equals("year")) {
					curBook[3] = "year" + " = " + attr.getValue(i) + ",";
				}
				if (attr.getQName(i).equals("pages")) {
					curBook[4] = "numberOfPages" + " = " + attr.getValue(i);
				}
				
				if (attr.getQName(i).equals("caption")) {
					list.get(names.get("resources")).add(attr.getValue(i));
				}
				if (attr.getQName(i).equals("lastName")) {
					String s = list.get(names.get("resources")).get(list.get(names.get("resources")).size() - 1);
					list.get(names.get("resources")).remove(list.get(names.get("resources")).size() - 1);
					list.get(names.get("resources")).add(attr.getValue(i) + " " + (attr.getValue(i + 1) == null?"":attr.getValue(i + 1)) + ", " + s);
				}

			} else if (localName.equals("course")) {
				list.get(names.get("Course")).add(attr.getValue(i) + " ; ");

			} else if ((!str.isEmpty()) && (attr.getQName(i).equals("caption"))) {
					str += attr.getValue(i);// + "\n";
			}
		}	
		if (str.length() > 2) 
			list.get(names.get("main")).add(str);
		if (localName.equals("book")) {
			for (int i = 0; i < 5; i++) {
				if (curBook[i] != null) {
					list.get(names.get("book")).add(curBook[i]);
				}
			}
			list.get(names.get("book")).add("}");
		}
	}

	public void endElement(String uri, String localName, String qname) {
		if (names.get(localName) != null) {
			
			if (localName.equals("book")) {
				String s = list.get(names.get("resources")).get(list.get(names.get("resources")).size() - 1);
				list.get(names.get("resources")).remove(list.get(names.get("resources")).size() - 1);
				list.get(names.get("resources")).add("* " + s);
			}

			now[names.get(localName)] = false;
			now[names.get("main")] = true;
		}
	}

	public void characters(char[] ch, int start, int length) {
		if (now[names.get("control")]) return;
		
		String s = new String(ch, start, length);
		String[] words = s.split("\\s");
		String res = new String("");
		for (int i = 0; i < words.length; i++) {
			String[] words2 = words[i].split("\n");
			for (int j = 0; j < words2.length; j++) {
				if (!(words2[j].equals("\n") || words2[j].equals(""))) {
					res += words2[j] + " ";
				}
			}
		}
		if (res.length() > 2) {
			for (int i = 0; i < N; i++) {
				if (now[i]) {
					if (i != names.get("location")) {
						list.get(i).add("* " + res);
					} else {
						list.get(i).add(res);
					}
				}
			}
		}
	}

	public void ignorableWhitespace(char[] ch, int start, int length) {
		// System.out.println("** " + new String(ch, start, length));
	}
}