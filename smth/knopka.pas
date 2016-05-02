
uses crt,graph;
type 

knopka=object
    cz,cl,w,h,x1,y1,r1,r2,x2,y2:integer;ac:boolean;
  procedure init(x0,y0,x4,y4,cli,cza:integer;act:boolean);
  procedure init2(x0,y0,rx,ry,cli,cza:integer;act:boolean);
  procedure draw1;
  procedure draw2;
  procedure draw_dawn;
  procedure draw_up;
    end;
panel=object(knopka)
  procedure initp(x00,y00,x44,y44,cl0,cz0:integer);
  procedure drawp;
    end;

  procedure knopka.init;
    begin
      x1:=x0;y1:=y0;x2:=x4;y2:=y4;cl:=cli;cz:=cza;ac:=act;
    end;
  procedure knopka.init2;
    begin
      x1:=x0;y1:=y0;r1:=rx;r2:=ry;cz:=cza;cl:=cli;act:=ac;
    end;
  procedure knopka.draw1; {моя кнопо4ка-квадрат}
    begin
      setcolor(2);
      if ac=true then begin
        setfillstyle(1,8);
        setcolor(15);line(x1-1,y1,x2+1,y1);line(x1,y1+2,x1,y2);
        setcolor(0);line(x1+1,y2,x2,y2);line(x2,y1+1,x2,y2);
        setcolor(7);line(x1+3,y2-3,x2-3,y2-3);line(x2-3,y1+3,x2-3,y2-3);
        bar(x1+2,y1+2,x2-3,y2-3);
      end 
      else  begin 
        setfillstyle(1,2); 
        setcolor(15);line(x1-1,y1,x2+1,y1);line(x1,y1+2,x1,y2);
        setcolor(0);line(x1+1,y2,x2,y2);line(x2,y1+1,x2,y2);
        setcolor(7);line(x1+3,y2-3,x2-3,y2-3);line(x2-3,y1+3,x2-3,y2-3);
        bar(x1+2,y1+2,x2-3,y2-3);
      end;
   end;
  procedure knopka.draw_dawn;
    begin
      setfillstyle(1,2);
      setcolor(8);line(x1,y1,x2,y1);line(x1,y1+3,x1,y2);
      setcolor(15);line(x1+3,y2,x2,y2);line(x2,y1+3,x2,y2-3);
      setcolor(7);line(x1+3,y1+3,x2-3,y1+3);line(x1+3,y1+3,x1+3,y2-3);
      bar(x1+4,y1+4,x2-3,y2-3);delay(5000);
    end;
  



procedure knopka.draw2;{а вот тут должна быть кнопка-ромб}
begin
setcolor(2);
if ac=true
then begin
setfillstyle(1,8);
setcolor(15);line(x2--1,(y2-y1) div 2,(x2-x1) div 2-1,y1);line(x2,(y2-y1) div 2+2,(x2-x1) div 2,y2);
setcolor(0);line((x2-x1) div 2+1,y2,x2,(y2-y1) div 2);line(x2,y2+1,x2,(y2-y1) div 2);
setcolor(7);line((x2-x1) div 2+3,y2-3,x2-3,(y2-y1) div 2-3);line(x2-3,y2+3,x2-3,(y2-y1) div 2-3);
  setcolor(12);
end else begin
setfillstyle(1,2);
setcolor(15);line(x2-1,(y2-y1) div 2,(x2-x1) div 2-1,y1);line(x2,(y2-y1) div 2+2,(x2-x1) div 2,y2);
setcolor(0);line((x2-x1)div 2+1,y2,x2,(y2-y1) div 2);line(x2,y2+1,x2,(y2-y1) div 2);
setcolor(7);line((x2-x1)div 2+3,y2-3,x2-3,(y2-y1) div 2-3);line(x2-3,y2+3,x2-3,(y2-y1) div 2-3);
end;end;
procedure knopka.draw_up;
begin
setfillstyle(1,2);
delay(5000);
end;


  procedure panel.initp;
   begin
     x1:=x00;y1:=y00;x2:=x44;y2:=y44;cz:=cz0;cl:=cl0;
    end;
  procedure panel.drawp;
    begin
      setfillstyle(1,cz);
      setlinestyle(0,2,3);setcolor(2);rectangle(x1,y1,x2,y2);
      floodfill(x1+5,y1+5,2);
     end;

var 
  a:array[1..3] of knopka;v:array[1..3,1..3] of real;
  p,c:panel;
  chk:char;i,j,k,z:byte;
  m:byte;gd,gm:integer;tt:string;f:text;
begin
  gd:=detect;
  initgraph(gd,gm,'d:\bp\bgi');
  cleardevice;
  setbkcolor(1);
  p.initp(120,10,560,80,4,blue);
  p.drawp;
  a[1].init(140,30,190,70,15,green,false);
  a[2].init2(280,50,25,20,15,green,false);
  a[3].init(490,30,540,70,15,green,false);
  a[1].ac:=true;
  m:=1;z:=2;
     repeat
     setbkcolor(0);
     cleardevice;
     p.drawp;
     for i:=1 to 4 do 
     begin
       if m=i then a[i].ac:=true
          else a[i].ac:=false;
       if (i=1) or (i=3) then a[i].draw1 
           else a[i].draw2;
      end;
  chk:=readkey;
  case chk of
#77:if (m=4) then m:=1 else m:=m+1;
#75:if (m=1) then m:=4 else m:=m-1;
#13:begin if (m=1) or (m=3) then a[m].draw_dawn 
         else a[m].draw_up;
case m of
1:begin {должна увеличивать объем}
   cleardevice;
   setbkcolor(1);
   p.initp(120,10,560,80,4,blue);
   p.drawp;
   a[1].init(140,30,190,70,15,green,false);
   a[2].init2(280,50,25,20,15,green,false);
   a[3].init(490,30,540,70,15,green,false);
   a[1].ac:=true;m:=1;
   end;
2:begin{открывает файл с заданием}
   c.initp(120,120,450,250,4,blue);
   c.drawp;
   assign(f,'d:\cs.txt');
   reset(f);j:=0;
   while not eof(f) do
     begin
     readln(f,tt);outtextxy(125,125+j,tt);
     j:=j+10;
     end;
   close(f);delay(65000);readkey;
   end;
3:begin {разварачивает}
   if (z mod 2)=0 then 
   begin
   p.initp(1,1,640,480,4,blue);
   a[1].ac:=true; 
   p.drawp;a[1].init(140,30,190,70,15,green,false);
   a[2].init2(280,50,25,20,15,green,false);
   a[3].init(490,30,540,70,15,green,false);
   z:=z+1; 
   end
  else 
  begin 
  p.initp(120,10,560,80,4,blue);
  p.drawp;a[1].init(140,30,190,70,15,green,false);
  a[2].init2(280,50,25,20,15,green,false);
  a[3].init(490,30,540,70,15,green,false);
  z:=z+1;
  a[1].ac:=true; 
  end;end;
end;
end;end;
until chk=#27;end.

