#include <bits/stdc++.h>
 
using namespace std;
 
int start, finish;
vector<vector<int> > v(239);
int cnt = 0;
 
 
void bfs(){
    queue<pair<int, int> > q;
    q.push({start, 0});
    while (!q.empty()){
    	cnt++;
    	cerr << cnt << endl;
        int vertex = q.front().first;
        int d = q.front().second;
        if (vertex == finish){
            cout << d;
            exit(0);
        }
        q.pop();
        for (int i = 0; i < v[vertex].size(); i++){
            q.push({v[vertex][i], d + 1});
        }
    }
}
 
int get_hash(string str){
    int out = 0;
    for (int i = 0; i < str.size(); i++){
        out *= 239;
        out += str[i];
        out %= 1LL * int(1e6 + 7);
    }
    return out;
}
 
int main(){
    freopen("alchemy.in", "r", stdin);
    freopen("alchemy.out", "w", stdout);
    ios_base::sync_with_stdio(false);
 
    set<int> already_declared;
    map<int, int> hi_Dasha;
 
    int n;
    cin >> n;
 
    int cnt = 0;
 
    for (int i = 0; i < n; i++){
        int f, s;
        string ff, ss;
        cin >> ff >> ss >> ss;
        f = get_hash(ff);
        s = get_hash(ss);
        if (already_declared.count(f) == 0){
            already_declared.insert(f);
            hi_Dasha[f] = cnt++;
        }
 
        if (already_declared.count(s) == 0){
            already_declared.insert(s);
            hi_Dasha[s] = cnt++;
        }
        v[hi_Dasha[f]].push_back(hi_Dasha[s]);
    }
    string startt, finishh;
    cin >> startt >> finishh;
 
    if (already_declared.count(get_hash(startt)) == 0 || already_declared.count(get_hash(finishh)) == 0){
        cout << -1;
        return 0;
    }
    cerr << 1 << endl;
    start = hi_Dasha[get_hash(startt)];
    finish = hi_Dasha[get_hash(finishh)];
     cerr << 2 << endl;
    bfs();
    cout << -1;
    return 0;
}