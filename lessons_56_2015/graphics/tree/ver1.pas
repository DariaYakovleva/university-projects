program elka;
uses
graph, crt;
var
g,h: integer;

begin
g := detect;
initgraph(g,h,'EGAVGA.BGI');
setcolor(2);
line(200,210,220,230);
line(220,230,180,230);
line(180,230,200,210);

line(200,230,230,250);
line(230,250,170,250);
line(170,250,200,230);

line(200,250,250,270);
line(250,270,150,270);
line(150,270,200,250);

line(200,270,270,290);
line(270,290,130,290);
line(130,290,200,270);

line(200,290,290,310);
line(290,310,110,310);
line(110,310,200,290);

readkey;

end.