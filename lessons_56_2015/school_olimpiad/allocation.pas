Program allocation;
var
a, b, c, x, y: integer;
begin
read(a, b, c);
if 3 * a < a + b then
	writeln(0, ' ', c)
else if 3 * b < a + b then
	writeln(c, ' ', 0)
else 
	begin
	x := c * (2 * a - b) div (a + b);
	y := c - x;
	writeln(x, ' ', y);
	end;
end.
