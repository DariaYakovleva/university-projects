//
//  dsu.cpp
//  Queue_sets
//
//  Created by Дарья Яковлева on 21.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 100010, INF = 1000000000;

struct vertex
{
    int mini, maxi, size, p;
    vertex(){}
    vertex(int a, int b, int c, int d)
    {
        p = a;
        size = b;
        mini = c;
        maxi = d;
    }
}dsu[N];

int n;

int pred(int x)
{
    if (dsu[x].p == x)
        return x;
    return dsu[x].p = pred(dsu[x].p);
}

void myunion(int a, int b)
{
    int left = pred(a), right = pred(b);
    if (left == right)
        return;
    
    if (dsu[left].size > dsu[right].size)
    {
        dsu[left] = vertex(left, dsu[right].size + dsu[left].size, min(dsu[right].mini, 
                                                       dsu[left].mini), max(dsu[right].maxi, dsu[left].maxi));
        dsu[right].p = left;
       /*
        dsu[right].size += dsu[left].size;
        dsu[right].mini = min(dsu[right].mini, dsu[left].mini);
        dsu[right].maxi = min(dsu[right].maxi, dsu[left].maxi);
       */
    }
    else
    {
        dsu[right] = vertex(right, dsu[left].size + dsu[right].size, min(dsu[right].mini, 
                                                       dsu[left].mini), max(dsu[right].maxi, dsu[left].maxi));
        dsu[left].p = right;
    }
}

int get(int a)
{
    int res = pred(a);
    return res;
    
}


void init()
{
    for (int i = 0; i < n; i++)
    {
        dsu[i].p = dsu[i].mini = dsu[i].maxi = i;
        dsu[i].size = 1;
    }
}

int main()
{
    freopen("dsu.in", "r", stdin);
    freopen("dsu.out", "w", stdout);
    cin >> n;
    init();
    string s;
    while (cin >> s)
    {
        int a;
        cin >> a;
        a--;
        if (s[0] == 'g')
        {
            int res = get(a);
            cout << dsu[res].mini + 1 << " " << dsu[res].maxi + 1 << " " << dsu[res].size << endl;
        }
        else
        {
            int b;
            cin >> b;
            b--;
            myunion(a, b);
        }
    }
 
    return 0;
}           