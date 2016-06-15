Program avtomobil;
Uses Crt,Graph;
Var gd,gm, px,py, col: integer;
i, k, x, y, r, a, b: longint;
begin
gd:=Detect;
Initgraph(gd,gm,'');

a := 400;
b := 400;

for i := 1 to 100 do
begin
	r := 300;
	while (r > 10) do
	begin	
		col := random(15);
		setcolor(col);
		setfillstyle(1, col);
		fillellipse(a, b, r, r);
		r := r - 1;
	end;
end;



delay(5000);
end.