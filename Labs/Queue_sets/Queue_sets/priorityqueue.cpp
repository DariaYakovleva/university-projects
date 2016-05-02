//
//  priorityqueue.cpp
//  Queue_sets
//
//  Created by Дарья Яковлева on 22.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 1000010, INF = 1000000000;

struct vertex
{
    int v, op;
}queue[N];
int operation[N];

int n = 0, k = 0;

void sift_down(int pos)
{
    if ((2 * pos + 1 < n) && (queue[pos].v > queue[2 * pos + 1].v) && (2 * n + 2 >= n || queue[2 * pos + 1].v <= queue[2 * pos + 2].v))
    {
        swap(queue[pos], queue[2 * pos + 1]);
        operation[queue[pos].op] = pos;
        operation[queue[pos * 2 + 1].op] = pos * 2 + 1;
        sift_down(2 * pos + 1);
    }
    if ((2 * pos + 2 < n) && (queue[pos].v > queue[2 * pos + 2].v))
    {
        swap(queue[pos], queue[2 * pos + 2]);
        operation[queue[pos].op] = pos;
        operation[queue[pos * 2 + 2].op] = pos * 2 + 2;
        sift_down(2 * pos + 2);
    }
}

void sift_up(int pos)
{
    if (pos == 0)
        return;
    if (queue[(pos + 1) / 2 - 1].v > queue[pos].v)
    {
        swap(queue[(pos + 1) / 2 - 1], queue[pos]);
        operation[queue[(pos + 1) / 2 - 1].op] = (pos + 1) / 2 - 1;
        operation[queue[pos].op] = pos;
        sift_up((pos + 1) / 2 - 1);
    }
}

int extract_min() 
{
    int res = queue[0].v;
    operation[queue[0].op] = -1;
    queue[0] = queue[n - 1];
    operation[queue[0].op] = 0;
    n--;
    sift_down(0);
    return res;
}

void push(int a, int oper)
{
    queue[n].v = a;
    queue[n].op = oper;
    operation[oper] = n;
    n++;
    sift_up(n - 1);
}

void decrease_key(int pos, int a)
{
    if (pos == -1)
        return;
    queue[pos].v = a;
    sift_up(pos);
}



int main()
{
    freopen("priorityqueue.in", "r", stdin);
    freopen("priorityqueue.out", "w", stdout);
    string s;
    
    while (cin >> s)
    {
        int a, b;
        if (s[0] == 'p')
        {
            cin >> a;
            push(a, k);
        }
        else
            if (s[0] == 'e')
            {
                if (n == 0)
                    cout << "*" << endl;
                else
                    cout << extract_min() << endl;
                operation[k] = -1;
            }
        else
        {
            cin >> a >> b;
            a--;
            decrease_key(operation[a], b);
            operation[k] = -1;
        }
        k++;
    }
    
    return 0;
}           