//#include <fstream>
#include <cstdio>
#include <iostream>
//#include <stdlib.h>
#include <algorithm>
#include <cmath>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <queue>
#include <stack>
#include <ctime>

#define ll long long
#define ld long double

using namespace std;

const ll inf = (ll)2 * 1e9;
const ll MOD = (ll)1e9 + 7;
const ll P = 239;
const ll MAX_N = 500;
const long double pi = 3.1415926535897932384626;
const long double eps = 1e-6;

ll f(ll n, ll d, ll x)
{
    ll cnt = 0;
    while (n > 0 && n % d == x)
    {
        cnt++;
        n /= d;
    }
    return cnt;
}

int main()
{
	freopen("input.txt", "r", stdin);
    freopen("output.txt", "w", stdout);    
    ll n, k;
    cin >> n >> k;
    if (n <= 100)
    {
        ll ans1 = 2, ans2 = 0;
        for (ll i = 2; i <= n + 1; i++)
        {
            ll cnt = f(n, i, k);
            if (cnt > ans2)
            {
                ans1 = i;
                ans2 = cnt;
            }
        }
        cout << ans1 << " " << ans2 << endl;
        return 0;
    }
    ll ans1, ans2;
    if (n % k == 0)
    {
        ans1 = n / k - 1;
        ans2 = 2;
    }
    else
    {
        ans1 = n - k;
        ans2 = 1;
    }
    for (ll i = 2; i * i <= n; i++)
    {
        ll cnt = f(n, i, k);
        if (cnt > ans2)
        {
            ans1 = i;
            ans2 = cnt;
        }
    }
    cout << ans1 << " " << ans2 << endl;
    return 0;
}
