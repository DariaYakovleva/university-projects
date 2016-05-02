Program pirates;
var
s: string;
x, y, n, i, j: integer;

begin
read(s);
n := length(s);
x := n div 3;
y := n mod 3;
if y mod 3 = 0 then
	begin
	x := x - 1;
	y := 3;
	end;
for i := 1 to 3 do
	begin
	for j := 1 to x do
		write('*');
	writeln;
	end;
for i := 1 to y do
	write('*');
writeln;
end.