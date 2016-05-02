#include <iostream>
#include <cstdio>


using namespace std;
const int N = 100010;
int n, arr[N], dp[N][2], end[N];

int find(int pos, int ch) {
	if (pos == 0) return dp[pos][ch] = 1;
	if (dp[pos][ch] != -1) return dp[pos][ch];
	if (ch == 0) {
		if (arr[pos - 1] < arr[pos]) return dp[pos][ch] = find(pos - 1, ch) + 1;	
		else return dp[pos][ch] = 1;
	} else {
		if (pos == 1) {
			end[pos] = arr[pos];
			return dp[pos][ch] = 2;
		}
		find(pos - 1, ch);
		if (end[pos - 1] < arr[pos]) {
			dp[pos][ch] = max(dp[pos][ch], find(pos - 1, ch) + 1);
			end[pos] = arr[pos];
		}
		if (arr[pos] - arr[pos - 2] > 1) {
			dp[pos][ch] = max(dp[pos][ch], find(pos - 2, 0) + 2);
			end[pos] = arr[pos];
		}
		if (find(pos - 1, 0) + 1 > dp[pos][ch]) {
			end[pos] = arr[pos - 1] + 1;
			dp[pos][ch] = find(pos - 1, 0) + 1;
		}
		dp[pos][ch] = max(dp[pos][ch], min(find(pos, 0) + 1, pos + 1));
		return dp[pos][ch];		 
	}	
}


int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		dp[i][0] = dp[i][1] = -1;
	}
	end[0] = arr[0];
	int res = 0;
	for (int i = 0; i < n; i++)
		res = max(res, max(find(i, 1), find(i, 1)));
	cout << res << endl;


	return 0;
}