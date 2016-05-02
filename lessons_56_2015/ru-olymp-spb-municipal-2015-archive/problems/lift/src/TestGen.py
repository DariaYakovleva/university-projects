import random
from random import randint
import math
random.seed("spb-municipal-2015")

class TestGen:
    def __init__(self):
        self.test_number = 0
        self.desc_file = open("../tests.desc", "w")

    def generate_samples(self):             
        self.print_test(15, 8, 5, 3, "preliminary")

                                       
    def print_test(self, n, k, a, b, dirname="tests"):
#        subtask = None            
        self.test_number += 1
        test_name = "../" + dirname + "/{0:0=2d}".format(self.test_number)
        test_file = open(test_name, "w")
        print(n, k, a, b, file=test_file)
        test_file.close()

    def generate_all_tests(self):
        self.generate_samples() 

        self.test_number = 0

        limit = [10, 10, 100, 1000]
        for i, n in enumerate([2, 10, 37, 100]):
            self.print_test(n, 1, randint(1, limit[i]), randint(1, limit[i]))
        for n in [2, 10, 37, 100]:
            self.print_test(n, n, randint(1, limit[i]), randint(1, limit[i]))
        for n in [2, 10, 37, 100]:
            self.print_test(n, randint(1, 100), randint(1, 1000), randint(1, 1000))
        self.print_test(100, 1, 1000, 1000)
        self.print_test(100, 100, 1000, 1000)
        for i in range(6):
            self.print_test(randint(2, 100), randint(1, 100), randint(1, 1000), randint(1, 1000))
        

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
writer = TestGen()
writer.generate_all_tests()
finish = time.time()
print('All tests were generated. Elapsed time: ', finish - start)