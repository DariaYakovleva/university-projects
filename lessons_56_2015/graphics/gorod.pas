PROGRAM  Domiki;
      uses Graph;
      var  grDriver: Integer;
           grMode  : Integer;
           i,j     : Integer;
  { ------------------------------ }
   PROCEDURE  Domik (x,y: Integer);
   { Рисует домик, у которого левый нижний угол }
   { имеет координаты (x,y)                     }
      const dx=60;     { Ширина фасада }
            dy=40;     { Высота фасада }
            dx2=dx DIV 2;
            dy2=dy DIV 2;
            wx=16;     { Ширина окна }
            wy=22;     { Высота окна }
            wx2=wx DIV 2;
            wy2=wy DIV 2;
   BEGIN
      Rectangle (x,y,x+dx,y-dy); MoveTo (x,y-dy);
      Linerel (dx2,-dx2);  { Левый скат крыши }
      Linerel (dx2,dx2);   { Левый скат крыши }
      Rectangle (x+dx2-wx2,y-dy2-wy2,x+dx2+wx2,y-dy2+wy2);  { Окно }
      MoveTo (x+dx2,y-dy2);       { Центр фасада (и окна)          }
      LineRel (0,wy2);            { Вертикальная часть рамы окна   }
      MoveTo (x+dx2-wx2,y-dy2);   { Центр левой части рамы окна    }
      LineRel (wx,0);             { Горизонтальная часть рамы окна }
      SetFillStyle (SolidFill,Red);
      FloodFill (x+1,y-1,White);
      SetFillStyle (SolidFill,Blue);
      FloodFill (x+dx2,y-dy-1,White)
   END;
  { --- }
   BEGIN
      grDriver:=VGA; grMode:=VGAHi;
      InitGraph (grDriver,grMode,'..\bgi');
      If  GraphResult=grOk
         then  begin
                  For i:=1 to 6  do
                     For j:=1 to 5  do
                        Domik (i*80,j*80);
                  ReadLn
               end
   END.