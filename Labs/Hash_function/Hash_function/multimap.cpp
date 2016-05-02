//
//  multimap.cpp
//  Hash_function
//
//  Created by Дарья Яковлева on 21.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <string>
#include <map>
#include <algorithm>

using namespace std;

multimap<string, string> mmap;

int main() {
    freopen("multimap.in", "r", stdin);
    freopen("multimap.out", "w", stdout);
    string s;
    
    while (cin >> s) {
        string a;
        cin >> a;
        if (s[0] == 'p') {
            string b;
            cin >> b;
            mmap.insert(a, b);
        }
        if (s[0] == 'd') {
            if (s.size() == 9) {
                string b;
                cin >> b;
              //  mmap.erase(mmap.find(make_pair(a, b)));
            } else {
                mmap.erase(a);
            }
            
        }
        if (s[0] == 'g') {
            
        }
    }
}