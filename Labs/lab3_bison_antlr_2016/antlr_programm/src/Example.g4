grammar Example;

@header {
	import java.util.HashMap;
	import java.util.Map;
}
@parser::members {
	Map<String, Character> memory = new HashMap<String, Character>();
}

start
	locals [String res]
	@init {
		$res = "int main() {\n";
	}
	@after{
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
	| swap {
		$res = $swap.res;
	}
	| print {
		$res = $print.res;
	}
	;


assigment
	returns[String res]
	: NAME '=' BOOLEAN {
      	$res = "bool " + $NAME.text + " = " + $BOOLEAN.text + ";\n";
      	memory.put($NAME.text, 'b');
    }
	| NAME '=' INT {
		$res = "int " + $NAME.text + " = " + $INT.text + ";\n";
		memory.put($NAME.text, 'd');
	}
	| NAME '=' FLOAT {
		$res = "float " + $NAME.text + " = " + $FLOAT.text + ";\n";
		memory.put($NAME.text, 'f');
	}
	| NAME '=' expr {
		$res = "int " + $NAME.text + " = " + $expr.text + ";\n";
		memory.put($NAME.text, $expr.t);
	}
	;


print
	returns [String res]
	locals [List<String> lst]
	@init {
		String tmp = "";
		$lst = new ArrayList<String>();
	}
	@after{}
	: 'print' '(' NAME { $lst.add($NAME.text); } (',' n1 = NAME { $lst.add($n1.text); })* ')' {
		$res = "printf(\"";
		for (String n: $lst) {
//			if (memory.containsKey(n)) {
				$res += "%" + memory.get(n) + " ";
//			} else {
//				$res += "%" + "d" + " ";
//			}
		}
		$res += "\", ";
		for (int i = 0; i < $lst.size(); i++) {
			String n = $lst.get(i);
			if (i > 0) $res += ", ";
        	$res += n;
        }
        $res += ");\n";
	}
	| 'print' '(' '\'' STR '\'' ')' {
		$res = "printf(\"" + $STR.text + "\");\n";
    }
    | 'print' '(' expr ')' {
    	$res = "printf(%" + $expr.t + ", " + $expr.res + ");\n";
    }
	;


swap
	returns[String res]
	locals[String tt]
	: op1 = var ',' op2 = var '='  var ',' var {
		if ($op1.t == 'd') $tt = "int";
		if ($op1.t == 'f') $tt = "float";
		if ($op1.t == 'b') $tt = "boolean";
		$res = "";
		$res += $tt + " tmp = " + $op1.text + ";\n";
		$res += $op1.text + " = " + $op2.text  + ";\n";
		$res += $op2.text + " = tmp" + ";\n";
	}
	;


read
	returns[String res]
	: NAME '=' 'read' '_' type '(' ')' {
		$res = "scanf(%" + $type.t + ", &" + $NAME.text + ");\n";
		memory.put($NAME.text, $type.t);
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
		$res = "if (" + $logicExpr.text + ") {\n";
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
	: 'for' NAME {memory.put($NAME.text, 'd');} '=' 'range' '(' expr ')' '{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (int " + $NAME.text + " = 0;" + $NAME.text + " < "  + $expr.res + "; " + $NAME.text + "++) ";
	}
	| 'for' NAME {memory.put($NAME.text, 'd');} '=' 'range' '(' f1 = expr ',' f2 = expr ')' '{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (int " + $NAME.text + " = " + $f1.res + "; " + $NAME.text + " < "  + $f2.res + "; " + $NAME.text + "++) ";
	}
	| 'for' NAME {memory.put($NAME.text, 'd');} '=' 'range' '(' f11 = expr ',' f22 = expr ',' f33 = expr ')'
		'{' (command {$for1 += $command.res;})+ '}' {
		$res = "for (int " + $NAME.text + " = " + $f11.res + "; " + $NAME.text + " < "  + $f22.res + "; " +
		$NAME.text + " = " + $NAME.text  + " + " + $f33.res + ") ";
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
		if ($op2.t == 'f') $t = $op2.t;
	}
	| var {
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
	returns[char t]
	: NAME {
//		if (memory.containsKey($NAME.text))
//			$t = memory.get($NAME.text);
//		else
			$t = 'd';
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

TYPE: 'bool' | 'int' | 'float';

NAME: (('a'..'z')|('A'..'Z'))(('a'..'z')|('A'..'Z')|('0'..'9'))*;

LOGICTERM: '==' | '>=' | '<=' | '<' | '>';

SIGN: '+' | '-' | '*' | '/';

BOOLEAN: 'True' | 'False';

INT: '0' | [1-9] [0-9]*;

FLOAT: '-'? INT '.' INT EXP? | '-'? INT EXP | '-'? INT;

EXP: [Ee] [+\-]? INT;

WS: [ \t\r\n]+ -> skip;

STR : (~['\\])+;
