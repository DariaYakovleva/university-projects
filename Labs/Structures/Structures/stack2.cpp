#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

struct list
{
    int x;
    list *to;
};

int n;
list *head;

void pushst(int a)
{
    list *v = new list;
    v->to = head;
    v->x = a;
    head = v;
}

int popst()
{
    int t = head->x;
    head = head->to;
    return t;
}

int main()
{
    freopen("stack2.in", "r", stdin);
    freopen("stack2.out", "w", stdout);
    cin >> n;
    head = new list;
    head->to = NULL; 
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