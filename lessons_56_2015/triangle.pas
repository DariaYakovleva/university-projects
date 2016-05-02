Program triangle;
var
n, cnt, i: integer;
begin
read(n);
cnt := 1;
while cnt <= n do 
	begin
	for i := 1 to ((n - cnt) div 2) do
		write(' ');
	for i := 1 to cnt do
		write('*');
	writeln();
	cnt := cnt + 2;
	end;
end.
	