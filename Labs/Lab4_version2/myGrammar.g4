start
	: exprc
	;

exprc
	: EVAL expr {
		res.res = res.children.get("t1").res;
	}
	;

expr
	: t e1[res.children.get("t").res] {
		res.res = res.children.get("e1").res;
	}
	;

e1[int tt]
    : ADD t e1[res.children.get("t").res + tt] {
    	res.res = res.children.get("e1").res;
    }
    | EPS
    ;

t
    : f t1[res.children.get("f").res] {
    	res.res = res.children.get("t1").res;
    }
    ;

t1[int tt]
    : MUL f t1[res.children.get("f").res * tt] {
    	res.res = res.children.get("t1").res;
    }
    | EPS;

f
    : INT {
     	res.res = INT.res;
    }
    | LB expr RB {
    	res.res = res.children.get("expr").res;
    }
    ;

ADD: '+';
SUB: '-';
MUL: '*';
DIV: '/';
INT: [0-9]+;
UN: '_';
LB: '(';
RB: ')';
EVAL: 'evaluate';
EPS: '';
END: '$';