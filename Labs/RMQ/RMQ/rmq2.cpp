#include <iostream>
#include <cstdio>
#include <vector>
#define ll long long

using namespace std;

const ll N = 100010, INF = 1000000000000000100;

ll n, value[N * 4], arr[N], updval[4 * N], need[4 * N];

void build(ll v, ll l, ll r) {
    
    if (v >= 4 * N) 
        return;
    if (r - l == 1) {
        value[v] = arr[l];
        return;
    }
    ll m = (l + r) / 2;
    build(2 * v + 1, l, m);
    build(2 * v + 2, m, r);
    value[v] = min(value[v * 2 + 1], value[v * 2 + 2]);
}

void push(ll v) {
    if (need[v] == 0)
        return;
    if (2 * v + 2 < N * 4) {
        //push(2 * v + 2);
        if ((need[v] == 2) && (need[2 * v + 2] != 0)) {
                updval[2 * v + 2] += updval[v];
        } else {
            need[2 * v + 2] = need[v];
            updval[2 * v + 2] = updval[v];
        }
    }
    if (2 * v + 1 < N * 4) {
        //push(2 * v + 1);
        if ((need[v] == 2) && (need[2 * v + 1] != 0)) {
                updval[2 * v + 1] += updval[v];
        } else {
            need[2 * v + 1] = need[v];
            updval[2 * v + 1] = updval[v];
        }
    }
    if (need[v] == 1) {
        value[v] = updval[v];
    } else {
        value[v] += updval[v];
    }
    need[v] = 0;
    updval[v] = 0;
    
}

void update(ll v, ll l, ll r, ll ql, ll qr, ll b, ll oper) {
    
    if (v >= 4 * N) 
        return;
    push(v);
    if ((l >= qr) || (r <= ql))
        return;
    if ((l >= ql) && (r <= qr)) {
        need[v] = oper;
        updval[v] = b;
        push(v);
        return;
    }
    ll m = (l + r) / 2;
    update(2 * v + 1, l, m, ql, qr, b, oper);
    update(2 * v + 2, m, r, ql, qr, b, oper);
    if (r - l > 1)
        value[v] = min(value[v * 2 + 1], value[v * 2 + 2]);
}


ll rmq(ll v, ll l, ll r, ll ql, ll qr) {
    push(v);
    if ((l >= qr) || (r <= ql)) {
        return INF;
    }
    if ((l >= ql) && (r <= qr)) {
        return value[v];
    }
    ll m = (l + r) / 2;
    if (r - l == 1)
        return value[v];
    return min(rmq(2 * v + 1, l, m, ql, qr), rmq(2 * v + 2, m, r, ql, qr));
}

void init() {
    for (ll i = 0; i < N * 4; i++) {
        value[i] = INF;
        need[i] = 0;
        updval[i] = 0;
    }
}

int main() 
{
    freopen("rmq2.in", "r", stdin);
    freopen("rmq2.out", "w", stdout);
    init();
    cin >> n;
    for (ll i = 0; i < n; i++) {
        cin >> arr[i];
    }
    build(0, 0, n);
    string s;
    while (cin >> s) {
        ll a, b;
        cin >> a >> b;
        if (s[0] == 's') {
            ll c;
            cin >> c;
            update(0, 0, n, a - 1, b, c, 1);
        } else  if (s[0] == 'a') {
            ll c;
            cin >> c;
            update(0, 0, n, a - 1, b, c, 2);
        
        } else {
            cout << rmq(0, 0, n, a - 1, b) << endl;
        }
        
    }
    
    return 0;
}