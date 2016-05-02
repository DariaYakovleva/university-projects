Program matches;
var
n, i: integer;
win: boolean;
dp: array[0..10010] of boolean;

begin
read(n);
for i := 0 to n do
	dp[i] := false;
for i := 1 to n do
    begin
	win := false;
	if (dp[i - 1] = false) or ((i >= 2) and (dp[i - 2] = false)) or ((i >= 1000) and (dp[i - 1000] = false)) then
		win := true;
	dp[i] := win
	end;
if dp[n] = true then
	writeln(1)
else
	writeln(2);
end.