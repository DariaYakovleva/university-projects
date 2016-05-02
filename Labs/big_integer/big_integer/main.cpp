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
#include "big_integer.cpp"


using namespace std;


int main() {
    
    //?5 6 сложение
    big_integer a;
    big_integer b;
    string s1, s2;
    cin >> s1 >> s2;
    a = big_integer(s1);
    b = big_integer(s2);
    big_integer c2 = a;
    big_integer c3 = a;
    big_integer c4 = a;
    cout << "a = " << a << endl;
    cout << "b = " << b << endl;
    cout << "a + b = " << a + b << endl;
    cout << "a - b = " << a - b << endl;
    cout << "a * b = " << a * b << endl;
    cout << "a / b = " << a / b << endl;
    cout << "a % b = " << a % b << endl;
    c2 |= b;
    c3 &= b;
    c4 ^= b;
    cout << "a | b = " << c2 << endl;
    cout << "a & b = " << c3 << endl;
    cout << "a ^ b = " << c4 << endl;
    return 0;
}