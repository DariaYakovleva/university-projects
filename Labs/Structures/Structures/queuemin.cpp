#include <iostream>
#include <cstdio>
#include <algorithm>
#include <cmath>

using namespace std;

#define ll long long

struct my
{
    int x, m;
    my(){}
    my(int a, int b)
    {
        x = a;
        m = b;
    }
};

const int N = 30000010;
int n, m, k, s1 = 0, s2 = 0, a, b, c, x1, x2, mini;
ll res = 0;
int stack1[N], stack2[N]; 

void insert(int x)
{
    if (s1 == 0)
    {
        stack1[s1] = x;
        mini = x;
    }
    else
    {
        stack1[s1] = x;
        mini = min(mini, x);
    }
	s1++;
}

void s1tos2()
{
    while (s1 > 0)
    {
        if (s2 == 0)
        {
            stack2[s2] = stack1[s1 - 1];
        }
        else
        {
            stack2[s2] = min(stack1[s1 - 1], stack2[s2 - 1]);
        }
        s1--;
        s2++;
    }
}

void deleted()
{
    if (s2 == 0)
    {
        s1tos2();
    }
    s2--;
    
}

int extract_min()
{
    if (s1 == 0)
        return stack2[s2 - 1];
    if (s2 == 0)
        return mini;
    return min(mini, stack2[s2 - 1]);
}


int main()
{
    freopen("queuemin2.in", "r", stdin);
    freopen("queuemin2.out", "w", stdout);
    scanf("%d %d %d", &n, &m, &k);
    scanf("%d %d %d", &a, &b, &c);
    for (int i = 0; i < k; i++)
    {
        int x;
        scanf("%d", &x);
        insert(x);
        if (i + 1 >= m)
        {
            res += extract_min();
            deleted();
        }
        if (i == k - 2)
            x2 = x;
        if (i == k - 1)
            x1 = x;
    }
    for (int i = k; i < n; i++)
    {
        
        int nx = (int)(a * x2 + b * x1 + c);
        insert(nx);
        x2 = x1;
        x1 = nx;
        if (i + 1 >= m)
        {
            res += extract_min();
            deleted();
        }
    }
    
    printf("%lld\n", res);
    
    return 0;
}