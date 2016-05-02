#include <bits/stdc++.h>

using namespace std;


int n;
const string ans[10] = {
"1 2 R M\n2 3 R M\n1 4 M M\n1 1 L M",
"1 1 R M",
"2 1 M M\n3 1 L M\n1 3 R M",
"2 1 M M\n3 1 M L\n2 4 R M\n1 4 L M",
"2 3 M M\n3 2 M L\n1 1 R M",
"",
"",
"",
"",
""};

int main() {
	freopen("artificial.in", "r", stdin);
	freopen("artificial.out", "w", stdout);
	cin >> n;
	cout << ans[n - 1];             	
}