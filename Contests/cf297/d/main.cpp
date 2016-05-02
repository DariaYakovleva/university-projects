#include <iostream>
#include <cstdio>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 2010;
char arr[N][N];
bool used[N][N];
int n, m;

int minX = N;
int maxX = -1;
int minY = N;
int maxY = -1; 

struct points {
	int x1, y1, x2, y2;
	points(){};
	points(int a, int b, int c, int d) {
		x1 = a; y1 = b; x2 = c; y2 = d;
	}
};
vector<points> rect;

bool cmp(points a, points b) {
	return (a.x1 <= b.x1) || (a.x1 == b.x1 && a.y2 >= b.y2);
}

void dfs(int i, int j) {
	used[i][j] = true;
	minX = min(minX, i);
	maxX = max(maxX, i);
	minY = min(minY, j);
	maxY = max(maxY, j);
	if (i > 0 && (!used[i - 1][j]) && arr[i - 1][j] == '.') {
		dfs(i - 1, j);
	}
	if (j > 0 && (!used[i][j - 1]) && arr[i][j - 1] == '.') {
		dfs(i, j - 1);
	}
	if (i < n - 1 && (!used[i + 1][j]) && arr[i + 1][j] == '.') {
		dfs(i + 1, j);
	}
	if (j < m - 1 && (!used[i][j + 1]) && arr[i][j + 1] == '.') {
		dfs(i, j + 1);
	}

}


int main() {
	cin >> n >> m;
	char c;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> c;
			arr[i][j] = c;
			used[i][j] = false;
		}
	}	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (!used[i][j] && arr[i][j] == '.') {
				minX = N;
				maxX = -1;
				minY = N;
				maxY = -1;
				dfs(i, j);
				rect.push_back(points(minX, minY, maxX, maxY));
			}
		}		
	}
	sort(rect.begin(), rect.end(), cmp);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (arr[i][j] == '*') {
				int l = -1, r = (int)rect.size();
				while (r - l > 1) {
					int m1 = (l + r) / 2;
					if (i < rect[m1].x1) {
						l = m1;
					} else {
						r = m1;
					}
				}
				for (int k = r; k < (int)rect.size(); k++) {
					if (i >= rect[k].x1 && i <= rect[k].x2 && j >= rect[k].y1 && j <= rect[k].y2) {
						arr[i][j] = '.';
						break;
					}
				}
			}
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << arr[i][j];
		}
		cout << endl;
	}
		



	return 0;
}