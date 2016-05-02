import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;


public class Correctness extends MakeExpr{
	public Correctness(List<Expression> s) {
		statements = s;
	}
	
	List<String> goCheck() {
		List<String> result = new ArrayList<>();
		for (int i = 0; i < statements.size(); i++) {
			Expression exp = statements.get(i);
			int ax = compWithAx(exp);
			if (ax != -1) {
				result.add("(" + (i + 1) + ") " + exp.printExp() + " (Сх.акс. " + (ax + 1) + ")");
			} else {
				Pair<Integer, Integer> mp = modusPonens(exp, statements);
				if (mp != null) {
					result.add("(" + (i + 1) + ") " + exp.printExp() + " (M.P. "
							+ (mp.getKey() + 1) + ", " + (mp.getValue() + 1) + ")");
				} else {
					statements.set(i, null);
					result.add("(" + (i + 1) + ") " + exp.printExp() + " Не доказано");
					System.out.println("(" + (i + 1) + ") " + exp.printExp() + " Не доказано");
				}
			}
		}
		return result;
	}
	
	public List<String> getStatements() {
		return goCheck();
	}
}
