import random
from random import randint
import math
random.seed("spb-municipal-2015")

test_number = 0

def generate_samples():             
    print_test(3, 4, [1, 1, 2, 1], "preliminary")

def print_test(n, k, a, dirname="tests"):
    global test_number
    test_number += 1
    print(test_number)
    test_name = "../" + dirname + "/{0:0=2d}".format(test_number)
    test_file = open(test_name, "w")
    print(n, k, file=test_file)
    print(" ".join(map(str, a)), file=test_file)
    test_file.close()

def print_random(n, k):
    a = []
    x = [0, 0]
    for i in range(k):
        if x[0] == k - n and max(x) > k - n:
            x[1] += 1
            a.append(1)
        elif x[1] == k - n and max(x) > k - n:
            x[0] += 1
            a.append(0)
        elif x[0] == n - 1 and i < k - 1:
            x[1] += 1
            a.append(1)
        elif x[1] == n - 1 and i < k - 1:
            x[0] += 1
            a.append(0)
        else:
            r = randint(0, 1)
            x[r] += 1
            a.append(r)
    a = list(map(lambda x: x + 1, a))
    print_test(n, k, a)


def generate_all_tests():
    global test_number
    generate_samples() 

    test_number = 0

    print_test(1, 1, [1])
    print_test(1, 1, [2])
    print_test(2, 2, [1, 1])
    print_test(2, 2, [2, 2])
    print_test(2, 3, [1, 2, 1])

    print_test(3, 3, [2, 2, 2])
    print_test(3, 5, [1, 1, 2, 2, 2])
    print_test(3, 5, [1, 2, 1, 2, 1])
    print_random(4, 6)
    print_random(4, 4)

    print_random(6, 10)
    print_random(10, 13)
    print_random(20, 20)
    print_random(20, 39)
    print_random(20, 28)

    print_random(40, 40)
    print_random(40, 45)
    print_random(40, 55)
    print_random(40, 67)
    print_random(40, 79)

    print_random(100, 100)
    print_random(100, 105)
    print_random(100, 115)
    print_random(100, 137)
    print_random(100, 199)

import os, shutil
if os.path.exists('../tests'):
    shutil.rmtree('../tests', ignore_errors=False, onerror=None)
if os.path.exists('../preliminary'):
    shutil.rmtree('../preliminary', ignore_errors=False, onerror=None)
try:
    os.makedirs('../tests')
    os.makedirs('../preliminary')

except OSError:
    pass

import time
start = time.time()
generate_all_tests()
finish = time.time()
print('All tests were generated. Elapsed time: ', finish - start)