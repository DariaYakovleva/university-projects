#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 1000010;
int *stack1 = new int[1];
int head = 0, tail = 0, n, s = 1;

void bigger()
{
    int *a = new int[2 * s];
    for (int i = 0; i < s; i++)
        a[i] = stack1[i];
    stack1 = a;
    s = 2 * s;
}

void less1()
{
    int *a = new int[s / 2];
    for (int i = 0; i < s / 2; i++)
        a[i] = stack1[i];
    stack1 = a;
    s = s / 2;
}
void pushst(int x)
{
    if (tail + 1 == s)
        bigger();
    stack1[tail] = x;
    tail++;
}

int popst()
{
    tail--;
    int t = stack1[tail];
    if (tail + 1 <= s / 4)
        less1();
    return t;
}

int main()
{
    freopen("stack1.in", "r", stdin);
    freopen("stack1.out", "w", stdout);
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        char c;
        int a;
        cin >> c;
        //cin >> c;
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