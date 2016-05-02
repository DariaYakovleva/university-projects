import random
from random import randint
import math
random.seed("spb-municipal-2015")

test_number = 0

def generate_samples():             
    print_test(6, 2, 2, 4, [1, 2, 3, 4, 6, 13], "preliminary")

def print_test(n, k, a, b, x, dirname="tests"):
    global test_number
    test_number += 1
    print(test_number)
    test_name = "../" + dirname + "/{0:0=2d}".format(test_number)
    test_file = open(test_name, "w")
    print(n, k, a, b, file=test_file)
    print(" ".join(map(str, x)), file=test_file)
    test_file.close()

def generate_random(n, k, maxx):
    x = []
    for i in range(n):
        x.append(randint(1, maxx - n + 1))
    x.sort()
    for i in range(n):
        x[i] += i
    while True:
        a = randint(1, n)
        b = randint(1, n)
        if a * k <= n <= b * k:
            break
    print_test(n, k, a, b, x)

def generate_random_tight(n, k, maxx):
    x = []
    for i in range(n):
        x.append(randint(1, maxx - n + 1))
    x.sort()
    for i in range(n):
        x[i] += i
    a = n // k
    b = a + 1
    if b > n:
        b = n
    print_test(n, k, a, b, x)

def generate_random_quite_tight(n, k, maxx):
    x = []
    for i in range(n):
        x.append(randint(1, maxx - n + 1))
    x.sort()
    for i in range(n):
        x[i] += i
    a = n // k - randint(0, n // k // 5)
    b = n // k + randint(1, 1 + n // k // 5)
    if a < 1:
        a = 1
    if b > n:
        b = n
    print_test(n, k, a, b, x)

def generate_skew(n, k, maxx):
    x = []
    for i in range(n):
        while True:
            y = randint(1, maxx - n + 1)
            if ((y // (maxx // 10)) % 2 == 1):
                break
        x.append(y)
    x.sort()
    for i in range(n):
        x[i] += i
    a = n // k - randint(0, n // k // 5)
    b = n //k  + randint(1, 1 + n // k // 5)
    if a < 1:
        a = 1
    if b > n:
        b = n
    print_test(n, k, a, b, x)

def generate_all_tests():
    global test_number
    generate_samples() 

    test_number = 0

    generate_skew(5, 2, 10**9)
    generate_random(5, 2, 10**9)

    generate_random(10, 2, 100)
    generate_random_tight(10, 3, 10**5)
    generate_random(10, 5, 10**9)
    generate_random_quite_tight(10, 9, 10**5)
    generate_random(10, 10, 10**9)

    generate_skew(20, 3, 10000)
    generate_skew(20, 2, 10**5)
    generate_skew(20, 4, 10**9)
    generate_skew(20, 9, 10**5)
    generate_skew(20, 17, 10**9)

    generate_random(100, 3, 10000)
    generate_random_tight(100, 7, 10**5)
    generate_random(100, 13, 10**9)
    generate_random_quite_tight(100, 19, 10**5)
    generate_random(100, 47, 10**9)

    generate_random(200, 3, 10000)
    generate_random_tight(200, 7, 10**5)
    generate_random(200, 13, 10**9)
    generate_random_quite_tight(200, 19, 10**5)
    generate_random(200, 47, 10**9)

    generate_skew(200, 3, 10000)
    generate_skew(200, 6, 10**5)
    generate_skew(200, 8, 10**9)
    generate_skew(200, 19, 10**9)
    generate_skew(200, 37, 10**9)

    generate_skew(200, 150, 10**9)
    generate_skew(200, 190, 10**9)
    generate_skew(200, 200, 10**9)
    generate_random_quite_tight(200, 150, 10**9)
    generate_random_quite_tight(200, 190, 10**9)
    generate_random_quite_tight(200, 200, 10**9)

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