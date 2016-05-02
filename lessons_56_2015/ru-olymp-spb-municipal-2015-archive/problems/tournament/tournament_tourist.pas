const
  maxn = 444;
var
  n, k, i, x, y, after, xx, yy: longint;
  a: array [0..maxn] of longint;
  can: boolean;
begin
  readln(n, k);
  x:=0; y:=0;
  for i:=1 to k do
  begin
    read(a[i]);
    if a[i] = 1 then inc(x)
    else inc(y);
  end;
  after:=-1;
  xx:=0; yy:=0;
  for i:=1 to k do
  begin
    if a[i] = 1 then inc(xx)
    else inc(yy);
    if (xx > y) or (yy > x) then
    begin
      after:=i;
      break;
    end;
  end;
  writeln(after);
  xx:=0; yy:=0;
  for i:=1 to k do
  begin
    if i > 1 then write(' ');
    can:=False;
    if ((xx + 1 = n) or (yy + 1 = n)) and (i < k) then can:=True;
    if ((x = xx) or (y = yy)) and (i > after) then can:=True;
    if can then write('1')
    else write('0');
    if a[i] = 1 then inc(xx)
    else inc(yy);
  end;
  writeln;
end.
