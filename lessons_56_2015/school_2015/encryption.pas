Program encryption;
var
ss: string;

function encr(s: string) : string;
var
	a, b: string;
	pos: integer;
begin
	if length(s) < 2 then
		encr := s
	else 
		begin
		pos := length(s) div 2;
		a := copy(s, 1, pos);
		b := copy(s, pos + 1, length(s) - pos);
		encr := encr(b) + encr(a);
		end;
end;

begin
read(ss);
writeln(encr(ss));
end.