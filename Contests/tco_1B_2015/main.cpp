#include <vector>
#include <iostream>
#include <cstdio>
#include <algorithm>
#include <string>
using namespace std;
const int N = 60;
class TheTips {
	int n;
	int used[N];
	vector<string> clues1;
	vector< pair<int, int> > prob;
	
	int dfs(int v) {
		used[v] = true;
		int res = 1;
		for (int i = 0; i < n; i++) {
			if (clues1[v][i] == 'Y' && !used[i]) {
				res += dfs(i);
			}
		}
		return res;
	}
	int solve(vector<string> clues, vector<int> probability) {
		n = (int)clues.size();
		clues1 = clues;
		double res = 0.0;
		for (int i = 0; i < n; i++) {
			used[i] = false;
			prob.push_back(make_pair(-probability[i], i));
		}
		sort(prob.begin(), prob.end());
		for (int i = 0; i < n; i++) {
			if (!used[prob[i].second]) {
				res += (double)dfs(i) * prob[i].first / 100;				
			}
		}
		return res;
	
	}       
};

int main() {
}