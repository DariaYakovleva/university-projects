rem   *** validation ***
call scripts\run-validator-tests.bat
call scripts\run-checker-tests.bat

rem    *** tests ***
md tests
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 153 20 15" "tests\09" 9
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 162 30 16" "tests\10" 10
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 1731 40 37" "tests\11" 11
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 1834 50 38" "tests\12" 12
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 21423 80 61" "tests\13" 13
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 2323 99 98" "tests\14" 14
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 234 100 96" "tests\15" 15
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5350 750 550" "tests\16" 16
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5541 760 351" "tests\17" 17
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5650 700 698" "tests\18" 18
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5667 800 669" "tests\19" 19
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5667 900 767" "tests\20" 20
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5667 1000 800" "tests\21" 21
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5667 1000 1000" "tests\22" 22
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 5667 800 367" "tests\23" 23
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900000 1000000 900000" "tests\27" 27
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900001 1000000 900001" "tests\28" 28
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900005 1000000 900005" "tests\29" 29
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900006 1000000 900006" "tests\30" 30
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900020 1000000 900020" "tests\31" 31
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900026 1000000 900026" "tests\32" 32
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900027 1000000 900027" "tests\33" 33
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900028 1000000 900028" "tests\34" 34
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900029 1000000 900029" "tests\35" 35
call scripts\gen-input-via-stdout.bat "files\generator_DY.exe 900030 1000000 900030" "tests\36" 36
call scripts\gen-answer.bat tests\01 tests\01.a "tests" "1"
call scripts\gen-answer.bat tests\02 tests\02.a "tests" "1"
call scripts\gen-answer.bat tests\03 tests\03.a "tests" "1"
call scripts\gen-answer.bat tests\04 tests\04.a "tests" "1"
call scripts\gen-answer.bat tests\05 tests\05.a "tests" "1"
call scripts\gen-answer.bat tests\06 tests\06.a "tests" "1"
call scripts\gen-answer.bat tests\07 tests\07.a "tests" "1"
call scripts\gen-answer.bat tests\08 tests\08.a "tests" "1"
call scripts\gen-answer.bat tests\09 tests\09.a "tests" "1"
call scripts\gen-answer.bat tests\10 tests\10.a "tests" "1"
call scripts\gen-answer.bat tests\11 tests\11.a "tests" "1"
call scripts\gen-answer.bat tests\12 tests\12.a "tests" "1"
call scripts\gen-answer.bat tests\13 tests\13.a "tests" "1"
call scripts\gen-answer.bat tests\14 tests\14.a "tests" "1"
call scripts\gen-answer.bat tests\15 tests\15.a "tests" "1"
call scripts\gen-answer.bat tests\16 tests\16.a "tests" "2"
call scripts\gen-answer.bat tests\17 tests\17.a "tests" "2"
call scripts\gen-answer.bat tests\18 tests\18.a "tests" "2"
call scripts\gen-answer.bat tests\19 tests\19.a "tests" "2"
call scripts\gen-answer.bat tests\20 tests\20.a "tests" "2"
call scripts\gen-answer.bat tests\21 tests\21.a "tests" "2"
call scripts\gen-answer.bat tests\22 tests\22.a "tests" "2"
call scripts\gen-answer.bat tests\23 tests\23.a "tests" "2"
call scripts\gen-answer.bat tests\24 tests\24.a "tests" "2"
call scripts\gen-answer.bat tests\25 tests\25.a "tests" "2"
call scripts\gen-answer.bat tests\26 tests\26.a "tests" "2"
call scripts\gen-answer.bat tests\27 tests\27.a "tests" "3"
call scripts\gen-answer.bat tests\28 tests\28.a "tests" "3"
call scripts\gen-answer.bat tests\29 tests\29.a "tests" "3"
call scripts\gen-answer.bat tests\30 tests\30.a "tests" "3"
call scripts\gen-answer.bat tests\31 tests\31.a "tests" "3"
call scripts\gen-answer.bat tests\32 tests\32.a "tests" "3"
call scripts\gen-answer.bat tests\33 tests\33.a "tests" "3"
call scripts\gen-answer.bat tests\34 tests\34.a "tests" "3"
call scripts\gen-answer.bat tests\35 tests\35.a "tests" "3"
call scripts\gen-answer.bat tests\36 tests\36.a "tests" "3"
call scripts\gen-answer.bat tests\37 tests\37.a "tests" "3"
call scripts\gen-answer.bat tests\38 tests\38.a "tests" "3"

