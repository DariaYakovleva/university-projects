Program avtomobil;
Uses Crt,Graph;
Var gd,gm, px,py: integer;
i, k, x, y, r, a, b: longint;
begin
gd:=Detect;
Initgraph(gd,gm,'');

//setlinestyle(4, 4, 4);
//bar3d(100, 100, 200, 200, 100, true);
r := 100;
a := 300;
b := 300;
	
randomize;
for i := a-r to a+r do
begin
	
	setfillstyle(0, 15);
//	circle(a, b, 100);
//	circle(a, b, 200);
	setcolor(15);
	setfillstyle(1, 15);
	for k := 1 to 0 do 
	begin
		px := random(1000);
		py := random(500);
		fillellipse(px, py, 2, 2);
	end;

	setcolor(14);
	setfillstyle(1, 14);
	fillellipse(a, b, 50, 50);

	r := 100;
	x := i;
	y := round(sqrt(r * r - (x - a) * (x - a))) + b;
	setcolor(9);
	setfillstyle(1, 9);
	fillellipse(x, y, 20, 20);

	r := 200;
	x := i;
	y := round(sqrt(r * r - (x - a) * (x - a))) + b;
	setcolor(8);
	setfillstyle(1, 8);
	fillellipse(x, y, 20, 20);

	r := 300;
	x := i;
	y := round(sqrt(r * r - (x - a) * (x - a))) + b;
	setcolor(10);
	setfillstyle(1, 10);
	fillellipse(x, y, 20, 20);

	delay(100);
	clearViewPort;
end;



delay(5000);
end.