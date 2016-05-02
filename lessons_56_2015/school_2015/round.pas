Program round;
var
i, a, b, left, right: longint;
ok: boolean;
begin
for i := 1 to 10 do
	begin
	read(a, b);
	if b < a then 
		begin
		writeln('NO');
		continue;
		end;
	left := a;
	right := a;
	ok := false;
	while b > right do
		begin
		left := (left * 3) div 2;
		right := (right * 3 + 1) div 2; 
		end;
	if (b >= left) and (b <= right) then
		writeln('YES')
	else
		writeln('NO');
	end;
end.