#include <iostream>
#include <cstdio>
#include <vector>
#include <cstring>

using namespace std;
int n, m;
const int N = 1010;
const int INF = 100000010;
char image[N][N];
int sx = -1, sy = -1;
int mx = 0, my = 0;
int cntX = 0;

int ok(int x, int y) {
    int cnt = 0;
	for (int i = sx; i < sx + x; i++)
		for (int j = sy; j < sy + y; j++) {
			if (image[i][j] == 'X') cnt++;	
			else return -1;
		}
	int xx = sx + x - 1;
	int yy = sy + y - 1;
	int sh = 1; 
    while (sh) {
    	sh = 0;
    	if (xx + 1 < n) {
    		bool l = true;
    		for (int j = yy - y + 1; j <= yy; j++) {
    			if (!(image[xx + 1][j] == 'X')) l = false;
			}
			if (l) {
				cnt += y;
				xx++; 
				sh = 1;
			}
	    }
    	if (yy + 1 < m) {
    	bool d = true;
    		for (int i = xx - x + 1; i <= xx; i++) 
    			if (!(image[i][yy + 1] == 'X')) d = false;
			if (d) {
				cnt += x;
				yy++;
				sh = 1;
	    	}
	    }	
   }
   if (cnt == cntX) return x * y;
   return -1;

}

int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> image[i][j];
			if (image[i][j] == 'X') {
				cntX++;
				if (sx == -1)
					sx = i, sy = j;				                                         	
			}
		}
	}
	while (sx + mx < n && image[sx + mx][sy] == 'X') mx++;
	while (sy + my < m && image[sx][sy + my] == 'X') my++;
//	cout << sx << " " << sy << " " << mx << " " << my << endl;
	//x is real
	int res = INF;
	for (int i = 1; i <= my; i++) {
	    int tmp = ok(mx, i);
	    if (tmp != -1)
			res = min(res, tmp);
	}
	//y is real
	for (int i = 1; i <= mx; i++) {
        int tmp = ok(i, my);
	    if (tmp != -1)
			res = min(res, tmp);
	}
	if (res == INF) {
		cout << "-1" << endl;
	} else {
		cout << res << endl;
	}

	return 0;
}