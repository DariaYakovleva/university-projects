//
//  parking3.cpp
//  Queue_sets
//
//  Created by Дарья Яковлева on 26.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 300010, INF = 1000000000;

struct vertex
{
    int mini, maxi, size, p, color;
    vertex(){}
    vertex(int a, int b, int c, int d, int e)
    {
        p = a;
        size = b;
        mini = c;
        maxi = d;
        color = e;
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
                                                                       dsu[left].mini), max(dsu[right].maxi, dsu[left].maxi), 1);
        dsu[right].p = left;
    }
    else
    {
        dsu[right] = vertex(right, dsu[left].size + dsu[right].size, min(dsu[right].mini, 
                                                                         dsu[left].mini), max(dsu[right].maxi, dsu[left].maxi), 1);
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
        dsu[i].color = 0;
    }
}

int main()
{
    freopen("parking.in", "r", stdin);
    freopen("parking.out", "w", stdout);
    cin >> n;
    init();
    for (int i = 0; i < n; i++)
    {
        int a;
        cin >> a;
        a--;
        int p = pred(a), place;
        if (dsu[p].color == 0)
        {
            cout << a + 1 << " ";
            dsu[a].color = 1;
            place = a;
        }
        else
        {
            place = dsu[p].maxi + 1;
            if (place >= n)
            {
                int next = pred(0); 
                if (dsu[next].color == 0)
                    place = next;
                else
                    place = dsu[pred(0)].maxi + 1;
            }
            dsu[place].color = 1;
            cout << place + 1 << " ";
        }
        if (place > 0)
        {
            int left = pred(place - 1);
            if (dsu[left].color == 1)
                myunion(place, left);
        }
        if (place + 1 < n)
        {
            int right = pred(place + 1);
            if (dsu[right].color == 1)
                myunion(place, right);
        }
    }
    
    return 0;
}           