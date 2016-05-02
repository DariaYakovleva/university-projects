#include <iostream>
#include <cstdio>
#include <vector>
#include <cstdlib>
#include <ctime>
#define ll long long

using namespace std;

const ll N = 100010, INF = (ll)10e18 + 100;


int main() 
{
    srand(time(NULL));
    freopen("rmq2.in", "w", stdout);
    //freopen("rmq2_right.out", "w", stdout);
    ll n = 20;
    cout << n << endl;
    for (ll i = 0; i < n; i++) {
        cout << rand() % 100 + 100000000000000000 <<  " ";
    }
    cout << endl;
    ll m = 0;
    for (ll i = 0; i < m; i++) {
        ll a = rand() % (n / 2) + 1;
        ll b = a + rand() % (n - a - 1);
        cout << "set " << a <<  " " << b << " " << rand() % 10000 + 100000000000000000 << endl;

        for (ll i = 1; i <= n; i++)
            for (ll j = i; j <= n; j++)
                cout << "min " << i << " " << j << endl;
        
        a = rand() % (n / 2) + 1;
        b = a + rand() % (n - a - 1); 
        
        cout << "add " << a <<  " " << b <<  " " << rand() % 10000 + 100000000000000000 << endl;
        
        for (ll i = 1; i <= n; i++)
            for (ll j = i; j <= n; j++)
                cout << "min " << i << " " << j << endl;
    }
    
    for (ll ii = 1; ii <=n; ii++)
        for (ll jj = ii; jj<= n; jj++) {
            cout << "add " << ii <<  " " << jj <<  " " << rand() % 10000 + 100000000000000000 << endl;
            
            for (ll i = 1; i <= n; i++)
                for (ll j = i; j <= n; j++)
                    cout << "min " << i << " " << j << endl;
            
            ll a = rand() % (n / 2) + 1;
            ll b = a + rand() % (n - a - 1); 
            
              cout << "set " << ii <<  " " << jj << " " << rand() % 10000 + 100000000000000000 << endl;
            for (ll i = 1; i <= n; i++)
                for (ll j = i; j <= n; j++)
                    cout << "min " << i << " " << j << endl;
            
        }
    
    cout << endl;
    return 0;
}