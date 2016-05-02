#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>


using namepsace std;

const int N = 2010;

struct vertex

int n;
int colors[N];
bool used[N];
bool tree true;


void dfs(int v) {
	used[v] = 1;
	int pos = -1;
	for (int i = 0; i < n; i++) {
		if ((pos == -1 || dist[v][i] < dist[v][pos]) && colors[v] != colors[i] && !used[i])
			pos = i;
	}
	if (pos == -1) { 
		tree = false;
		break;
    }
    colors[pos] = colors[v];
    dfs(pos);


}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		colors[i] = i;
		used[i] = false;
		for (int j = 0; j < n; j++) {
			cin >> dist[i][j];
		}
    }



	return 0;
}	