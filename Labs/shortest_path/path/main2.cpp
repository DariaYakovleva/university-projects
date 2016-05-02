#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <queue>
#define ll long long
 
using namespace std;
 
const int N = 3010;
const ll INF = 100000000000000010;
 
struct edge {
    int in, to, w;
    edge() {}
    edge(int a, int b, ll c) {
        in = a;
        to = b;
        w = c;
    }
};
 
int n, m, s;
vector<edge> g;
ll d[N];
bool no_path[N];
 
void ford(int st) {
    d[st] = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            int in = g[j].in, to = g[j].to;
            ll  w = g[j].w;
            if (d[in] < INF)
            if (d[to] > d[in] + w) {
                d[to] = d[in] + w;
            }
        }
    }
    for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
        int in = g[j].in, to = g[j].to;
        ll w = g[j].w;
        if (d[in] < INF)
        if (d[to] > d[in] + w) {
            d[to] = d[in] + w;
            no_path[to] = true;
        }
    }
    }  
}           
 
void init() {
    for (int i = 0; i < n; i++) {
        d[i] = INF;
        no_path[i] = false;
    }
}
 
int main() {
    freopen("path.in", "r", stdin);
    freopen("path.out", "w", stdout);
    cin >> n >> m >> s;
    s--;
    init();
    for (int i = 0; i < m; i++) {
        int a, b;
        ll c;
        cin >> a >> b >> c;
        a--;
        b--;
        g.push_back(edge(a, b, c));    
    }      
    ford(s);
    for (int i = 0; i < n; i++) {
        if (d[i] == INF) {
            cout << "*" << endl;
        } else if (no_path[i]) {
            cout << "-" << endl;
        } else {
            cout << d[i] << endl;
        }
    }
 
    return 0;
}