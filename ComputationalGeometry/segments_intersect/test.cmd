@echo off
for /L %%i in (1, 1, 10000) do (
gen.exe > input.in
main.exe > out1.out
main2.exe > out2.out
fc out1.out out2.out
if errorlevel 1 exit
echo Test %%i: OK
echo %TIME%
)