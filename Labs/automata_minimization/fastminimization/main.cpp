#include <bits/stdc++.h>
  
using namespace std;
  
const int N = 50010;
const int M = 26; 
int n, m, k;
int aut[N][M];
int naut[N][M];
vector<int> autr[N][M];
bool term[N];
bool used[N];
bool nterm[N];
int comp[N];
int cnt_t = 0;
int m1 = 0;
int num = 0;
  
void init() {
    for (int i = 0; i < N; i++) {
        term[i] = false;
        comp[i] = -1;
        nterm[i] = false;
        used[i] = false;
        for (int j = 0; j < M; j++) {
            aut[i][j] = 0;
            naut[i][j] = 0;
        }
    }
}
  
void dfs(int v) {
    used[v] = true;
    for (int i = 0; i < M; i++) {
        if (!used[aut[v][i]]) {
            dfs(aut[v][i]);
        }
    }
}
  
int classs[N];
queue<pair<int, int>> q;
vector<unordered_set<int>> p;
unordered_set<int> t;
unordered_set<int> nt;
vector<unordered_set<int>> sss;
  
void findClasses() {
    dfs(1);
    for (int i = 0; i < n; i++) {
        if (term[i]) {
            t.insert(i);
            classs[i] = 0;
//          inv[0].push_back(i);
        } else {
            nt.insert(i);
            classs[i] = 1;
//          inv[1].push_back(i);
        }
    }
    p.push_back(t);
    p.push_back(nt);
    sss.push_back(t);
    sss.push_back(nt);
    for (int i = 0; i < M; i++) {
        q.push({0, i});
        q.push({1, i});
    }
    while (!q.empty()) {
        const pair<int, int> cur = q.front();
        q.pop();
        map<int, vector<int>> inv;
        for (int q: sss[cur.first]) {
            for (int r: autr[q][cur.second]) {
//          int r = aut[q][cur.second];
                int i = classs[r];
                inv[i].push_back(r);
            }
        }
        for (pair<int, vector<int>> x: inv) if (x.second.size() > 0) {
        	int i = x.first;
            if (inv[i].size() < p[i].size()) {
                unordered_set<int> tmp;
                p.push_back(tmp);
                int j = p.size() - 1;
                for (int r: inv[i]) {
                    p[i].erase(r);
                    p[j].insert(r);
                }
                if (p[j].size() > p[i].size()) {
                    swap(p[i], p[j]);
                }
                for (int r: p[j]) {
                    classs[r] = j;
                }
                sss.push_back(p[j]);
                for (int i = 0; i < M; i++) {
                    q.push(make_pair(sss.size() - 1, i));
                }
            }
        }
    }
}
  
void buildDKA() {
    for (const unordered_set<int>& x: p) {
        if (x.find(0) != x.end()) {
            for (int y: x) {
                comp[y] = 0;
            }
        }
        if (x.find(1) != x.end() && comp[1] == -1) {
            num++;
            for (int y : x) {
                comp[y] = 1;
            }
        }
    }
    for (const unordered_set<int>& x: p) {
        int i = *x.begin();
        if (!used[i]) continue;
        if (comp[i] != -1) continue;
        num++;
        comp[i] = num;
        for (int y: x) {
            comp[y] = num;
        }
    }
//  for (int i = 0; i < n; i++) cout << "c" << i << "=" << comp[i] << ";";
    for (int i = 0; i < n; i++) {
        if (term[i] && comp[i] != -1 && !nterm[comp[i]]) {
            nterm[comp[i]] = true;
            cnt_t++;
        }   
    }
    if (nterm[0]) cnt_t--;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < M; j++) {
            if (comp[i] > 0 && comp[aut[i][j]] > 0 && naut[comp[i]][j] == 0) {
                naut[comp[i]][j] = comp[aut[i][j]];
                m1++;
            }
        }
    }
}
  
  
int main() {
    freopen("fastminimization.in", "r", stdin);
    freopen("fastminimization.out", "w", stdout);
    init();
    cin >> n >> m >> k; 
    for (int i = 0; i < k; i++) {
        int a;
        cin >> a;
        term[a] = true;
    }
    for (int i = 0; i < m; i++) {
        int a, b;
        char c;
        cin >> a >> b >> c;
        aut[a][c - 'a'] = b;
    }
    n++;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < M; j++) {
            autr[aut[i][j]][j].push_back(i);
        }
    }
  
    findClasses();
    buildDKA();
    cout << num << " " << m1 << " " << cnt_t << endl;
    for (int i = 1; i < n; i++) {
        if (nterm[i]) cout << i << " ";
    }
    cout << endl;
    for (int i = 1; i < n; i++) {
        for (int j = 0; j < M; j++) {
            if (naut[i][j] != 0) {
                cout << i << " " << naut[i][j] << " " << (char)('a' + j) << endl;
            }
        }
    }
  
}
