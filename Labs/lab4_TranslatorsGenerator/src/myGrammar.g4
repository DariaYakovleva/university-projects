grammar myGrammar;
start
	: exprc
	;

exprc
	returns[int res]
	: EVAL expr {
		res.res = t1.res;
	}
	;

expr
	returns[int res]
	: t e1[t0.res] {
		res.res = t1.res;
	}
	;

e1[int tt]
	returns[int res]
    : ADD t e1[t.res + tt] {
    	res.res = e1.res;
    }
    | EPS
    ;

t
	returns[int res]
    : f t1[f.res] {
    	res.res = t1.res;
    }
    ;

t1[int tt]
	returns[int res]
    : MUL f t1[f.res * tt] {
    	res.res = t1.res;
    }
    | EPS;

f
	returns[int res]
    : INT {
     	res.res = INT.res;
    }
    | LB expr RB {
    	res.res = expr.res;
    }
    ;


A: 'a';
B: 'b';
C: 'c';
ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
INT: [0..9]+;
UN: '_';
LB: '(';
RB: ')';
EVAL: 'evaluate';
EPS: 'e';
