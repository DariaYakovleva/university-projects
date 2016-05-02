
{Made by DSBM}
program DSBM;
uses graph, crt;
var dr, md: integer;
    i, j, x, y: integer;
begin
	clrscr;
	dr:=0;
	md:=0;
	initgraph(dr, md, '');
	{шарики}
	x := 1;
	y := 1;
	while (x < 1000) do
	while (y < 1000) do
	begin 
		setcolor(i);
		setfillstyle(1, (x + y) mod 15);
		fillellipse(x, y, 100, 100);
		setfillstyle(1, (x + y + 1) mod 15);
		fillellipse(x + 50, y + 50, 70, 70);
		setfillstyle(1, (x + y + 2) mod 15);
		fillellipse(x + 100, y + 110, 70, 70);
		setfillstyle(1, (x + y + 3) mod 15);
		fillellipse(x + 150, y + 160, 70, 70);
		delay(50);
		x := x + 50;
		y := y + 50;
	end;
end.
