program fs;

uses Math;

function countX(x : int64): int64;
var                        
	cnt, maxX, i : longint;
begin
	if (x < 0) then
		countX := 0;
	cnt := round(power((x / 36000.0), 1.0/6));
	maxX := 0;
	for i := cnt - 5 to cnt + 5 do
		if sqr(sqr(i) * i) * 36000 <= x then
			maxX := i;
	countX := maxX + 1;
end;

var
	l, r: int64;
	t, f: text;
begin
	assign(f, 'exam.in');
	reset(f);
	assign(t, 'exam.out');
	rewrite(t);
	read(f, l, r);
	writeln(t, 'dsf');
	writeln(t, countX(r) - countX(l - 1));
	close(f);
	close(t);
end.
