grammar GrammarsGrammar;


start
    : (rule1 | terminal)+
    ;

rule1
    : LOWERNAME params? rreturn? llocal? init? after? ':' state ('|' state)* ';'
    ;

attrs : '[' TYPE LOWERNAME (',' TYPE LOWERNAME)* ']';

state : state2+ code?;
state2: (variable? nnext BLOCK2?);
nnext: UPPERNAME | LOWERNAME;

terminal
    : UPPERNAME ':'  STR ('|' STR)* ';'
    ;

code : BLOCK ;

rreturn: RETURNS params;
llocal: LOCALS params;
init: INIT code;
after: AFTER code;
variable: LOWERNAME '=';

params: BLOCK2;

BLOCK2:      		'[' CONTENT2 ']';
fragment CONTENT2:   PTEXT2 BLOCK2 CONTENT2 | PTEXT2;
fragment PTEXT2:     (~[\[\]])+;


BLOCK:      		'{' CONTENT '}';
fragment CONTENT:   PTEXT+ BLOCK CONTENT | PTEXT+;
fragment PTEXT:     (~[{}"])+ | STR2;

RETURNS: 'returns';
LOCALS: 'locals';
AFTER: '@after';
INIT: '@init';
TYPE: 'String' | 'int' | 'boolean' | 'char';
UPPERNAME: ('A'..'Z') (('A'..'Z')|('0'..'9'))*;
LOWERNAME: ('a'..'z') (('a'..'z')|('0'..'9'))*;

STR : '\'' (~['\\])* '\'';
STR2 : '"' (~["])* '"';

WS: [ \t\r\n]+ -> skip;