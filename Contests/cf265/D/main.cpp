#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>

using namespace std;
const int N = 10;
int sq[N][N], res[N][N], a[N];
int used[N];
bool good = 0;


void dfs(int pos, int num) {
	if (good) return;
	if (num == 8) {
		good = 1;
		cout << "YES" << endl;
	    for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 3; j++) {
					cout << res[used[i]][j] << " ";
				}      
			cout << endl;
		}	
		return;
	}
//	used[pos] = num - 1;
	for (int i = 0; i < 8; i++) {
		if (used[i] == -1) {
		 for (int f = 0; f < 3; f++)
			for (int j = 0; j < 3; j++) {
				if (res[num - 1][f] == sq[i][j]) {
					for (int k = 0; k < 3; k++) 
						if (j != k && res[num - 1][(f + 1) % 3] == sq[i][k]) {
							res[num][f] = sq[i][j];
							res[num][(f + 1) % 3] = sq[i][k];
							res[num][(f + 2) % 3] = sq[i][3 - j - k];
							bool next = 1;
							for (int d = 0; d < num; d++) {
								if (res[d][0] == res[num][0] && res[d][1] == res[num][1] && res[d][2] == res[num][2])
									next = 0;								
							}
							if (next) {
								used[i] = num;
								dfs(i, num + 1);
								used[i] = -1; 
							}
						}
				}
			}	
		}
	}          
}

void bt(int pos, int have) {
	if (pos == 3) {
		//cout << a[0] << " " << a[1] <<  " " << a[2] << endl;
		res[0][0] = sq[0][a[0]];
		res[0][1] = sq[0][a[1]];
		res[0][2] = sq[0][a[2]];
		used[0] = 0;		
		dfs(0, 1);
		return;	
	}
	for (int i = 0; i < 3; i++) {
		if (!(have & (1 << i))) {
			a[pos] = i;
			have += (1 << i);
			bt(pos + 1, have);
			have -= (1 << i);
		}
	}
}

int main() {
	vector<int> g;
    for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 3; j++) {
			cin >> sq[i][j];
			int d = 0;
			for (int f = 0; f < g.size(); f++) {
				if (g[f] == sq[i][j]) d = 1;
			}
			if (!d) {
				g.push_back(sq[i][j]);
			}
		}	
		used[i] = -1;
	}
	if (g.size() > 6) {
	 	cout << "NO" << endl;
		return 0;
	}
	bt(0, 0);
	if (!good) 
		cout << "NO" << endl;
	return 0;
}