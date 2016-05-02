Program graphika2;
uses crt, graph;
Var
gd, gm, i: Integer;
s: String;
begin
gd := detect;
s := ' ';
Initgraph(gd, gm, s);
line(0, 0, 300, 300);
delay (10000);
end.


