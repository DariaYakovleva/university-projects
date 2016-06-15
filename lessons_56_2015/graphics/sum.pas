Program Space; {составил студент Тетуев Р., мат.фак. КБГУ}
  Uses Graph, Crt;
  Const
    RadOrb = 250 {радиус орбиты Земли}; RadSun = 70 {радиус Солнца};
    RadGal = 100 {радиус галактики }; RadZem = 18 {радиус Земли };
    Naklon = 0.2 {коэффициент наклона плоскости орбиты Земли};
    PressZem = 0.65 {коэффициент сплющенности полюсов Земли};
    Compress = 0.8 {коэффициент сжатия при переходе из };
                   {расширения режима VGA в режим CGA }
  Var
    ZemX, ZemY, UgMer, PixelY, DUgZem , UpDown,
    XRad, Grad, UgZem, PixelX, StAngle, Ua, Ub,
    ParallelY , Color, ZemPix, EndAngle,
    VisualPage, GrMode, GrError, GrDriver, i : Integer;
    Ugol, CompressZem, Expansion,
    DUgol, Projection, PolUgol : Real;
BEGIN
  {установка графического режима и проверка возможных ошибок}
  GrDriver := EGA; GrMode := EGAHi;
  InitGraph(GrDriver, GrMode, 'C:\TP\BGI');
  GrError := GraphResult; If GrError<>GrOk then Halt;
  SetBkColor(Black);
  SetFillStyle(1, Yellow); {установка стиля заполнения и цвета Cолнцa}
  Ugol := 0; DUgol := 2*Pi/180; {орбитальное угловое смещение Земли}
  UgZem := 0; DUgZem := 14; {осевое угловое смещение Земли}
  {------------------------------------------------------------------}
  VisualPage := 1;
  Repeat {цикл прерывается нажатием любой клавиши}
    SetVisualPage(1- (VisualPage mod 2));
         {установка номера видимой видеостраницы}
    VisualPage := VisualPage+1; {листание видеостраниц}
    SetActivePage(1 - (VisualPage mod 2));
         {установка номера невидимой (активной) видеостраницы,}
         {используемой для построения смещенного изображения }
    ClearDevice; {очистка графического экрана}
     {--------------------------------------------------------------}
    {Рисование "расходящейся" галактики}
    RandSeed:=1; {исходное значение датчика случайных чисел}
    Expansion:=VisualPage/100; {cкорость расширения галактики}
    For i:= 1 to VisualPage do
      begin XRad := Trunc(Expansion*RadGal*Random);
             {текущее расстояние от звезды до центра галактики}
        PolUgol:= 2*Pi*Random-VisualPage/30;
             {текущий центральный угол положения звезды галактики}
        PixelX := 370+Trunc(XRad*cos(PolUgol+1.8)); {координаты}
        PixelY := 250+Trunc(XRad*0.5*sin(PolUgol)); { звезды }
        PutPixel(PixelX, PixelY, White) {рисование звезды}
      end;
     {--------------------------------------------------------------}
    {Рисование мерцающих звезд}
    Randomize; {инициализация датчика случайных чисел}
    For i:=1 to 70 do
      PutPixel(Random(640),Random (350),White); {вспыхивающие звезды}
     {--------------------------------------------------------------}
    For i := 1 to 100 do {Рисование орбиты}
      PutPixel(320+Round(RadOrb * cos((i+VisualPage/5)*Pi/50+0.3)),
      160+Round(RadOrb*Naklon*sin((i+VisualPage/5)*Pi/50-Pi/2)),15);
     {--------------------------------------------------------------}
    PieSlice(310, 160, 0, 360, RadSun); {Рисование Солнца}
     {--------------------------------------------------------------}
    {Рисование Земли (ее параллелей и меридианов)}
    Ugol := Ugol+DUgol ; {угол поворота Земли относительно Солнца}
    Grad := Round(180*Ugol/Pi) mod 360; {в рад.(Ugol) и в град.(Grad)}
    ZemX := 320+Round(RadOrb*cos((Ugol+Pi/2+0.3))); { координаты }
    ZemY:=160+Round(RadOrb*Naklon*sin(Ugol)); {центра Земли}
    CompressZem := 2.5-cos(Ugol+0.3);
           {коэффициент учета удаленности Земли от наблюдателя}
    ZemPix := Round(RadZem*CompressZem); {текущий радиус Земли}
    UgZem := UgZem+DUgZem; {угол поворота Земли относительно своей оси}
    For i := 0 to 11 do { рисование меридианов }
      begin
        UgMer := (UgZem+i*30) mod 360;
        If (90<UgMer) and (UgMer<270) {установка начального и конечного}
          then begin StAngle := 90; EndAngle := 270 end { углов дуги }
          else begin StAngle := 270; EndAngle := 90 end; {эллипса меридиана}
        Ua := (Grad+220) mod 360; Ub := (Grad+400) mod 360;
           {установка цветов рисования затененной и освещенной
            частей меридиана}
        Color := LightBlue;
        If Ua<=Ub then if (Ua<UgMer) and (UgMer<Ub) then Color := White;
        If Ua >Ub then if (Ua<UgMer) or (UgMer<Ub) then Color := White;
        SetColor(Color);
        XRad := round((ZemPix*cos(UgMer*Pi/180))); 
        Ellipse(ZemX,ZemY,StAngle,EndAngle,abs(XRad),round(PressZem*ZemPix));
      end;
    For i := 2 to 7 do {рисование параллелей}
      begin
        XRad := abs(Round(ZemPix*sin(i*Pi/9)));
           {большая полуось эллипса параллели}
        UpDown := Round(ZemPix*PressZem*cos(i*Pi/9));
           {высота параллели над плоскостью экватора}
        ParallelY := ZemY+UpDown; {координата Y центра эллипса параллели}
        SetColor(LightBlue);
        Ellipse(ZemX, ParallelY, 0, 360, XRad, Round(Naklon*XRad));
           {затененная часть параллели}
        SetColor(White);
        Ellipse(ZemX,ParallelY,Grad+220,Grad+400,XRad,Round(Naklon*XRad));
           {освещенная часть параллели}
      end;
     {------------------------------------------------------------------}
    {Повторное рисование Cолнца, если оно ближе к наблюдателю, чем Земля}
    If CompressZem<2 then PieSlice(310, 160, 0, 360, RadSun);
     {------------------------------------------------------------------}
    RandSeed := VisualPage mod 12;
    For i := 1 to 250 do {Рисование протуберанцев}
      begin
        Projection := (1-sqr(Random))*Pi/2;
        XRad := RadSun+Round((20)*sin(Projection))-15;
        PolUgol := 2 * Pi * Random+VisualPage/20;
        {PolUgol, XRad - полярные координаты протуберанца}
        PixelX := 310 + Round( XRad * cos(PolUgol));
        PixelY := 160 + Round( Compress * XRad * sin(PolUgol));
        PutPixel(PixelX, PixelY, LightRed)
      end;
  until KeyPressed 
END.