uses Graph,crt;
var
  gd,gm,i,x,y:integer;
begin
  gd:=detect;
  Initgraph(gd,gm,'');
  if graphresult=grOk then begin
  setcolor(lightgreen);
      x:=getmaxx div 2;
      y:=getmaxy div 6;
      i:=1;
    for i:=1 to 3 do begin
      line(x-i*100,y+i*100,x+i*100,y+i*100);
      line(x-i*100,y+i*100,x,y+(i-1)*100);
      line(x+i*100,y+i*100,x,y+(i-1)*100);
    end;
    setcolor(Brown);
    rectangle(x-20,y+i*100,x+20,getmaxy);
  end;
  repeat until keypressed;
  closegraph;
end.