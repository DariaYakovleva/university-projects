@echo off
for /L %%i in (1, 1, 10000) do (
generator.exe
main.exe
main_Yakovleva.exe
fc teleports.out teleports2.out
if errorlevel 1 exit
echo Test %%i: OK
echo %TIME%
)