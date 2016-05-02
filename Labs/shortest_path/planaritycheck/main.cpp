#include <iostream>
#include <cstdio>
#include <algorithm>
#include <vector>
#include <set>
#include <cstring>

using namespace std;

const int N = 10;

int t;
int n;
int g[N][N];
int arr[N];
vector<int> v1;
vector<int> v2;
int v[N];
string s;
bool k33 = false;
int p = 0;

void k3(int pos, int k) {
	if (pos == 6) {
		if (k < 3) return;
		v1.clear();
		v2.clear();
		for (int i = 0; i < 6; i++) {
			if (arr[i]) v1.push_back(i);
			else v2.push_back(i);
		}
		for (int i = 0; i < v1.size(); i++)
			for (int j = 0; j < v2.size(); j++) {
		   		if (g[v1[i]][v2[j]] + g[v2[j]][v1[i]] != 2) return;
	    	}	
	 	k33 = true;
	 	return;	
	}
	if (k < 3) {
		arr[pos] = 1;
		k3(pos + 1, k + 1);
		arr[pos] = 0;
		k3(pos + 1, k);
	}
	else {
		arr[pos] = 0;
		k3(pos + 1, k);
	}	
}


bool k5(int k) {	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++)  if (i != k && j != k && i != j) {
			if (g[i][j] != 1) { 
				return false;
			}	
		}
	}
	return true;
}

void init() {
	k33 = false;
	for (int i = 0; i < n; i++) 
			g[i][i] = 0;
	int cur = 0;
	for (int i = 0; i < n; i++) 
		for (int j = 0; j < i; j++)  {
			g[i][j] = (s[cur] - '0');
			g[j][i] = (s[cur] - '0');
			cur++;
		} 
}

int main() {
	freopen("planaritycheck.in", "r", stdin);
	freopen("planaritycheck.out", "w", stdout);
	cin >> t;
	for (int x = 0; x < t; x++) {
		cin >> s;
		if (s.size() <= 6) cout << "YES" << endl;
		else {
			if (s.size() == 10) n = 5;
			else n = 6;
			init();
			if (n == 5) {
				if (k5(-1)) {
					cout << "NO" << endl;
				} else {
					cout << "YES" << endl;
				}
			} else {
				bool pl = true;
				for (int i = 0; i < n; i++) {
					p = 0;
					for (int j = 0; j < n; j++)
					{
						if (g[i][j] == 1) {
							v[p] = j; 
							p++;
						}
					}
					if (p == 2) {
				    	g[v[0]][v[1]] = 1;
				   		g[v[1]][v[0]] = 1; 
					}
					if (k5(i)) pl = false;
					init();
				}
				k3(0, 0);
				if (k33) pl = false;
				if (pl) cout << "YES" << endl;
				else cout << "NO" << endl;
			}
		}
		        	
	}
	return 0;
}