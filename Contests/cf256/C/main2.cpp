#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 5010, INF = 1000000010;
int n, arr[N];

int paint(int l, int r, int prev) {
 	int m = INF;
 	//cout << l << " " << r << endl;
 	for (int i = l; i <= r; i++)
 		m = min(m, arr[i]);
 	int res = m - prev;
 	int pos = l;
 	while (pos <= r) {
 		while (pos <= r && arr[pos] == m) pos++;
 		int len = 0;
 		while (pos + len + 1 <= r && arr[pos + len + 1] > m)  len++;
 	    if (pos <= r && arr[pos] > m) res += paint(pos, pos + len, m);
 	    pos = pos + len + 1;
 	}
 	res = min(res, r - l + 1);
 	return res;
 	
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
    	cin >> arr[i];
    }
    cout << paint(0, n - 1, 0) << endl;

    return 0;
}