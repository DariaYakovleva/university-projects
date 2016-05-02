//
//  main.cpp
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

int heap[N];
int n;


bool ok() 
{
    for (int i = 0; i < n; i++)
    {
        if ((2 * i + 1 < n) && (heap[i] > heap[2 * i + 1]))
        {
            return false;
        }
        if ((2 * i + 2 < n) && (heap[i] > heap[2 * i + 2]))
        {
            return false;
        }
    }
    return true;
}

int main()
{
    freopen("isheap.in", "r", stdin);
    freopen("isheap.out", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> heap[i];
    if (ok())
        cout << "YES" << endl;
    else
        cout << "NO" << endl;
    return 0;
}           