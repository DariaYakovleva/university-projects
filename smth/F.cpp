#include <iostream>

using namespace std;

long long arr[100];

void shpiling(long long n)
{
    long long k = 1;
    long long c;
    int i = 0;
    while (k <= n)
    {
        i++;
        c = k;
        k = arr[i];
    }
    int ii = 0;
    while (c * ii <= n)
    {
        ii++;
    }
    i--;
    ii--;
    cout << ii << " " << i << endl; // ii шпилингов i уровня
    n = n - c * ii;
    if (n > 0)
        shpiling(n);
    return;
}

int main()
{
    long long n;
    cin >> n;
    arr[0] = 1;
    for(int i = 1; i < 100; i++)
    {
        arr[i] = i * 10 * arr[i - 1];
    }
    shpiling(n);
    return 0;
}
