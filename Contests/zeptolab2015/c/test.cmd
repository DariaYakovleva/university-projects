@echo off
for /L %%i in (1, 1, 10) do (
generator.exe %%i
main.exe
)