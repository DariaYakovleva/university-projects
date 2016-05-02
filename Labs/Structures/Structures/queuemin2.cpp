//
//  queuemin2.cpp
//  Structures
//
//  Created by Дарья Яковлева on 09.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>

#define ll long long

using namespace std;

const int N = 30000010;
int queue[N], size = 0;



void sift_up(int i)
{
    if (i == 0)
        return;
    if (queue[i / 2] > queue[i])
    {
        swap(queue[i / 2], queue[i]);
        sift_up(i /  2);
    }
    
}

void sift_down(int i)
{
    int left, right;
    bool l = 0, r = 0;
    if ((i * 2 + 1 < size) && (queue[i * 2 + 1] < queue[i]))
    {
        left = queue[i * 2 + 1];
    }
    else
        l = 1;
    if ((i * 2 + 2 < size) && (queue[i * 2 + 2] < queue[i]))
    {
        right = queue[i * 2 + 2];
    }
    else
        r = 1;
    if (l && r)
        return;
    if (l)
    {
        swap(queue[i * 2 + 2], queue[i]);
        sift_down(i * 2 + 2);
    }
    else
    if (r)
    {
        swap(queue[i * 2 + 1], queue[i]);
        sift_down(i * 2 + 1);
    }
    else
    {
        if (left < right)
        {
            swap(queue[i * 2 + 1], queue[i]);
            sift_down(i * 2 + 1);
        }
        else
        {
            swap(queue[i * 2 + 2], queue[i]);
            sift_down(i * 2 + 2);
        }
    }
    
}

void insert(int a)
{
    queue[size] = a;
    sift_up(size);
    size++;
    
}
int extract_min()
{
    int t = queue[0];
    queue[0] = queue[size - 1];
    size--;
    sift_down(0);
    return t;
}

int n, m, k;                          
ll a, b, c;
ll x1, x2;
ll res = 0;

int main()
{
    //freopen("queuemin2.in", "r", stdin);
   // freopen("queuemin2.out", "w", stdout);
    scanf("%d %d %d", &n, &m, &k);
    scanf("%ld %ld %ld", &a, &b, &c);
    for (int i = 0; i < k; i++)
    {
        ll x;
        scanf("%ld", &x);
        insert(x);
        if (i == k - 2)
            x2 = x;
        if (i == k - 1)
            x1 = x;
    }
    for (int i = k; i < n; i++)
    {
        if (i >= m)
            res += extract_min();
        ll nx = (a * x2 + b * x1 + c) & 0xFFFFFFFF;
        
        insert((int)nx);
        x2 = x1;
        x1 = nx;
    }
    res += extract_min();
    cout << res << endl;
    return 0;
}