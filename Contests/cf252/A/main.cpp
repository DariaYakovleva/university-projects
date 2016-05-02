#include <cstdio>
#include <iostream>
#include <vector>

using namespace std;

int n, v;
vector<int> res;
int main() {
    cin >> n >> v;
	for (int i = 0; i < n; i++) {
		int k;
		bool yes = false;
		cin >> k;
		for (int j = 0; j < k; j++) {
			int a;
			cin >> a;
			if (a < v) yes = true;
		}
		if (yes) res.push_back(i + 1);
	}
	cout << res.size() << endl;
	for (int i = 0; i < res.size(); i++) {
		cout << res[i] << " ";
	}

	return 0;
}