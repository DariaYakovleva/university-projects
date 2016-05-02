
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ParserTex {

	String text = "";
	int nextTerm = 0;
	List<String> Target = new ArrayList<>();
	List<String> SubjectInterconnection = new ArrayList<>();
	List<String> Requirements = new ArrayList<>();
	List<String> Structure = new ArrayList<>();
	List<String> res = new ArrayList<>();
	List<String> res2 = new ArrayList<>();
	boolean target = false;
	boolean subject = false;
	boolean req = false;
	boolean structure = false;
	boolean hm2 = false;
	int curSec = 0;
	int curLab = 0;

	public ParserTex(Scanner in) {
		while (in.hasNext()) {
			//			System.out.println(in.next() + " !! " + text.length());
			text += in.nextLine();
		}
		nextTerm = 0;
		text.replaceAll("\\\\", "");
		text.replaceAll("\\_", "");
		text.replaceAll("~", "");
		Target.add("<Target>\n");
		SubjectInterconnection.add("<SubjectInterconnection>\n");
		Requirements.add("<Requirements>\n");
		Structure.add("<Structure>\n");
		convert();
	}

	public void begin() {
		nextTerm++;
		String bName = "";
		while (text.charAt(nextTerm) != '}') {
			bName += text.charAt(nextTerm);
			nextTerm++;
		}
		nextTerm++;
		convert();
	}

	public void command(String name) {
		String cur = "";
		if (name.equals("begin")) {
			boolean hm = false;
			if (hm2) {
				hm2 = false;
				hm = true;
			}
			begin();
			if (hm) {
				hm = false;
				req = false;
				subject = true;
				while (text.charAt(nextTerm) != '\\' && text.charAt(nextTerm) != '{' && text.charAt(nextTerm) != '}') {
					cur += text.charAt(nextTerm);
					nextTerm++;
				}
			}
		}
		if (name.equals("newpage")) {
			//			System.out.println(nextTerm);
			target = false;
			subject = false;
			req = false;
			structure = false;
		}
		if (name.equals("item")) {
			while (text.charAt(nextTerm) != '\\' && text.charAt(nextTerm) != '{') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
			if (!structure) {
				if (cur.endsWith(":") || cur.startsWith(" Á.")) {
					cur = "• " + cur;
					//					cur = (char)0x0387 + cur;
				} else {
					cur = "– " + cur;
					//					cur = (char)0x0336 + cur;
				}
			} else {
				if (cur.startsWith("[Ëåêöèÿ")) {
					cur = cur.replace("[", "");
					cur = cur.replaceAll("]", "");
					cur = cur.replaceAll("«", "");
					cur = cur.substring(0, cur.indexOf("»"));

				} else if (cur.startsWith("[Ëàáîðàòîðíàÿ")) {
					cur = cur.substring(cur.indexOf("«") + 1, cur.indexOf("»"));
					curLab++;
					cur = "<ProgramLaboratory LaboratoryID=\"" + curLab + "\" Name=\"" + cur + "\"/>";
				} else {
					cur = "";
				}
			}
		}
		if (name.equals("bfseries")) {
			while (text.charAt(nextTerm) != '\\' && text.charAt(nextTerm) != '{' && text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
		}
		if (name.equals("paragraph") || name.equals("textbf")) {
			nextTerm++;
			while (text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
			if (cur.startsWith("Ëàáîðàòîðíûé ïðàêòèêóì")) {
				String ch = "";
				int pos = 1;
				while (cur.charAt(pos) != '~') pos++;
				pos += 3;
				while (cur.charAt(pos) >= '0' && cur.charAt(pos) <= '9') {
					ch += cur.charAt(pos);
					pos++;
				}
				for (int i = Structure.size() - 1; i >= 0; i--) {
					if (Structure.get(i).startsWith("<Section")) {
						Structure.set(i, Structure.get(i).replaceAll("##", ch));
						break;
					}
				}
				cur = "\n </Data>";
			} else
				if (cur.startsWith("Òåîðåòè÷åñêèå çàíÿòèÿ")) {
					String ch = "";
					int pos = 1;
					while (cur.charAt(pos) != '~') pos++;
					pos += 3;
					while (cur.charAt(pos) >= '0' && cur.charAt(pos) <= '9') {
						ch += cur.charAt(pos);
						pos++;
					}
					for (int i = Structure.size() - 1; i >= 0; i--) {
						if (Structure.get(i).startsWith("<Section")) {
							Structure.set(i, Structure.get(i).replaceAll("%%", ch));
							break;
						}
					}
					cur = "";
				} else if (structure) {
					cur = "";
				}
		}
		if (name.equals("section")) {
			nextTerm++;
			while (text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
			if (cur.equals("ÖÅËÈ ÎÑÂÎÅÍÈß ÄÈÑÖÈÏËÈÍÛ")) {
				target = true;
			}
			if (cur.equals("ÌÅÑÒÎ ÄÈÑÖÈÏËÈÍÛ Â ÑÒÐÓÊÒÓÐÅ ÎÎÏ ÂÏÎ")) {
				target = false;
				req = true;
				hm2 = true;
			}

			nextTerm++;
			cur += " \n";
			cur = "";
			while (text.charAt(nextTerm) != '\\' && text.charAt(nextTerm) != '{' && text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
		}
		if (name.equals("subsection")) {
			nextTerm++;
			while (text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
			if (cur.equals("Âèäû è ñîäåðæàíèå ó÷åáíûõ çàíÿòèé")) {
				structure = true;
			}
			cur = "";
		}
		if (name.equals("subsubsection")) {
			nextTerm++;
			while (text.charAt(nextTerm) != '}') {
				cur += text.charAt(nextTerm);
				nextTerm++;
			}
			if (!cur.equals("Êóðñîâûå ðàáîòû")) {
				curSec++;
				if (curSec != 1) {
					Structure.add("</Section> \n");
				}
				String curName = cur.substring(cur.indexOf(".") + 3, cur.length() - 1);
				cur = "<Section SectionID=\"" + curSec + "\" Name=\"" + curName + 
						"\" LectureHours=\"%%\" PracticeHours=\"0\" LaboratoryHours=\"##\">";
				cur += "\n <Data>";
			} else {
				cur = "";
			}
		}
		if (!cur.isEmpty()) {
			cur += " \n";
			res.add(cur);
			if (target) {
				Target.add(cur);
			}
			if (subject) {
				SubjectInterconnection.add(cur);
			}
			if (req) {
				Requirements.add(cur);
			}
			if (structure) {
				Structure.add(cur);
			}
		}
	}

	public void convert() {
		while (nextTerm < text.length()) {
			if (text.charAt(nextTerm) == '\\') {
				String com = "";
				nextTerm++;
				while (text.charAt(nextTerm) >= 'a' && text.charAt(nextTerm) <= 'z') {
					com += text.charAt(nextTerm);
					nextTerm++;
				}
				if (com.equals("end")) {
					while (text.charAt(nextTerm) != '}') {
						nextTerm++;
					}
					nextTerm++;
					return;
				}
				command(com);
			} else {
				nextTerm++;
			}
		}
	}

	public List<String> getResult() {
		Target.add("</Target>\n");
		SubjectInterconnection.add("</SubjectInterconnection>\n");
		Requirements.add("</Requirements>\n");
		Structure.add("</Section>\n");
		Structure.add("</Structure>\n");
		res2.addAll(Target);
		res2.addAll(SubjectInterconnection);
		res2.addAll(Requirements);
		res2.addAll(Structure);
		return res2;
	}
}
