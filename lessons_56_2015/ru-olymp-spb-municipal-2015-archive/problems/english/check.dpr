{ Copyright(c) SPb-IFMO CTD Developers, 2000 }
{ Copyright(c) Roman Elizarov, 1997 }

{ $Id: fcmp2.dpr,v 1.1 2001/11/19 15:06:01 sta Exp $ }

(*
   FILE COMPARE (VERSION WITH LONG LINES SUPPORT)
   Test program for exact comparision of files.
   (all trailing spaces are ignored only if lines are short).
*)

program FCMP2;

uses
  testlib, sysutils;

var
  ja, pa: string;
  i: longint;
  l: longint;

function compress(s: string): string;
begin
	if length(s) < 1000 then
		compress := s
	else 
		compress := copy(s, 1, 500) + '...[' + inttostr(length(s) - 1000) + ' chars]...' + copy(s, length(s) - 500 + 1, 500);
end;

begin 
  l := 0;
  while not ouf.eof and not ans.eof do begin
    inc(l);
    pa := ouf.readstring;
    ja := ans.readstring;
    i := length(pa);
    while (i > 0) and (pa[i] = ' ') do dec(i);
    delete(pa, i + 1, length(pa) - i);
    i := length(ja);
    while (i > 0) and (ja[i] = ' ') do dec(i);
    delete(ja, i + 1, length(ja) - i);
    if pa <> ja then
        quit(_wa, 'Line ' + inttostr(l) + ' - expected: "' + compress(ja) + '", found: "' + compress(pa) + '"');
  end;
  if ouf.seekEof and not ans.seekEof then begin
    QUIT (_WA, 'Not enough lines! "' + compress(ans.readString) + '" expected' );
  end;
  if ans.seekEof and not ouf.seekEof then begin
    QUIT (_WA, 'Extra lines! "' + compress(ouf.readString) + '" encountered' );
  end;
  QUIT ( _OK, 'Ok' );
end.