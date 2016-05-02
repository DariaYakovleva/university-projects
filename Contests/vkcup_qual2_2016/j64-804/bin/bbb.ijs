dd0 =: 4 : 0
 i=.0
 n=.#x.
 z=.(1<.n){.d=.y.
 while. n>i=.1+i do.
  dx=.(-i)}.(i|.x.)-x.
  dy=.2 -~/\ d
  d =.dy%dx
  z =.z,{.d
 end.
)
   x=.0 1 5 8
   y=.4 6 18 6
   x dd0 y