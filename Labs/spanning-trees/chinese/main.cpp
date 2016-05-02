#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#define ll long long
 
using namespace std;
 
const int N = 1010;
const int M = 10010;
const int INF = 2000000100;
 
struct edge {
    int in, to, w;
    edge() {}
    edge(int a, int b, int c) {
        in = a;
        to = b;
        w = c;
    }
};
 
int n, m;
vector<edge> st_edges;
 
//cond begin
vector<int> g[N];
vector<int> g2[N];
vector<int> res;
bool colors[N];
int num[N];
 
void top_sort(int v) {
    colors[v] = true;
    for (size_t i = 0; i < g[v].size(); i++) {
        int to = g[v][i];
        if (!colors[to]) {
            top_sort(to);
        }
    }
    res.push_back(v);
}
 
void cond(int v, int numb) {
    colors[v] = true;
    num[v] = numb;
    for (size_t i = 0; i < g2[v].size(); i++) {
        int to = g2[v][i];
        if (!colors[to]) {
            cond(to, numb);
        }
    }
}
 
void init_cond() {
    for (int i = 0; i < N; i++) {
        colors[i] = false;
        num[i] = 0;
    }
    for (int i = 0; i < N; i++) {
        g[i].clear();
        g2[i].clear();
    }
    res.clear();
}
 
void cond_g(const vector<edge>& edges, int cnt) {
    init_cond();
    for (size_t i = 0; i < edges.size(); i++) {
        g[edges[i].in].push_back(edges[i].to);
        g2[edges[i].to].push_back(edges[i].in);
    }
    for (int i = 0; i < cnt; i++) {
        if (!colors[i]) {
            top_sort(i);
        }
    }
    reverse(res.begin(), res.end());
    for (int i = 0; i < N; i++)
        colors[i] = false;
    int cur = 0;
    for (int i = 0; i < cnt; i++) {
        if (colors[res[i]] == 0) {
            cond(res[i], cur);
            cur++;
        }      
    }
    //return num;
}
 
 
//cond end
 
bool used[N];
 
void dfs(int v, const vector<edge>& edges) {
    used[v] = 1;
    for (size_t i = 0; i < edges.size(); i++) {
        if (edges[i].in == v && (!used[edges[i].to]))
            dfs(edges[i].to, edges);
    }
}
 
void init_dfs() {
    for (int i = 0; i < N; i++) {
        used[i] = false;
    }
}
 
int min_e[N];
 
ll chinese(int root, vector<edge> edges, int cnt) {
    ll res = 0;
    for (int i = 0; i < cnt; i++)
        min_e[i] = INF;
    for (size_t i = 0; i < edges.size(); i++) {
        if (min_e[edges[i].to] > edges[i].w) {
            min_e[edges[i].to] = edges[i].w;
        }
    }                                          
//  cout << endl << "min_edges ";
	for (int i = 0; i < cnt; i++) {
		if (i != root && min_e[i] != INF) res += min_e[i];
	}
    vector<edge> min_edges;
    for (size_t i = 0; i < edges.size(); i++) {
    	edges[i].w -= min_e[edges[i].to];
        if (edges[i].w == 0) {
            min_edges.push_back(edges[i]);
//          cout << edges[i].in << " " << edges[i].to << " " << edges[i].w << ", ";
        }
    }
//    sort(min_edges.begin(), min_edges.end(), sr);
//    res = kruskal(min_edges);
//  cout << endl << cnt << " " << res << endl;
    init_dfs();
    dfs(root, min_edges);
    bool mst = true;
    for (size_t i = 0; i < min_edges.size(); i++) {
        if ((!used[min_edges[i].in]) || (!used[min_edges[i].to])) mst = false;
    }
    if (mst) return res;
 
    int new_cnt = 0;
    cond_g(min_edges, cnt);
//  cout << endl << "num ";
//  for (int i = 0; i < cnt; i++) cout << num[i] << " ";
    vector<edge> new_edges;
//  cout << endl << "new_edges ";
    for (size_t i = 0; i < edges.size(); i++) {
        if (num[edges[i].in] != num[edges[i].to]) {
            new_cnt = max(new_cnt, max(num[edges[i].in], num[edges[i].to]));
            new_edges.push_back(edge(num[edges[i].in], num[edges[i].to], edges[i].w));
//          cout << num[edges[i].in] << " " << num[edges[i].to] << " " << edges[i].w << ", ";
        }  
    }
    return res + chinese(num[root], new_edges, new_cnt + 1);
}
 
int main() {
    freopen("chinese.in", "r", stdin);
    freopen("chinese.out", "w", stdout);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        a--; b--;
        st_edges.push_back(edge(a, b, c));
    }
    for (int i = 0; i < n; i++) {
        used[i] = false;
    }
    dfs(0, st_edges);
    for (int i = 0; i < n; i++) {
        if (!used[i]) {
            cout << "NO" << endl;
            return 0;
        }
    }
    ll res = chinese(0, st_edges, n);
    cout << "YES" << endl << res << endl;
    return 0;
}
