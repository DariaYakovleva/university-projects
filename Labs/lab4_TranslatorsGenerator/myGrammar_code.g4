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
	: command {$res += $command.res;} command {$res += $command.res;} command {$res += $command.res;}
	;

command
	returns[String res]
	: read {
    	$res = $read.res;
    }
	| swap {
		$res = $swap.res;
	}
	;

swap
	returns[String res]
	locals[String tt]
	: var['d'] COMMA var['d'] EQ var['d'] COMMA var['d'] {
	}
	;


read
	returns[String res]
	locals[String tt]
	: RREAD UN type LB name RB {
		$res = "scanf(\"%" + $type.text + "\", &" + $name.text + ");\n";
		if ($type.text == "d") $tt = "int";
		if ($type.text == "f") $tt = "float";
		if ($type.text == "b") $tt = "boolean";
	}
	;


type
	returns [char t]
	: typet {
		$t = $typet.text;	
	}
	;

var[char in]
	returns[char t, String tt]
	: name {
		$t = $in;
	}
	;


typet: TB | TI | TF;
sign: ADD | SUB | MUL | DIV;
name: A | B | C;
TB: 'bool';
TI: 'int';
TF: 'float';
A: 'a';
B: 'b';
C: 'c';
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
TRUE: 'True';
FALSE: 'False';
INT: '0';
STR : 'aaa';
EQ: '=';
COMMA: ',';
UN: '_';
LB: '(';
RB: ')';
RREAD: 'read';

