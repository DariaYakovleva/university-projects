start
	locals [String res]
	@init {
		$res = "int main() {\n";
	}
	@after {
		$res +=  "\n";
		System.out.println($res);
	}
	: command {
		$res += $command.res;
	}
	;

command
	returns[String res]
	: read {
    	$res = $read.res;
    }
	| assigment {
		$res = $assigment.res;
	}
	;


assigment
	returns[String res]
	locals [String tt]
	: NAME EQ expr {
		$res = $NAME.text + " = " + $expr.text + ";\n";
		if (!memory.containsKey($NAME.text)) {
			if ($expr.t == 'd') $tt = "int";
			if ($expr.t == 'f') $tt = "float";
			if ($expr.t == 'b') $tt = "bool";
			variables.add($tt + " " + $NAME.text + ";\n");
			memory.put($NAME.text, $expr.t);
		}
	}
	;

read
	returns[String res]
	locals[String tt]
	: NAME EQ RREAD UN type LB RB {
		$res = "scanf(\"%" + $type.t + "\", &" + $NAME.text + ");\n";
		if (!memory.containsKey($NAME.text)) {
			if ($type.t == 'd') $tt = "int";
			if ($type.t == 'f') $tt = "float";
			if ($type.t == 'b') $tt = "boolean";
			variables.add($tt + " " + $NAME.text + ";\n");
			memory.put($NAME.text, $type.t);
		}
	}
	;


expr
	returns[String res, char t]
	: LB expr RB {
		$res = '(' + $expr.text + ')';
		$t = $expr.t;
	}
	| op11 = expr op3 = sign op2 = expr {
		$res = $op11.text + " " + $op3.text + " " + $op2.text;
		$t = $op11.t;
		if ($op2.t == 'f' || $op11.t == 'f') $t = 'f';
	}
	| op12 = INT {
		$res = $op12.text;
		$t = 'd';
	}
	;
type
	returns [char t]
	: type1 {
		$t = 'type';
	}
	;

type1: TB | TI | TF;

sign: ADD | SUB | MUL | DIV;

TB: 'bool';
TI: 'int';
TF: 'float';
NAME: 'name';
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
TRUE: 'True';
FALSE: 'False';
INT: '0';
STR : 'aaa';
EQ: '=';
UN: '_';
LB: '(';
RB: ')';
RREAD: 'read';

