
//
//  main.cpp
//
//  Created by Р вЂќР В°РЎР‚РЎРЉРЎРЏ Р Р‡Р С”Р С•Р Р†Р В»Р ВµР Р†Р В° on 08.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

int *queue = new int[1];
int head = 0, tail = 0, n, s = 1;

void bigger()
{
    int *a = new int[2 * s];
    for (int i = head; i < tail; i++)
        a[i - head] = queue[i];
    queue = a;
    s = 2 * s;
    tail -= head;
    head = 0;
}

void less1()
{
    int *a = new int[s / 2];
    for (int i = head; i < tail; i++)
        a[i - head] = queue[i];
    queue = a;
    s = s / 2;
    tail -= head;
    head = 0;
}
void pushst(int x)
{
    if (tail + 1 == s)
        bigger();
    queue[tail] = x;
    tail++;
}

int popst()
{
    int t = queue[head];
    head++;
    if (tail - head <= s / 4)
        less1();
    return t;
}

int main()
{
    freopen("queue1.in", "r", stdin);
    freopen("queue1.out", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        char c;
        int a;
        cin >> c;
        if (c == '+')
        {
            cin >> a;
            pushst(a);
        }
        else
        {
            cout << popst() << endl;
        }
    }
    return 0;
}           