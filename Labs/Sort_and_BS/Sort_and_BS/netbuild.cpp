//
//  main.cpp
//  Sort_and_BS
//
//  Created by Дарья Яковлева on 23.05.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <ctime>
#include <string>
#include <vector>

using namespace std;

const int N = 200;
int n, m = 0, k = 10;
vector< pair<int, int> > g[N];

void mpair(int level) {
    for (int i = 0; i < n; i += 2)
     if (i + 1 <= n && i + 2 <= n) {
        g[level].push_back(make_pair(i + 1, i + 2));
        m++;
    }
}
void diminish(int l, int r, int level) {
    for (int i = 0; i < (r - l + 1) / 2; i++)
     if (l + i <= n && r - i <= n) {
        g[level].push_back(make_pair(l + i, r - i));
        m++;
    }
}

void hm(int l, int cnt, int level) {
    for (int i = 0; i < cnt; i++) 
    if (l + i <= n && l + i + cnt <= n) {
        g[level].push_back(make_pair(l + i, l + i + cnt));
        m++;
    }
}

void bitonic() {
    mpair(0);
    diminish(1, 4, 1);
    diminish(5, 8, 1);
    diminish(9, 12, 1);
    diminish(13, 16, 1);
    mpair(2);
    diminish(1, 8, 3);
    diminish(9, 16, 3);
    hm(1, 2, 4);
    hm(5, 2, 4);
    hm(9, 2, 4);
    hm(13, 2, 4);
    mpair(5);
    diminish(1, 16, 6);
    hm(1, 4, 7);
    hm(9, 4, 7);
    hm(1, 2, 8);
    hm(5, 2, 8);
    hm(9, 2, 8);
    hm(13, 2, 8);
    mpair(9);
}

int main() {
    freopen("netbuild.in", "r", stdin);
    freopen("netbuild.out", "w", stdout);
    cin >> n;
    bitonic();
    cout << n << " " << m << " " << k << endl;
    for (int i = 0; i < k; i++) {
        cout << g[i].size() << " ";
        for (int j = 0; j < g[i].size(); j++) {
            cout << g[i][j].first << " " << g[i][j].second << " ";
        }
        cout << endl;
    }
    
}

