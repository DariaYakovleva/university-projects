program Pyth;
uses CRT, Graph;
procedure Draw(x, y, l, a: Real);
procedure Rect(x1, y1, l: Integer; a1: Real);
begin
	MoveTo(x1, y1);
	LineTo(x1 + Round(l * cos(a1)), y1 - Round(l * sin(a1)));
	LineTo(x1 + Round(l * sqrt(2) * cos(a1 + pi/4)), 
		y1 - Round(l * sqrt(2) * sin(a1 + pi/4)));
	LineTo(x1 + Round(l * cos(a1 + pi/2)), y1 - Round(l * sin(a1 + pi/2)));
	LineTo(x1, y1);
end;

begin
	if l > 4 then
	begin
		Rect(Round(x), Round(y), Round(l), a);
		Draw(x - l*sin(a), y - l * cos(a), l / sqrt(2), a + pi / 4);
		Draw(
			x - l * sin(a) + l / sqrt(2) * cos(a + pi/4),
			y - l * cos(a) - l / sqrt(2) * sin(a + pi/4), 
			l / sqrt(2), 
			a - pi/4);
	end;
end;

var
	gd, gm: Integer;
begin
	gd := detect;
	InitGraph(gd, gm, 'c:\bp\bgi');
	Draw(280, 460, 100, 0);
	ReadKey;
	CloseGraph;
end.