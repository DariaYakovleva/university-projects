#!/bin/bash -e

for ((j = 0; j < 1000; j++))
do
for i in test1.txt
do
    python main.py
    if test -f "$i" 
    then
        if ./mul < "$i" | cmp -s - $(basename $i .txt).gold
        then
            echo "$i: OK $j" 
        else
            echo "$i: FAIL $j"
        fi
    fi
done
done
