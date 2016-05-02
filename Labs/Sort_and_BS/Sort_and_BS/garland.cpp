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

using namespace std;

const double EPS = 0.0000001;
int n;
double a;

double solve(double x) {
    double prev = a, cur = x;
    for (int i = 2; i < n; i++)
    {
        double next = 2 * cur - prev + 2;
        prev = cur;
        cur = next;
        if (cur < EPS) return -1;
    }
    return cur;
}

double bs() {
    double l = 0, r = a;
    while (r - l > EPS) {
        double m = (r + l) / 2;
        if (solve(m) == -1) {
            l = m;
        } else {
            r = m;
        }
    }
    if (solve(l) != -1) return solve(l);
    return solve(r);
}


int main() {
    freopen("garland.in", "r", stdin);
    freopen("garland.out", "w", stdout);
    cin >> n >> a;
    printf("%.3lf\n", bs());
    
}

