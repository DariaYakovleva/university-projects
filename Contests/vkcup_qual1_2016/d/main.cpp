#include <bits/stdc++.h>

using namespace std;

const int N = 200010;
int arr[N];
int dp[N];
int prevv[N];
int n, m, s, d;
vector<string> res;

string toStr(int a) {
	string ans = "";
	if (a == 0) ans += "0";
	while (a > 0) {
		ans += (char)('0' + a % 10);
		a /= 10;
	}
	reverse(ans.begin(), ans.end());
	return ans;
}


int ddp(int pos) {
	if (dp[pos] != -1) return dp[pos];
	if (pos == 1) {
		dp[pos] = 0;
		prevv[pos] = 0;
		if ((n == 1 || arr[pos + 1] != arr[pos] + 1) && arr[pos] > s && d > 1) {
			dp[pos] = 1;
		}
		return dp[pos];
	}
	dp[pos] = 0;
	for (int i = pos - 1; i >= 0; i--) {
		int dist = arr[pos] - arr[i + 1] + 2;
		if (dist > d) break;
		if (arr[i + 1] - arr[i] - 2 >= s) {
//			cout << pos << " " << i << endl;
			dp[pos] = ddp(i);
			if (dp[pos] == 1) {
				prevv[pos] = i;
				return dp[pos];						
			}
		}
	}
	return dp[pos];
}

int main() {
	cin >> n >> m >> s >> d;
	arr[0] = 0;
	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
		dp[i] = -1;
		prevv[i] = -1;
	}
	sort(arr, arr + n + 1);
	dp[0] = 1;	
	if (ddp(n) == 0) {
		cout << "IMPOSSIBLE" << endl;
		return 0;
	}
	int cur = n;
	if (m != arr[n] + 1) res.push_back("RUN " + toStr(m - arr[n] - 1));
	while (cur > 0) {                          	
		int p = prevv[cur];
		res.push_back("JUMP " + toStr(arr[cur] - arr[p + 1] + 2));
		if (p == 0) {
			res.push_back("RUN " + toStr(arr[p + 1] - arr[p] - 1));
		} else {
			res.push_back("RUN " + toStr(arr[p + 1] - arr[p] - 2));
		}
		cur = p;
	}

	for (int i = res.size() - 1; i >= 0; i--) {
		cout << res[i] << endl;
	}
	
	return 0;
}
