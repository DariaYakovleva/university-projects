Program Stena;
Uses GraphABC;
Const 
       Kw=20;
       KH=10;
       W=30;
       H=30;
Var 
    i, j, x, y: Integer;
Begin
    CenterWindow;
    x := W div 2 - Kw div 2;
    y := 0;
    SetBrushColor(clGold);
    For i:=1 to h do
    Begin
    	x := x - Kw div 2;
    	y := y + Kh;
    	x1 := x;
    	y1 := y;
     	For j := 1 to i do
      	Begin
       		Rectangle(x1, y1, x1 + kw, y1 + kh);
         	x1 := x1 + kw;
      	End;
     End;
End.