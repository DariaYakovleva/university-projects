import random
from random import randint
import math
random.seed("spb-municipal-2015")

test_number = 0

def generate_samples():             
    print_test(6, 18, [5, 3, 10, 2, 4, 9], "preliminary")

def solve(n, x, b):
    a = b[:]
    a.sort()
    R = n - 1
    best = 0
    for L in range(n):
        while R > 0 and a[L] + a[R] > x:
            R -= 1
        if L != R and a[L] + a[R] <= x:
            best = max(a[L] + a[R], best)
    return best

                                   
def random_test(n, mx, limit):             
    a = [0] * n
    best = 0
    mx3 = mx // 3
    while True:
        for i in range(n):
            if randint(1, 2) == 1:
                a[i] = randint(1, mx3 - limit)
            elif randint(1, 1000) != 1:
                a[i] = randint(2 * mx3 + limit, mx)
            else:
                a[i] = randint(1, mx)
        x = 2 * mx3 + randint(0, limit // 2)
        if x > 10**9:
            x = 10**9
        v = solve(n, x, a)
        if v <= x - limit:
            break
        if x - v > best:    
            best = x - v
            print("-->" + str(best))
    print_test(n, x, a)

def random_test2(n, mx, limit):             
    a = [0] * n
    best = 0
    mx3 = mx // 3
    while True:
        for i in range(n):
            if randint(1, 2) == 1:
                a[i] = randint(1, mx3 - limit)
            else:
                a[i] = randint(2 * mx3 + limit, mx)
        x = 2 * mx3 - randint(0, limit // 2)
        if x > 10**9:
            x = 10**9
        v = solve(n, x, a)
        if v <= x - limit:
            break
        if x - v > best:    
            best = x - v
            print("-->" + str(best))
    print_test(n, x, a)

                                   
def print_test(n, x, a, dirname="tests"):
    global test_number
    test_number += 1
    print(test_number)
    test_name = "../" + dirname + "/{0:0=2d}".format(test_number)
    test_file = open(test_name, "w")
    print(n, x, file=test_file)
    print(" ".join(map(str, a)), file=test_file)
    test_file.close()

def generate_all_tests():
    global test_number
    generate_samples() 

    test_number = 0

    print_test(2, 2, [1, 1])
    print_test(3, 10**9, [5*10**8-1,5*10**8-0,5*10**8+1])
    random_test(10, 1000, 9)
    random_test(100, 1000, 9)
    random_test(1000, 10**8, 100)
    random_test(10000, 10**9, 100)
    random_test(25000, 10**9, 100)
    random_test(50000, 10**9, 100)
    random_test2(100000, 10**5, 100)
    random_test2(100000, 5*10**8, 100)


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