#define ll long long
#include <iostream>
#include <stdio.h>
#include <string.h>
#include <string>
#include <math.h>
#include <vector>
#include <algorithm>
#include <cstdlib>
#include <deque>
#include <cmath>
#include <set>
#include <map>
#include <ctime>

using namespace std;

inline int myrand()
{
    return abs((rand() << 16) ^ rand());   
}

int main()
{
    // double start = clock() / CLOCKS_PER_SEC;
    
    // double delta = (clock() - start) / CLOCKS_PER_SEC;
    
    // printf("%.5lf", delta);
    srand(time(0));
    
    int n = 500;
    
    cout << n << endl;
    for (int i = 0; i < n; i++)
    {
        cout << myrand()%1000 + 1 << " ";
        long long a = myrand()%1000000000 + 1;
        cout << a << " " << myrand()%100000000 + 1 + a << endl;
    }
    int m = 500000;
    cout << m << endl;
    for (int i = 0; i < m ;i++)
    {
        cout << myrand()%1000000000 + 1 << " " << myrand()%100000 + 1 << " " << myrand()%1000000000 + 1 << endl;
    }
    
    
    return 0;
}