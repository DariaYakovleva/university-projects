
{Made by DSBM}
program DSBM;
uses graph, crt;
var dr, md: integer;
    i, j, x, y: integer;
begin
	clrscr;
	x := succ(0);
	y := succ(0);
	window(x, y, x + 20, y + 20);
	clrscr;
	gotoxy(6,2);
	dr:=0;
	md:=0;
	initgraph(dr, md, '');
	setBkColor(white);
	setcolor(green);
	SetFillStyle(1, green);
	{первый треугольник Ёлочки}
	line(100,50,50,100);
	line(50,100,150,100);
	line(150,100,100,50);
	floodfill(52,99,green);

	{второй треугольник Ёлочки}
	line(100,100,50,150);
	line(50,150,150,150);
	line(150,150,100,100);
	floodfill(52,149,green);

	{третий треугольник Ёлочки}
	line(100,150,50,200);
	line(50,200,150,200);
	line(150,200,100,150);
	floodfill(52,199,green);
	
	{ствол дерева}
	setfillstyle(1, brown);
	setcolor(brown);
	line(90,201,90,240);
	line(90,240,110,240);
	line(110,240,110,201);
	line(110,201,90,201);
	floodfill(100,210,brown);

	{шарики}
	for i := 1 to 15 do
	begin 
		setcolor(i);
		setfillstyle(1, i);
		fillellipse(150,105,4,4);
		fillellipse(50,155,4,4);
		fillellipse(150,205,4,4);
		fillellipse(50,105,4,4);
		fillellipse(150,155,4,4);
		fillellipse(50,205,4,4);
		delay(700);
	end;
end.
