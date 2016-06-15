program PythTree;
uses Graph, CRT;
const
	max = 3;
  
var
	gd, gm : Integer;
procedure LineTo1(x, y : Integer; l, u : Real);
begin
	Line(x, y, Round(x + l * cos(u)), Round(y - l * sin(u)));
end;

procedure Draw(x, y : Integer; l, u : real);
begin
	if KeyPressed then
		exit;
	if l > max then
	begin
		l := l * 0.7;
		LineTo1(x, y, l, u);
		x := Round(x + l * cos(u));
		y := Round(y - l * sin(u));
		Draw(x, y, l, u + pi / 4); {”гол поворота 1}
		Draw(x, y, l, u - pi / 6); {”гол поворота 2}
	end;
end;

begin
   gd := Detect;
   InitGraph(gd, gm, 'c:\bp\bgi');
   Draw(320, 460, 200, pi/2);
   ReadKey;
   CloseGraph;
end.