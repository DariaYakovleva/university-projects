Program avtomobil;
Uses Crt,Graph;
Var gd,gm,i,k,x:integer;
begin
gd:=Detect;
Initgraph(gd,gm,'');
setfillstyle(1,4);   {kyzov}
bar(0,0,100,100);
fillellipse(0, 0, 100, 100);
delay(1000);
clearDevice;
delay(1000);
end.