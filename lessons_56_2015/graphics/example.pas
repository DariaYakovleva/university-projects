Program graphika2;
uses crt, graph;
Var
gd, gm, i: Integer;
s: String;
begin
gd:=detect;
s:=' ';
Initgraph(gd, gm, s);
setBkColor(green);
randomize;
for i := 1 to 100 do
    begin
    setcolor(random(15));
	line(random (200), random (200), random (200), random (200));
	delay(100);
	end;
delay (10000);
end.


