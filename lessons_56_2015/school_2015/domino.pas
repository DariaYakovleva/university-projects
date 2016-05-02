Program domino;
var
n, m:integer;
begin
read(n, m);
if (n mod 2 = 0) or (m mod 2 = 0) then
	writeln(n * m div 2)
else
	writeln('-1');
end.