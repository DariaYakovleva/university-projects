var
  tt, qq, i: longint;
  s, st: string;
  flag: boolean;
begin
  readln(tt);
  for qq:=1 to tt do
  begin
    readln(s);
    if s[1] <= 'Z' then
    begin
      s[1]:=Chr(Ord(s[1]) + 32);
      flag:=True;
    end
    else flag:=False;
    if s[1] = 'e' then s:='a' + s;
    st:='';
    for i:=1 to length(s) do
      if (s[i] = 's') and ((i = length(s)) or (s[i + 1] <> 'h')) and (i > 1) then st:=st + 'th'
      else st:=st + s[i];
    s:=st;
    st:='';
    for i:=1 to length(s) do
      if (i > 1) and (s[i] = 'o') and (s[i - 1] = 'o') and ((i = 2) or (s[i - 2] <> 'o')) then st:=st + 'u'
      else st:=st + s[i];
    s:=st;
    if flag then
    begin
      s[1]:=Chr(Ord(s[1]) - 32);
    end;
    writeln(s);
  end;
end.
