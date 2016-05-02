//
//  main.cpp
//  Sort_and_BS
//
//  Created by Дарья Яковлева on 23.05.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>


using namespace std;

const int N = 100010;
int n, m;
int arr[N];


int lbs(int x) {
    int l = -1, r = n;
    while (r > l + 1) {
        int m = (l + r) / 2;
        if (x > arr[m]) {
            l = m;
        } else {
            r = m;
        }
    }
    if ((r < n) && (arr[r] == x)) return r;
    return -2;
}

int rbs(int x) {
    int l = -1, r = n;
    while (r > l + 1) {
        int m = (l + r) / 2;
        if (x >= arr[m]) {
            l = m;
        } else {
            r = m;
        }
    }
    if ((l > -1) && (arr[l] == x)) return l;
    return -2;
}

int main() {
    freopen("binsearch.in", "r", stdin);
    freopen("binsearch.out", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    cin >> m;
    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;
        cout << lbs(x) + 1 << " " << rbs(x) + 1 << endl;
    }
}

