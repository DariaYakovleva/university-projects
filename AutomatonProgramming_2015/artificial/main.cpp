#include <bits/stdc++.h>

using namespace std;


const int R = 0;
const int B = 1;
const int L = 2;
const int T = 3;
int di[4] = {0, 1, 0, -1};
int dj[4] = {1, 0, -1, 0};

const int N = 110;
const char lleft = 'L';
const char rright = 'R';
const char mov = 'M';
char step[3] = {'M', 'L', 'R'};
int stars = 0;

int n, m, k;

pair<int, int> tree[N];
pair<char, char> moove[N];
char ant[N][N];


bool check() {
	int cnt = 0;
	int state = 0;
	int go = R;
	int pos_i = 0;
	int pos_j = 0;
	for (int i = 0; i < k; i++) {
		int next = 0;
		if (ant[(pos_i + di[go] + m) % m][(pos_j + dj[go] + m) % m] == '*') {
			next = 1;		
		}		
		if (next == 0) {
			if (moove[state].first == lleft) {
				go = (go - 1 + 4) % 4;
			} else if (moove[state].first == rright) { 
				go = (go + 1) % 4;
			} else {
				pos_i = (pos_i + di[go] + m) % m;
				pos_j = (pos_j + dj[go] + m) % m;
//				ant[pos_i][pos_j] = '@';
			}
			state = tree[state].first;
		} else {
			if (moove[state].second == lleft) {
				go = (go - 1 + 4) % 4;
			} else if (moove[state].second == rright) { 
				go = (go + 1) % 4;
			} else {
				pos_i = (pos_i + di[go] + m) % m;
				pos_j = (pos_j + dj[go] + m) % m;
				cnt++;
				ant[pos_i][pos_j] = '#';
			}
			state = tree[state].second;
		}
		if (k - i < stars - cnt - 1) return false;						
	}
	if (cnt == stars) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				cout << ant[i][j];
			}
			cout << endl;
		}
	}
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < m; j++) {
			if (ant[i][j] == '#') ant[i][j] = '*';
		}
	}
//	if (cnt > 10) cerr << cnt << " " << k << endl;
	return (cnt == stars);
}

bool bt(int pos) {
	if (pos == n) {
		if (check()) {
			cerr << "OK" << endl;
			for (int i = 0; i < n; i++) {
				cout << tree[i].first + 1 << " " << tree[i].second + 1 << " " << moove[i].first << " " << moove[i].second << endl;
			}
			return true;
		}
		return false;
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			tree[pos].first = i;
			tree[pos].second = j;
			for (int k1 = 0; k1 < 3; k1++) {
				for (int d = 0; d < 3; d++) {
					moove[pos].first = step[k1];
					moove[pos].second = step[d];
					if (bt(pos + 1)) return true;
				}
			} 
		}
	}
	return false;	
}

int main() {
	freopen("artificial.in", "r", stdin);
	freopen("artificial.out", "w", stdout);
	cin >> m >> n >> k;
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < m; j++) {
			cin >> ant[i][j];
			if (ant[i][j] == '*') stars++;
		}
	}
	bt(0);	             	
}