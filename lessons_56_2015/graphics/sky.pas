Program avtomobil;
Uses Crt,Graph;
Var gd,gm, px,py: integer;
i, j, x, y, r, a, b: longint;
begin
gd:=Detect;
Initgraph(gd,gm,'');

r := 100;
a := 300;
b := 300;

randomize;
for i := 1 to 500 do 
begin
	for j := 1 to 100 do
	begin
		px := random(1000);
		py := random(500);
		fillellipse(px, py, 2, 2);
	end;	
	
	cleardevice;
end;


delay(5000);
end.