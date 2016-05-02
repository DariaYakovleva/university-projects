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
list *head, *tail;

void newqueue(int a)
{
    tail = new list;
    head = new list;
    list *v = new list;
    v->x = a;
    v->to = NULL;
    head = v;
    tail = v;
}

void pushst(int a)
{
    list *v = new list;
    v->to = NULL;
    v->x = a;
    tail->to=v;
    tail=v;
}

int popst()
{
    int t = head->x;
    head = head->to;
    return t;
}

int main()
{
    freopen("queue2.in", "r", stdin);
    freopen("queue2.out", "w", stdout);
    cin >> n;
    
    for (int i = 0; i < n; i++)
    {
        char c;
        int a;
        cin >> c;
        if (c == '+')
        {
            cin >> a;
            if (head == NULL)
                newqueue(a);
            else
                pushst(a);
        }
        else
        {
            cout << popst() << endl;
        }
    }
    return 0;
}