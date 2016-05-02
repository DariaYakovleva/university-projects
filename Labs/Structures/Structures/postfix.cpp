#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>
#define ll long long

using namespace std;

struct list
{
    ll x;
    list *to;
};

list *stack;

void pushst(ll a)
{
    list *v = new list;
    v->to = stack;
    v->x = a;
    stack = v;
}

int popst()
{
    ll t = stack->x;
    stack = stack->to;
    return t;
}

int main()
{
    freopen("postfix.in", "r", stdin);
    freopen("postfix.out", "w", stdout);
    stack = new list;
    stack->x = 0;
    stack->to = NULL; 
    string s;
    getline(cin, s);
    int i = 0;
    while (i < s.size())
    {
        if (s[i] != ' ')
        if (s[i] == '+')
        {
            pushst(popst() + popst());
        }
        else
        if ((s[i] == '-') && ((i + 1 == s.size()) || (s[i + 1] == ' ')))
        {
            ll t = popst();
            pushst(popst() - t);
        }
        else
        if (s[i] == '*')
        {
            pushst(popst() * popst());
        }
        else 
        {
            if (s[i] == '-')
            {
                pushst(-(s[i + 1] - '0'));
                i++;
            }
            else
                pushst(s[i] - '0');
        }
        i++;
    }
    cout << popst() << endl;
    
    return 0;
}