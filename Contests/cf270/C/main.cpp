#include <cstdio>
#include <iostream>
#include <algorithm>
#include <cstring>

using namespace std;

const int N = 100010;

int n;
int pos[N];
string names[N][2];                   

int main() {
	
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> names[i][0] >> names[i][1];
	}
	for (int i = 0; i < n; i++) {
		cin >> pos[i];
		pos[i]--;
	}
	bool yes = true;
	int cur = 2;
	for (int i = 1; i < n; i ++) {
		if (cur == 2) {
			if ((names[pos[i]][0] >= names[pos[i - 1]][0] || names[pos[i]][0] >= names[pos[i - 1]][1]) &&
			(names[pos[i]][1] >= names[pos[i - 1]][0] || names[pos[i]][1] >= names[pos[i - 1]][1])) {
				cur = 2;
			} else if ((names[pos[i]][0] >= names[pos[i - 1]][0] || names[pos[i]][0] >= names[pos[i - 1]][1])) {
				cur = 0;
			} else if (names[pos[i]][1] >= names[pos[i - 1]][0] || names[pos[i]][1] >= names[pos[i - 1]][1]) {
				cur = 1;
			} else {
				yes = false;
				break;
			}		
		} else {
			if ((names[pos[i]][0] >= names[pos[i - 1]][cur]) && (names[pos[i]][1] >= names[pos[i - 1]][cur])) {
				cur = 2;
			} else if (names[pos[i]][0] >= names[pos[i - 1]][cur]) {
				cur = 0;
			} else if (names[pos[i]][1] >= names[pos[i - 1]][cur]) {
				cur = 1;
			} else {
				yes = false;
				break;
			}	
		}
	}	
	if (yes)
		cout << "YES" << endl;
	else    	
		cout << "NO" << endl;	                    


	return 0;
}