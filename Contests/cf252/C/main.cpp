#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
const int N = 310;
int n, m, k , arr[N][N];
vector< pair<int, int> > tr[N * N / 2 + 10];
int main() {
	cin >> n >> m >> k;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
		   arr[i][j] = -1; 
		}
	}
	int cur = 0;
    for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if ((j % 2 == 0) && (j + 1 < m)) {
				arr[i][j] = min(cur, k - 1);
			} 
			if (j % 2 == 1) {
				arr[i][j] = min(cur, k - 1);
				cur++;
			}  
		}
	}
	for (int i = 0; i < n; i++) {
		if (arr[i][m - 1] == -1) {
			if (i % 2 == 0) {
				if (cur < k && i + 1 < n) {
					arr[i][m - 1] = min(cur, k - 1);
				} else {
					arr[i][m - 1] = arr[i][m - 2];
				}
			}
			if (i % 2 == 1) {
				if (cur < k) {
					arr[i][m - 1] = min(cur, k - 1);
					cur++;
				} else {
					arr[i][m - 1] = arr[i][m - 2];
				}
			}
		}
	}
	int i = 0, j = 0;
	bool br = false;
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (arr[i][j] + 1 < k)
				tr[arr[i][j]].push_back(make_pair(i + 1, j + 1));
			else {
				br = true;
				break;
			}
		}
		if (br) break;
	}
	//cout << i << " " << j << "f"<<endl;
	for (int ii = i; ii < n; ii++) {
		if (ii - i == 0) {
			for (int jj = j; jj < m; jj++) 
				tr[arr[ii][jj]].push_back(make_pair(ii + 1, jj + 1));
		} else if ((ii - i) % 2 == 0) {
			for (int jj = 0; jj < m; jj++) 
				tr[arr[ii][jj]].push_back(make_pair(ii + 1, jj + 1));
			
		} else {
			for (int jj = m - 1; jj >= 0; jj--) 
				tr[arr[ii][jj]].push_back(make_pair(ii + 1, jj + 1));
		}
	}	
	for (int i = 0; i < k; i++) {
		cout << tr[i].size() << " " ;
		for (int j = 0; j < tr[i].size(); j++) {
			cout << tr[i][j].first << " " << tr[i][j].second << " ";
		}
		cout << endl;
	}
	return 0;
}