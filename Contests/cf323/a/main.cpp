#include <bits/stdc++.h>

using namespace std;
const int N = 100;
bool used1[N];
bool used2[N];
int main() {
	int n;
	 cin >> n;
	 for (int i = 0; i <= n; i++) {
	 	used1[i] = false;
	 	used2[i] = false;
	 }
	 for (int i = 0; i < n * n; i++) {
	 	int a, b;
	 	cin >> a >> b;
	 	if (!used1[a] && !used2[b]) {
	 		cout << i + 1 << " " ;
	 		used1[a] = true;
	 		used2[b] = true;
		 }
	 }

	return 0;
}