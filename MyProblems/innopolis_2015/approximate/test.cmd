@echo off
for /L %%a in (1, 1, 1) do (
	for /L %%b in (1, 1, 5000000) do (
		generator.exe %%a %%b %%b
		ok_solution_100000_DY.exe
		approximate_mitya_double.exe
		fc approximate.out approximate2.out
		if errorlevel 1 exit
		echo Test %%a %%b %%n: OK
		echo %TIME%
	)	
)