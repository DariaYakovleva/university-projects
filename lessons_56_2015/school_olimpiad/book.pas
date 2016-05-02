Program book;
var
n, k, page, number: integer;

begin
read(k, n);
page := n div k + 1;
number := n mod k;
if n mod k = 0 then
    begin
	page := page - 1;
	number := k;
	end;
writeln(page, ' ', number);
end.