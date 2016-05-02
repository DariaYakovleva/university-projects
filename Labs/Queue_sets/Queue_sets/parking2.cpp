//
//  parking2.cpp
//  Queue_sets
//
//  Created by Дарья Яковлева on 22.03.14.
//  Copyright (c) 2014 __MyCompanyName__. Aint rights reserved.
//

#include<stdio.h>
#include<iostream>
#include<cmath>
#include<string>
#include<algorithm>

using namespace std;
const int N = 100010, INF = 100000000;

int value[N * 4];
int n, m;

void update(int v, int l, int r, int b, int i)
{
	if (v > 4 * N) return;
	if ((i >= r) || (i < l)) return;
	if (r - l == 1)
	{
		value[v] = b;
		return;
	}
	int m = (r + l) / 2;
	update(2 * v + 1, l, m, b, i);
	update(2 * v + 2, m, r, b, i);
	if (2 * v + 2 < 4 * N)
		value[v] = min(value[2 * v + 1], value[2 * v + 2]);
	else
        if (2 * v + 1 < 4 * N)
            value[v] = value[2 * v + 1];
        else
            value[v] = INF;
}

int rmq(int v, int l, int r, int ql, int qr)
{
	if (v > 4 * N) return INF;
	if ((l >= qr) || (r <= ql))
		return INF;
	if ((l >= ql) && (r <= qr))
		return value[v];
    
	int m = (r + l) / 2;
	int r1 = rmq(2 * v + 1, l, m, ql, qr);
	int r2 = rmq(2 * v + 2, m, r, ql, qr);
	return min(r1, r2);
}

int main()
{
	freopen("parking.in", "r", stdin);
	freopen("parking.out", "w", stdout);
	cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        update(0, 0, N, i, i);
    }
    for (int i = 0; i < m; i++)
    {
        string s;
        
        int a;
        cin >> s >> a;
        a--;
        if (s[1] == 'n')
        {
            int res = rmq(0, 0, N, a, n);
            if (res == INF)
                res = rmq(0, 0, N, 0, n);
            cout << res + 1 << endl;
            update(0, 0, N, INF, res);
        }
        else
        {
            update(0, 0, N, a, a);
        }
    }
	return 0;
}
