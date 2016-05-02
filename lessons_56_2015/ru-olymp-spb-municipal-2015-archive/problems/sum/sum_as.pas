var
  n, x, i, l, r, mid, ans: longint;
  a: array [0..100010] of longint;
 
procedure Sort(l, r: longint);
var
  i, j, x, tmp: longint;
begin
  i:=l; j:=r;
  x:=a[l + random(r - l + 1)];
  repeat
    while a[i] < x do inc(i);
    while x < a[j] do dec(j);
    if i <= j then
    begin
      tmp:=a[i]; a[i]:=a[j]; a[j]:=tmp;
      inc(i); dec(j);
    end;
  until i > j;
  if l < j then Sort(l, j);
  if i < r then Sort(i, r);
end;
 
begin
  readln(n, x);
  for i:=1 to n do read(a[i]);
  Sort(1, n);
  ans:=0;
  for i:=2 to n do
  begin
    l:=0; r:=i - 1;
    while l < r do
    begin
      mid:=(l + r + 1) shr 1;
      if a[i] + a[mid] > x then r:=mid - 1
      else l:=mid;
    end;
    if (l > 0) and (a[i] + a[l] > ans) then ans:=a[i] + a[l];
  end;
  writeln(ans);
end.
