Program alarm;
var
s, t: integer;
begin
read(s, t);
if t >= s then
	writeln(t - s)
else
	writeln(t + 12 - s);
end.