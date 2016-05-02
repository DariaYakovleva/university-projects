
//
//  main.cpp
//
//  Created by Дарья Яковлева on 08.03.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

struct list
{
    char x;
    list *to;
};

int n;
list *stack;
string s = "";

void pushst(char a)
{
    list *v = new list;
    v->to = stack;
    v->x = a;
    stack = v;
}

int popst()
{
    int t = stack->x;
    stack = stack->to;
    return t;
}

bool myread()
{
    s = "";
    char c;
    while (scanf("%c", &c) != EOF)
    {
        if (c == '\n')
            return 1;
        s += c;
    }
    return 0;
    
}


int main()
{
    freopen("brackets.in", "r", stdin);
    freopen("brackets.out", "w", stdout);
    while (myread())
    {
        bool right = 1;
        stack = new list;
        stack->to = NULL;
        stack->x = '0';
        for (int i = 0; i < s.size(); i++)
        {
            if ((s[i] == '(') || (s[i] == '['))
            {
                pushst(s[i]);
            }
            if (s[i] == ')')
            {
                if (stack->x != '(')
                {
                    right = 0;
                    break;
                }
                else
                    popst();
                
            }
            if (s[i] == ']')
            {
                if (stack->x != '[')
                {
                    right = 0;
                    break;
                }
                else
                    popst();
            }
        }
        if (stack->x != '0')
            right = 0;
        if (right)
            cout << "YES" << endl;
        else
            cout << "NO" << endl;
    }
    return 0;
}