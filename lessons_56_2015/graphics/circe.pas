
Program Graphika10;
Uses crt, Graph;
var 
i: integer;
gd, gm: Integer;
s: String;
begin
gd := detect;
s := ' ';
Initgraph(gd, gm, s); 
SetBkColor(green); //цвет фона
SetLineStyle(3, 0, 3); //тип 0..4, образец 0..3, толщина 1 или 3
for i := 0 to 11 do
begin
SetFillStyle(i, i); //тип закраски и ее цвет
circle(100 + i * 100, 100 + i * 100, 100);
bar (500, 100, 900, 500); // прямоугольник с кординатам верхнего левого угла и нижнего правого
delay(1000); // задержка
end;
readln;
closegraph
end.



