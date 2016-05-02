
#include <bits/stdc++.h>


using namespace std;

const int N = 10010;
const long long INF = 100000010;

struct edge {
	int to;
	long long w;
	edge(){};
	edge(int a, long long b) {
		to = a;
		w = b;
	}
};

vector<edge> g[N];
int m, n, k;
int arr[N];
std::vector<std::vector<vector<long long>>> dp; 
std::vector<vector<long long>> dist;

void init0() {
	dp.clear();
	dist.clear();
	dp.resize(k + 10, vector<vector<long long>>(k + 10, vector<long long>(k + 10, INF)));
	dist.resize(k + 10, vector<long long>(k + 10, INF));	
//	for (int i = 0; i <= k; i++) {
//		for (int j = 0; j <= k; j++) {
//			for (int kk = 0; kk <= k; kk++) {
//				assert(dp[i][j][k] == INF);
//			}
//		}
//	}
}
/*
void init() {
	for (int i = 0; i <= k; i++) {
		for (int j = i; j <= k; j++) {
			for (int kk = j; kk <= k; kk++) {
				dp[i][j][kk] = INF;
			}
		}
	}
	for (int i = 0; i <= k; i++) {
		for (int j = 0; j <= k; j++) {
			dist[i][j] = INF;
		}
	}

			
}
*/
priority_queue< pair<int, int> > q;

long long aux[N];

void bfs(int st) {
	for (int i = 0; i <= n; i++) aux[i] = INF;
	int num = st;
	st = arr[st];
	aux[st] = 0;
	q.push(make_pair(st, 0));
	while (!q.empty()) {
		int v = q.top().first;
		q.pop();
		for (size_t i = 0; i < g[v].size(); i++) {
		    int to = g[v][i].to;
		    long long w = g[v][i].w;
			if (aux[to] > aux[v] + w) {
				aux[to] = aux[v] + w;
				q.push(make_pair(to, aux[to]));
			}
		}
	}
//	cerr << st << ":" << endl;
//	for (int i = 0; i < n; i++) {
//		cerr << aux[i] << " " ;
//	}
//	cerr << endl;
	for (int i = 0; i <= k; i++) {
		dist[num][i] = aux[arr[i]];
//		cerr << "arr " << i << " " << arr[i] << " " << num << " " << dist[num][i] << endl;
	}
}            


void ddp() {
	dp[0][0][0] = 0;
	for (int kk = 0; kk <= k; kk++) {
		int i, j, next;
		for (j = 0; j <= kk; j++) {
			for (i = 0; i <= j; i++) {
				if (dp[i][j][kk] == INF) continue;
			  	next = kk + 1;
//			  	cerr << k << ": " << i << " " << j << " " << kk << " " <<next << endl;
				dp[i][j][next] = min(dp[i][j][next], dp[i][j][kk] + dist[kk][next]);
//				cerr << 1 << endl;
				dp[i][kk][next] = min(dp[i][kk][next], dp[i][j][kk] + dist[j][next]);
//				cerr << 2 << endl;
				dp[j][kk][next] = min(dp[j][kk][next], dp[i][j][kk] + dist[i][next]);
//				cerr << 3 << endl;
				
			}
		}
//		cerr << "dp: " << dp.size() << " " << dp[i - 1][j - 1][next] << " " << dp[i - 1][kk][next] << " " << dp[j - 1][kk][next] << endl;
//		cerr << dist[kk][next] << " " << dist[j - 1][next] << " " << dist[i - 1][next] << endl;
//		cerr << k << ": " << i << " " << j << " " << kk << " " << next << endl;
	}	
	cerr << "!!!" << endl;
//	for (int i = 0; i <= k; i++) {
//		for (int j = i; j <= k; j++) {
//			for (int kk = j; kk <= k; kk++) {
//				cerr << i << " " << j << " " << kk << " " << dp[i][j][kk] << endl;
//			}
//		}
//	}
}

long long go() {
	cerr << "strart go" << endl;
	long long res = INF;
	for (int i = 0; i <= k; i++) {
		for (int j = i; j <= k; j++) {
//			cerr << i << " " << j << " " << k << " " << res << endl;
//			cerr << dp[i][j][k] << " " << dist[i][0] << " " <<  dist[j][0] << " !" << dist[k][0] << endl;
			res = min(res, dp[i][j][k] + dist[i][0] + dist[j][0] + dist[k][0]);			
		}
	}
	return res;		
}




int main() {
//	for (int i = 0; i < 10; i++) {
//		string ss = "" + (char)(i + '0');
//		freopen(("sets/orders0" + ss + ".in").c_str(), "r", stdin);
//		freopen(("sets/orders0" + ss + ".ans").c_str(), "w", stdout);
	cin >> n >> m;
	for (int i = 0; i < m; i++) {
		int a, b;
		long long c;
		cin >> a >> b >> c;
		a--;
		b--;
		g[a].push_back(edge(b, c));
		g[b].push_back(edge(a, c));		
	}           
	int t;             
	cin >> t;
	for (int i = 0; i < t; i++) {
		cerr << "test " << i + 1 << endl;
		int h;
		cin >> h >> k;
		h--;
		cerr << "start init " << k << endl;
		init0();
		cerr << "init0" << endl;
//		init();
		cerr << "arrs" << endl;
		arr[0] = h;
		for (int j = 1; j <= k; j++) {
			cin >> arr[j];
			arr[j]--;
		}
		cerr << "go bfs " << endl;
		for (int j = 0; j <= k; j++) {
			bfs(j);		
		}
		cerr << "go dp " << endl;
		ddp();
		cerr << "go go " << endl;
		cout << go() << endl;
	}
//}
	return 0;
}