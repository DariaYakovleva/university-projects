const
  maxn = 444;
  inf = round(1e9);
var
  q, ft, n, k, a, b, i, j: longint;
  x: array [0..maxn] of longint;
  f: array [0..maxn, 0..maxn] of longint;
begin
  readln(n, k, a, b);
  for i:=1 to n do read(x[i]);
  for i:=0 to n do
    for j:=0 to k do f[i][j]:=inf;
  f[0][0]:=0;
  for i:=0 to n - 1 do
    for j:=0 to k - 1 do
      if f[i][j] < inf then
      begin
        for q:=i + a to i + b do
        begin
          if q > n then break;
          ft:=x[q] - x[i + 1];
          if f[i][j] > ft then ft:=f[i][j];
          if ft < f[q][j + 1] then f[q][j + 1]:=ft;
        end;
      end;
  writeln(f[n][k]);
end.
