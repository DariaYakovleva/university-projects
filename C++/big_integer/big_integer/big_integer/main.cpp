//
//  main.cpp
//  big_integer
//
//  Created by Дарья Яковлева on 02.05.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <string>
#include <cstdio>
#include "big_integer.h"
#include <fstream>

using namespace std;


int main() {
    ofstream file ("outpupt.out");
    big_integer a;
    big_integer b;
    string s1, s2;
    cin >> s1 >> s2;
    a = big_integer(s1);
    b = big_integer(s2);
    big_integer c2 = a;
    big_integer c3 = a;
    big_integer c4 = a;
    big_integer c5 = a;
    big_integer c6 = a;
    file << "a = " << a << endl;
    file << "b = " << b << endl;
    file << "a + a = " << a + a << endl;
    file << "a + b = " << a + b << endl;
    file << "a - b = " << a - b << endl;
    file << "a * b = " << a * b << endl;
    file << "a / b = " << a / b << endl;
    file << "a % b = " << a % b << endl;
    c2 |= b;
    c3 &= b;
    c4 ^= b;
    c5 >>= 3;
    c6 <<= 3;
    file << "a | b = " << c2 << endl;
    file << "a & b = " << c3 << endl;
    file << "a ^ b = " << c4 << endl;
    file << "a >> b = " << c5 << endl;
     file << "a << b = " << c6 << endl;
    myVector vec;
    myVector vec2;
    vec2.resize(10);
    file << vec.size() << endl;
    vec.push_back(10);
    file << vec[0] << endl;
    file << vec.size() << endl;
    vec.pop_back();
    file << vec.size() << endl;
    vec.push_back(23);
    vec2 = vec;
    file << "vec2 " << vec2.size() << endl;
    file << "vec2 " <<vec2[0] << endl;
    vec.resize(3);
    file << vec[0] << endl;
    vec[0] = 1;
    file << vec[0] << endl;
    vec.clear();
    file << vec.size() << endl;
    vec.push_back(100);
    file << vec[0] << endl;
    file << vec.size() << endl;
    vec.pop_back();
    file << vec.size() << endl;
    vec.push_back(23);
    file << vec[0] << endl;
    vec[0] = 1;
    file << vec[0] << endl;
    return 0;
}
