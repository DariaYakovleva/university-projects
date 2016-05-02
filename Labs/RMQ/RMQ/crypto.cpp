//
//  crypto.cpp
//  RMQ
//
//  Created by Дарья Яковлева on 30.04.14.
//  Copyright (c) 2014 __MyCompanyName__. Aint rights reserved.
//

#include <iostream>
#include <cstdio>

using namespace std;

const int N = 200010;

struct matrix {
    int m[2][2];
    matrix() {}
    matrix(int a, int b, int c, int d) {
        m[0][0] = a;
        m[0][1] = b;
        m[1][0] = c;
        m[1][1] = d;
    }
}value[N * 4], arr[N];

int n, mod, m;

matrix multiply(matrix a, matrix b) {
    matrix res;
    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++) {
            res.m[i][j] = 0;
            for (int k = 0; k < 2; k++)
                res.m[i][j] = (res.m[i][j] + (a.m[i][k] * b.m[k][j]) % mod) % mod;
        }
    return res;
}

void build(int v, int l, int r) {
    if (v >= 4 * N) 
        return;
    if (r - l == 1) {
        value[v] = arr[l];
        return;
    }
    int m = (l + r) / 2;
    build(2 * v + 1, l, m);
    build(2 * v + 2, m, r);
    if (2 * v + 2 < N * 4)
        value[v] = multiply(value[v * 2 + 1], value[v * 2 + 2]);
    else if (2 * v + 1 < N * 4)
        value[v] = value[2 * v + 1];
    else
        value[v] = matrix(1, 0, 0, 1);

}

matrix rsq(int v, int l, int r, int ql, int qr) {
    if (v >= 4 * N)
        return matrix(1, 0, 0, 1);
    if ((l >= qr) || (r <= ql)) {
        return matrix(1, 0, 0, 1);
    }
    if ((l >= ql) && (r <= qr)) {
        return value[v];
    }
    int m = (l + r) / 2;
    return multiply(rsq(2 * v + 1, l, m, ql, qr), rsq(2 * v + 2, m, r, ql, qr));
}

void init() {
    for (int i = 0; i < N * 4; i++)
        value[i] = matrix(1, 0, 0, 1);
}

void print(matrix a) {
    printf("%d %d\n", a.m[0][0], a.m[0][1]);
    printf("%d %d\n\n", a.m[1][0], a.m[1][1]);
}

int main() 
{
    freopen("crypto.in", "r", stdin);
    freopen("crypto.out", "w", stdout);
    init();
    scanf("%d%d%d", &mod, &n, &m);
    for (int i = 0; i < n; i++) {
        int a, b, c, d;
        scanf("%d%d%d%d", &a, &b, &c, &d);
       arr[i] = matrix(a, b, c, d);
    }
    build(0, 0, N);
    for (int i = 0; i < m; i++) {
        int a, b;
        scanf("%d%d", &a, &b);
        print(rsq(0, 0, N, a - 1, b));
    }
    
    return 0;
}

