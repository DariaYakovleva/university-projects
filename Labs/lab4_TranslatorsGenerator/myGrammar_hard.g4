start
	locals [String res]
	@init {
		$res = "int main() {\n";
	}
	@after{
		for (String s: variables) System.out.println(s);
		$res +=  "}\n";
		System.out.println($res);
	}
	: (command {$res += $command.res;})+
	;

command
	returns[String res]
	: read {
    	$res = $read.res;
    }
	| assigment {
		$res = $assigment.res;
	}
	| statement {
		$res = $statement.res;
	}
	| forStatement {
		$res = $forStatement.res;
	}
	| whileStatement {
		$res = $whileStatement.res;
	}
	| print {
		$res = $print.res;
	}
	;


assigment
	returns[String res]
	locals [String tt]
	: name '=' expr {
		$res = $name.res + " = " + $expr.res + ";\n";
		if (!memory.containsKey($name.res)) {
			if ($expr.t == 'd') $tt = "int";
			if ($expr.t == 'f') $tt = "float";
			if ($expr.t == 'b') $tt = "bool";
			variables.add($tt + " " + $name.res + ";\n");
			memory.put($name.res, $expr.t);
		}
	}
	;


print
	returns [String res]
	locals [List<String> lst, String tmp]
	@init {
		String tmp = "";
		$lst = new ArrayList<String>();
	}
	@after{}
	: 'print' '(' var { $lst.add($var.text); } (',' n1 = var { $lst.add($n1.text); })* ')' {
		$res = "printf(\"";
		for (String n: $lst) {
			if (memory.containsKey(n)) {
				$res += "%" + memory.get(n) + " ";
			} else {
				$res += "%" + "d" + " ";
			}
		}
		$res += "\", ";
		for (int i = 0; i < $lst.size(); i++) {
			String n = $lst.get(i);
			if (i > 0) $res += ", ";
        	$res += n;
        }
        $res += ");\n";
	}
	| 'print' '(' STR ')' {
		$tmp = $STR.text;
		$tmp.replace('\'', '"');
		$res = "printf(" + $tmp + ");\n";
    }
    | 'print' '(' expr ')' {
    	$res = "printf(%" + $expr.t + ", " + $expr.res + ");\n";
    }
	;


read
	returns[String res]
	locals[String tt]
	: 'read' '_' type '(' name ')' {
		$res = "scanf(\"%" + $type.t + "\", &" + $name.res + ");\n";
		if (!memory.containsKey($name.res)) {
			if ($type.t == 'd') $tt = "int";
			if ($type.t == 'f') $tt = "float";
			if ($type.t == 'b') $tt = "boolean";
			variables.add($tt + " " + $name.res + ";\n");
			memory.put($name.res, $type.t);
		}
	}
	;


statement
	returns[String res]
	locals [String if1, String if2]
    @init {
    	$res = "";
    	$if1 = "";
    	$if2 = "";
    }
    @after{
        $res += $if1 + "}\n";
        if (!$if2.isEmpty()) $res += "else {\n" + $if2 + "\n}\n";
    }
	: 'if' '(' logicExpr ')' '{' (command {$if1 += $command.res;})+ '}' ('else' '{' (command {$if2 += $command.res;})+ '}')? {
		$res = "if (" + $logicExpr.text + ") {\n";
	}
	;


whileStatement
	returns[String res]
	locals [String while1]
    @init {
    	$res = "";
    	$while1 = "";
    }
    @after{
        $res += $while1 + "}\n";
    }
	: 'while' '(' logicExpr ')' '{' (command {$while1 += $command.res;})+ '}' {
		$res = "while (" + $logicExpr.text + ") {\n";
	}
	;


forStatement
	returns[String res]
	locals [String for1]
    @init {
    	$res = "";
    	$for1 = "";
    }
    @after{
        $res += "{\n" + $for1 + "}\n";
    }
	: 'for' var '=' 'range' '(' expr ')' '{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (" + $var.text + " = 0;" + $var.text + " < "  + $expr.res + "; " + $var.text + "++) ";
	}
	| 'for' var '=' 'range' '(' f1 = expr ',' f2 = expr ')' '{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (" + $var.text + " = " + $f1.res + "; " + $var.text + " < "  + $f2.res + "; " + $var.text + "++) ";
	}
	| 'for' var '=' 'range' '(' f11 = expr ',' f22 = expr ',' f33 = expr ')'
		'{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (" + $var.text + " = " + $f11.res + "; " + $var.text + " < "  + $f22.res + "; " +
		$var.text + " = " + $var.text  + " + " + $f33.res + ") ";
	}
	;


expr
	returns[String res, char t]
	: '(' expr ')' {
		$res = '(' + $expr.text + ')';
		$t = $expr.t;
	}
	| op11 = expr op3 = SIGN op2 = expr {
		$res = $op11.text + " " + $op3.text + " " + $op2.text;
		$t = $op11.t;
		if ($op2.t == 'f' || $op11.t == 'f') $t = 'f';
	}
	| var['d'] {
		$res = $var.text;
		$t = $var.t;
	}
	| op12 = INT {
		$res = $op12.text;
		$t = 'd';
	}
	| op13 = FLOAT {
		$res = $op13.text;
		$t = 'f';
	}
	;


logicExpr
	returns[String res]
	: '(' op14 = logicExpr ')' {
		$res = "(" + $op14.text + ")";
	}
	| op15 = logicExpr op2 = ('or'|'and') op3 = logicExpr {
		$res = $op15.text + " " + $op2.text + " " + $op3.text;
	}
	| op16 = expr op22 = LOGICTERM op33 = expr {
		$res = $op16.text + " " + $op22.text + " " + $op33.text;
	}
	| BOOLEAN {
		$res = $BOOLEAN.text;
	}
	;


var
	returns[char t, String tt]
	: NAME {
		if (memory.containsKey($NAME.text)) {
			$t = memory.get($NAME.text);
		} else {
			$t = 'd';
		}
		if ($t == 'd') $tt = "int";
        if ($t == 'f') $tt = "float";
        if ($t == 'b') $tt = "boolean";
		if (!memory.containsKey($NAME.text)) {
			memory.put($NAME.text, $t);
			variables.add($tt + " " + $NAME.text + ";\n");
		}
	}
	;


type
	returns [char t]
	: 'int' {
		$t = 'd';
	}
	| 'float' {
		$t = 'f';
	}
	| 'bool' {
		$t = 'b';
	}
	;

name
    returns [String res]
    : NAME {
        $res = $NAME.text;
    }

TYPE: 'bool' | 'int' | 'float';
NAME: 'a' | 'b' | 'c' | 'd' | 'e' | 'f' | 'g';
LOGICTERM: '==' | '>=' | '<=' | '<' | '>';
SIGN: '+' | '-' | '*' | '/';
BOOLEAN: 'True' | 'False';
INT: '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9';
STR : '\'' 'abc' '\'';
