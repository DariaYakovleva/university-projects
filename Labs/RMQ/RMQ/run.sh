
#! /bin/sh
echo "Hello world!"
c++ rmq2.cpp -O2 -o rmq2
c++ rmq2_test.cpp -O2 -o rmq2_test
c++ rmq2_right.cpp -O2 -o rmq2_right

for ((i = 0; i < 10000; i++))
do
./rmq2_test
./rmq2_right
./rmq2
if diff rmq2.out rmq2_right.out
then
    echo "OK $i"
else
    echo "FAILD $i"
    exit 0
fi
done
exit 0