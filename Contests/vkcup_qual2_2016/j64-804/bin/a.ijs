print =: 1!:2&2
read =: 1!:1[3

v =. (read-.LF)-.CR
b =. +/ ". v
ans =. ((b * (b + 1)) % 2) + 1
print ans

exit ''
