#include <bits/stdc++.h>

using namespace std;

int r = 0;
int b = 0;
int g = 0;

int n;

int main() { 
	cin >> n;
	string s;
	cin >> s;
	for (int i = 0; i < n; i++) {
		if (s[i] == 'B') b++;
		if (s[i] == 'R') r++;
		if (s[i] == 'G') g++;
	}	          
	if ((g > 0 && r > 0) || (g + r == 0 && b > 0) || (r > 1 && b > 0) || (g > 1 && b > 0)) cout << "B";
	if ((b > 0 && r > 0) || (b + r == 0 && g > 0) || (r > 1 && g > 0) || (b > 1 && g > 0)) cout << "G";
	if ((g > 0 && b > 0) || (g + b == 0 && r > 0) || (b > 1 && r > 0) || (g > 1 && r > 0)) cout << "R";
	cout << endl;



	return 0;
}