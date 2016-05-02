
#! /bin/sh
echo "Hello world!"
java Test
java Right_solution
./main
for ((i = 0; i < 1; i++))
do
if diff set.out set_right.out
then
    echo "OK $i"
else
    echo "FAILD $i"
    exit 0
fi
done
exit 0
