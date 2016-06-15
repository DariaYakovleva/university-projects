grammar GrammarsGrammar;


start
    : (rule | terminal)+
    ;

rule
    : LOWERNAME ':' (UPPERNAME | LOWERNAME)+ ('|' (UPPERNAME | LOWERNAME)+)* ';'
    ;

terminal
    : UPPERNAME ':'  STR ';'
    ;

UPPERNAME: ('A'..'Z') (('A'..'Z')|('0'..'9'))*;
LOWERNAME: ('a'..'z') (('a'..'z')|('0'..'9'))*;
WS: [ \t\r\n]+ -> skip;
STR : '\'' (~['\\])* '\'';