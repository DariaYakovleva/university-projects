var
  n, k, a, b: longint;
begin
  read(n, k, a, b);
  writeln(((k - 1) + (n - 1)) * b, ' ', (n - 1) * a);
end.
