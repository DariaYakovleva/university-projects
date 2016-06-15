start
	locals [String res]
	@init {}
	@after {}
	: command command command
	;

command
	returns[String res]
	: read {}
	| swap {}
	| exprc {}
	;


exprc
	returns[String res]
	: EVAL expr
	;

expr
	returns[int res]
	: t e1[t] {
		$res = $e1.res;
	}
	;

e1[int tt]
	returns[int res]
    : ADD t e1[t.res + tt] {
    	$res = $e1.res;    	
    }
    | EPS
    ;

t[int tt]
	returns[int res]
    : f t1[f.res] {
    	$res = $t1.res;
    }
    ;

t1[int tt]
    : MUL f t1[f.res * tt] {
    	$res = $t1.res;
    }
    | EPS;

f
	returns[int res]
    : INT {
     	$res = $INT.res;
    }
    | LB expr RB {
    	$res = $expr.res;
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
	: RREAD UN type LB name RB {}
	;


type
	returns [char ty]
	: type1 {}
	;

var[char in]
	returns[char ty, String tt]
	: name {
		$ty = $in;
	}
	;


type1: TB | TI | TF;
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
EVAL: 'evaluate';
EPS: '';
