Program avtomobil;
Uses Crt,Graph;
Var gd,gm,i,k,x:integer;
begin
gd:=Detect;
Initgraph(gd,gm,'');
SetBkColor(green);  {fon}
setfillstyle(1,4);   {kyzov}
bar(40,430,160,375);
setfillstyle(1,8); {glyschak}
bar(39,420,25,428);
setfillstyle(1,5);  {gryz 1}
bar(49,375,70,350);
setfillstyle(1,3);   {gryz 2}
bar(75,374,90,360);
setfillstyle(1,12);  {gryz 3}
bar(100,374,140,330);
setfillstyle(1,red);  {kabina(1)}
bar(160,430,210,340);
setcolor(red);
setfillstyle(1,red); {nos kabini(2)}
bar(205,430,265,380);
setfillstyle(1,8); {bamper}
bar(265,430,250,425);
MoveTo(205,375);{kapot}
LineTo(260,375);
LineTo(265,380);
LineTo(210,380);
LineTo(205,375);
MoveTo(220,376);
setfillstyle(1,red);
floodfill(227,377,red);
setcolor(white); {kapot obveden}
MoveTo(265,380);
LineTo(260,375);
LineTo(210,375);
LineTo(215,380);
LineTo(265,380);
setcolor(black);{peregorodka m/d kuzovom i kabinoj}
MoveTo(160,375);
LineTo(160,430);
LineTo(160,375);
setfillstyle(1,3); {okno}
bar(204,380,165,345);
setcolor(cyan);  {niz dveri}
MoveTo(165,420);
LineTo(170,420);
LineTo(204,380);
LineTo(165,380);
LineTo(165,420);
floodfill(170,415,cyan);
setfillstyle(1,green); {okno v okne}
bar(170,377,199,348);
setcolor(6); {zadnee koleso}
Circle(80,438,20);
setfillstyle(1,6);
floodfill(80,438,6);
setcolor(6);        {perednee koleso}
Circle(210,438,20);
setfillstyle(1,6);
floodfill(210,438,6);
setcolor(14);    {solnze}
Circle(20,20,70);
setfillstyle(1,14);
floodfill(20,20,14);
readln;
end.