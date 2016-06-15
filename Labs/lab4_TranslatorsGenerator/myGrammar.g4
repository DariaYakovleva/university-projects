start
	: exprc {
		res.res = res.children.get("exprc").res;
	}
	;

exprc
	: expr {
		
		res.res = res.children.get("expr").res;
	}
	;

expr
	: t e1[res.children.get("t").res] {
		res.res = res.children.get("e1").res;
	}
	;

e1[int tt]
	@init {res.res = tt;}
    : ADD t e1[res.children.get("t").res + tt] {
    	res.res = res.children.get("e1").res;
    }
    | SUB t e1[tt - res.children.get("t").res] {
    	res.res = res.children.get("e1").res;
    }
    | EPS {
    	res.res = tt;
    }
    ;

t
    : f t1[res.children.get("f").res] {
    	res.res = res.children.get("t1").res;
    }
    ;

t1[int tt]
	@init {res.res = tt;}
    : MUL f t1[res.children.get("f").res * tt] {
    	res.res = res.children.get("t1").res;
    }
    | EPS {
    	res.res = tt;
    }

f	
	@init {String cur = lex.curValue;}
    : INT {     	
	    res.res = Integer.parseInt(cur);
    }
    | LB expr RB {
    	res.res = res.children.get("expr").res;
    }
    ;

SUB: '-';
ADD: '+';
MUL: '*';
INT: '0';
LB: '(';
RB: ')';
EPS: '';
END: '$';