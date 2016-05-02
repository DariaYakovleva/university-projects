//
//  main.cpp
//  RMQ
//
//  Created by Дарья Яковлева on 29.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#define ll long long

using namespace std;

const ll N = 500010;

ll n, arr[N * 4];

void update(ll v, ll l, ll r, ll i, ll b) {
    
    if (v >= 4 * N) 
        return;
    if ((i < l) || (i >= r))
        return;
    if (r - l == 1) {
        arr[v] = b;
        return;
    }
    ll m = (l + r) / 2;
    update(2 * v + 1, l, m, i, b);
    update(2 * v + 2, m, r, i, b);
    if (2 * v + 2 < N * 4)
        arr[v] = arr[v * 2 + 1] + arr[v * 2 + 2];
    else if (2 * v + 1 < N * 4)
        arr[v] = arr[2 * v + 1];
    else
        arr[v] = 0;
}


ll rsq(ll v, ll l, ll r, ll ql, ll qr) {
    if (v >= 4 * N)
        return 0;
    if ((l >= qr) || (r <= ql)) {
        return 0;
    }
    if ((l >= ql) && (r <= qr)) {
        return arr[v];
    }
    ll m = (l + r) / 2;
    return rsq(2 * v + 1, l, m, ql, qr) + rsq(2 * v + 2, m, r, ql, qr);
}

void init() {
    for (ll i = 0; i < N * 4; i++)
        arr[i] = 0;
}

int main() 
{
    freopen("rsq.in", "r", stdin);
    freopen("rsq.out", "w", stdout);
    init();
    cin >> n;
    for (ll i = 0; i < n; i++) {
        ll a;
        cin >> a;
        update(0, 0, N, i, a);
    }
    string s;
    while (cin >> s) {
        ll a, b;
        cin >> a >> b;
        if (s[1] == 'e') {
            update(0, 0, N, a - 1, b);
        } else {
            cout << rsq(0, 0, N, a - 1, b) << endl;
        }
            
    }
    
    return 0;
}

