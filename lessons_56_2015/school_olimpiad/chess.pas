Program chess;
var
	dx: array[1..4] of integer = (-2, -1, 1, 2);
	dy: array[1..4] of integer = (-2, -1, 1, 2);
	ans_x, ans_y: array[1..1000] of integer;
	ans2_x, ans2_y: array[1..1000] of integer;
	i, j: integer;
	sx, sy: integer;
	cnt, cnt2, tmp: integer;
	c: char;
	
procedure step(x: integer; y: integer);
begin
	for i := 1 to 4 do 
		begin
		for j := 1 to 4 do 
			if abs(dx[i]) + abs(dy[j]) = 3 then
				begin
				if ((x + dx[i] >= 1) and (x + dx[i] <= 8)) and ((y + dy[j] >= 1) and (y + dy[j] <= 8)) then
					begin
					ans_x[cnt] := x + dx[i];
					ans_y[cnt] := y + dy[j];
					cnt := cnt + 1;
					end;
				end;
		end;	
end;

procedure sort(size: integer);
begin
	for i := size downto 1 do 
		begin
		for j := 1 to i - 1 do 
			begin
			if (ans_y[j] > ans_y[j + 1]) or ((ans_y[j] = ans_y[j + 1]) and (ans_x[j] > ans_x[j + 1])) then
				begin
				tmp := ans_x[j];
				ans_x[j] := ans_x[j + 1];
				ans_x[j + 1] := tmp;
				tmp := ans_y[j];
				ans_y[j] := ans_y[j + 1];
				ans_y[j + 1] := tmp;
				end;
			end;
		end;
end;

begin
	read(c, sy);
	sx := ord(c) - ord('A') + 1;
	cnt := 1;
	step(sx, sy);
	sort(cnt - 1);
	for i := 1 to cnt - 1 do
		if (i = 1) or (ans_x[i] <> ans_x[i - 1]) or (ans_y[i] <> ans_y[i - 1]) then
			begin
			write(char(ord('A') - 1 + ans_x[i]), ans_y[i], ' ');
			ans2_x[i] := ans_x[i];
			ans2_y[i] :=  ans_y[i];
			end;
	writeln;
	cnt2 := cnt;
	cnt := 1;
	writeln(cnt2);
	for i := 1 to cnt2 - 1 do
		begin
		step(ans2_x[i], ans2_y[i]);
		end;
	sort(cnt - 1);
	for i := 1 to cnt - 1 do
		if (i = 1) or (ans_x[i] <> ans_x[i - 1]) or (ans_y[i] <> ans_y[i - 1]) then
			write(char(ord('A') - 1 + ans_x[i]), ans_y[i], ' ');
	writeln;
end.


