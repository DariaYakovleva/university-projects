import random
from random import randint
import math
random.seed("spb-municipal-2015")

class TestGen:
    def __init__(self):
        self.test_number = 0
        self.desc_file = open("../tests.desc", "w")

    def generate_samples(self):             
        self.print_test(["soon", "English", "this"], "preliminary")

                                       
    def print_test(self, a, dirname="tests"):
#        subtask = None
        self.test_number += 1
        test_name = "../" + dirname + "/{0:0=2d}".format(self.test_number)
        test_file = open(test_name, "w")
        print(len(a), file=test_file)
        for x in a:
            print(x, file = test_file)
        test_file.close()
        print(str(self.test_number) + " generated")

    def rnd1(self, canS, canS1, canSH, canO, canO1, canE, canE1, canBig1):
        good = False
        while not good:
            L = randint(1, 30)
            res = ""
            for i in range(L):
                good = False
                while not good:
                    good = True
                    c = chr(ord('a') + randint(0, 25))
                    if c == 's' and not canS:
                        good = False
                    if c == 's' and i == 0 and not canS1:
                        good = False
                    if c == 'h' and i > 0 and res[-1].lower() == 's' and not canSH:
                        good = False
                    if c == 'e' and not canE:
                        good = False
                    if c == 'e' and i == 0 and not canE1:
                        good = False
                    if c == 'o' and i >= canO and res[len(res) - canO:] == 'o' * canO:
                        good = False
                    if c == 'o' and i == 0 and not canO1:
                        good = False
                if i == 0 and canBig1:
                    if randint(1, 2) == 1:
                        c = c.upper()
                res += c
                if canO > 0 and res.find("o" * canO) == -1 and randint(1, 5) == 1:
                    res += "o" * canO
            res = res[:L]
            if canS and res.find("s") == -1 and randint(1, 50) != 1:
                good = False
            if canE and res.find("e") == -1 and randint(1, 50) != 1:
                good = False
            if canS1 and res[0] != 's' and randint(1, 50) != 1:
                good = False
            if canE1 and res[0] != 'e' and randint(1, 50) != 1:
                good = False
            if canSH and res.find("sh") == -1 and randint(1, 50) != 1:
                good = False
            if canO > 0 and res.find("o" * min(canO, 5)) == -1 and randint(1, 5000) != 1:
                good = False
        return res
                

    def rnd2(self, canS, canS1, canSH, canO, canO1, canE, canE1, canBig1):
        good = False
        while not good:
            L = randint(1, 30)
            res = ""
            for i in range(L):
                good = False
                while not good:
                    good = True
                    c = chr(ord('a') + randint(0, 25))
                    if c == 's' and not canS:
                        good = False
                    if c == 's' and i == 0 and not canS1:
                        good = False
                    if c == 'h' and i > 0 and res[-1].lower() == 's' and not canSH:
                        good = False
                    if c == 'e' and not canE:
                        good = False
                    if c == 'e' and i == 0 and not canE1:
                        good = False
                    if c == 'o' and i >= canO and res[len(res) - canO:] == 'o' * canO:
                        good = False
                    if c == 'o' and i == 0 and not canO1:
                        good = False
                if i == 0 and canBig1:
                    if randint(1, 2) == 1:
                        c = c.upper()
                res += c
                if canO > 0 and res.find("o" * canO) == -1 and randint(1, 5) == 1:
                    res += "o" * canO
            res = res[:L]
            badCases = 0
            if canS and res.find("s") == -1 and randint(1, 50) != 1:
                badCases += 1
            if canE and res.find("e") == -1 and randint(1, 50) != 1:
                badCases += 1
            if canS1 and res[0] != 's' and randint(1, 50) != 1:
                badCases += 1
            if canE1 and res[0] != 'e' and randint(1, 50) != 1:
                badCases += 1
            if canSH and res.find("sh") == -1 and randint(1, 50) != 1:
                badCases += 1
            if canO > 0 and res.find("o" * min(canO, 5)) == -1 and randint(1, 5000) != 1:
                badCases += 1
            good = badCases < 4
        return res
                

    def rnds(self, n, canS, canS1, canSH, canO, canO1, canE, canE1, canBig1):
        res = []
        for i in range(n):
            res.append(self.rnd1(canS, canS1, canSH, canO, canO1, canE, canE1, canBig1))
        return res

    def rnds2(self, n, canS, canS1, canSH, canO, canO1, canE, canE1, canBig1):
        res = []
        for i in range(n):
            res.append(self.rnd2(canS, canS1, canSH, canO, canO1, canE, canE1, canBig1))
        return res

    def generate_all_tests(self):
        self.generate_samples() 

        self.test_number = 0
        
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = True, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = True, canS1 = True, canSH = False, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = True, canS1 = False, canSH = True, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = True, canS1 = True, canSH = True, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = True, canS1 = True, canSH = True, canO = 0, canO1 = False, canE = False, canE1 = False, canBig1 = True))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 1, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 2, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 3, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 4, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 5, canO1 = False, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 1, canO1 = True, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 2, canO1 = True, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 5, canO1 = True, canE = False, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = True, canE1 = False, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = True, canE1 = True, canBig1 = False))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = True, canE1 = False, canBig1 = True))
        self.print_test(self.rnds(100, canS = False, canS1 = False, canSH = False, canO = 0, canO1 = False, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 1, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 2, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 3, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 3, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 4, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 5, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        self.print_test(self.rnds2(100, canS = True, canS1 = True, canSH = True, canO = 6, canO1 = True, canE = True, canE1 = True, canBig1 = True))
        

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