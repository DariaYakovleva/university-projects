Program avtomobil;
Uses Crt,Graph;
Var gd,gm, px,py: integer;
i, k, x, y, r, a, b: longint;
begin
gd:=Detect;
Initgraph(gd,gm,'');

setcolor(4);
setlinestyle(1, 4, 4);
setfillstyle(1, 3);
bar3d(100, 100, 200, 200, 100, true);
bar3d(100, 100, 200, 200, 100, true);

delay(5000);
end.