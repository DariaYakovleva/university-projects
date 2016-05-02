@echo off
	for /L %%b in (1, 1, 5000000) do (
		gen.exe
		main.exe
		main_wa.exe
		fc tower.out tower2.out
		if errorlevel 1 exit
		echo Test %%b: OK
		echo %TIME%
	)