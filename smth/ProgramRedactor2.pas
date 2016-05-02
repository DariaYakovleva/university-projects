Program q;
Uses GraphABC;
Const
   Size=20; 
Var
    WW,WH,XS,YS,Kol,c:Integer;
    ch,ch1,ch2:char;
    
Procedure Setka(x,y,Kol,Size:Integer);
Var
   i:Integer;
Begin
   For i:=0 to Kol do
  Begin
   Line(x, y + i * Size, x + Size * Kol, y + i * Size);
   Line(x + i * Size, y, x + Size * i, y + Size * Kol);
  End;
End;

Procedure KeyPress(chp:Char);
Begin
    Case chp Of
    'r':SetBrushColor(clred);
    'y':SetBrushColor(clyellow);    
    'g':SetBrushColor(clgreen);
    End;
    ch := chp;
end;
 
Procedure MouseDown(x, y, mb:Integer);
Begin
   If ((x-xs) mod Size<>0) and ((y-ys) mod Size<>0)
   and (x>xs) and (X<xs+Size*kol) and 
    (y>ys) and (y<ys+Size*kol) then
   If mb = 1 then
   Begin
      Floodfill(x,y,clblue);
      if (ch = 'r') then 
      Begin
            SetBrushColor(clred);
            Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
            Rectangle((x-xs),(y-ys), x, y);            
      End;
      if (ch = 'y') then 
      Begin
            SetBrushColor(clyellow);
            Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
            Rectangle((x-xs),(y-ys), x, y);            
      End;
      if (ch = 'g') then 
      Begin
            SetBrushColor(clgreen);
            Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
            Rectangle((x-xs),(y-ys), x, y);            
      End;
   End
   else 
      Floodfill(x,y,clwhite);
End;
Procedure MouseMove(x,y,mb:Integer);
Begin
  If ((x-xs) mod Size<>0) and ((y-ys) mod Size<>0)
   and (x>xs) and (X<xs+Size*kol) and 
    (y>ys) and (y<ys+Size*kol) then
    Begin 
      If mb = 1 then
        Begin
          Floodfill(x,y,clblue);
          if (ch = 'r') then 
            Begin
              SetBrushColor(clred);
              Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
              Rectangle((x-xs),(y-ys), x, y);            
              End;
          if (ch = 'y') then 
            Begin
               SetBrushColor(clyellow);
               Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
               Rectangle((x-xs),(y-ys), x, y);            
            End;
          if (ch = 'g') then 
          Begin
              SetBrushColor(clgreen);
              Rectangle((x-xs) div Size*Size-1,(y-ys) div Size*Size-1,(x-xs) div Size*Size+1,(y-ys) div Size*Size+1);
              Rectangle((x-xs),(y-ys), x, y);
          End;

        End;
      end;
   If mb = 2 then
      Floodfill(x,y,clwhite);
End;
Begin
  CenterWindow;
  WW:=WindowWidth;
  Wh:=WindowHeight;
  Kol:=WH div Size;
  Xs:=(WW - Kol * Size) div 2;
  ys:=(Wh - Kol * Size) div 2;
  xs:=100;
  ys:=100;
  ch:= 'w';
  write(xs, ' ', ys);
  Setka(xs,ys,Kol,Size);
  OnMouseDown:=MouseDown;
  OnMouseMove:=MouseMove;
  OnKeyPress:=KeyPress;
End.