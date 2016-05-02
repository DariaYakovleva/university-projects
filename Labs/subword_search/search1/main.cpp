#include <iostream>
#include <cstdio>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string p, t;

bool substr(int pos) {
	int ind = 0;
	while (((pos + ind) < (int)t.size()) && (ind < (int)p.size()) && (t[pos + ind] == p[ind])) {
		ind++;
	}
	return (ind == (int)p.size());
}

int main() {
	freopen("search1.in", "r", stdin);
	freopen("search1.out", "w", stdout);
	getline(cin, p);
	getline(cin, t);
	vector<int> ans;
	for (int i = 0; i < (int)t.size(); i++) {
		if (substr(i)) ans.push_back(i);	
	}
	cout << ans.size() << endl;
	for (int i = 0; i < (int)ans.size(); i++) {
		cout << ans[i] + 1  <<  " ";
	}

	return 0;
}