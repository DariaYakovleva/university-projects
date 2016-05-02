#include <iostream>
#include <cstdio>
#include <vector>
#define ll long long

using namespace std;

const ll N = 100010, INF = (int)10e18 + 100;

ll n, arr[N];


void update(ll l, ll r, ll b, ll oper) {
    for (ll i = l; i < r; i++) {
        if (oper == 1)
            arr[i] = b;
        else {
            arr[i] += b;
        }
    }
    
}


ll rmq(ll l, ll r) {
    ll m = arr[l];
    for (ll i = l; i < r; i++)
        m = min(m, arr[i]);
    return m;
}

void init() {
    for (ll i = 0; i < n; i++) {
        arr[i] = INF;
    }
}

int main() 
{
    freopen("rmq2.in", "r", stdin);
    freopen("rmq2_right.out", "w", stdout);
    cin >> n;
    init();
    for (ll i = 0; i < n; i++) {
        cin >> arr[i];
    }
    string s;
    while (cin >> s) {
        ll a, b;
        cin >> a >> b;
        if (s[0] == 's') {
            ll c;
            cin >> c;
            update(a - 1, b, c, 1);
        } else  if (s[0] == 'a') {
            ll c;
            cin >> c;
            update(a - 1, b, c, 2);
            
        } else {
            cout << rmq(a - 1, b) << endl;
        }
        
    }
    
    return 0;
}