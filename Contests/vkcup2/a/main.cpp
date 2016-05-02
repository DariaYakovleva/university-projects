#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

const int N = 310;

vector< pair<int, int> > id;
set<int> friends[N]; 


int main() {
	int n, k;
	cin >> n >> k;
	for (int i = 0; i < n; i++) {
		int a, b;
		cin >> a >> b;
		bool fa = false;
		bool fb = false;
		for (int j = 0; j < id.size(); j++) {
			if (id[j].first == a) {
				friends[j].insert(b);
				fa = true;
			}
			if (id[j].first == b) {
				friends[j].insert(a);
				fb = true;
			}
		}
		if (!fa) {
			id.push_back(make_pair(a, id.size()));
			friends[id.size() - 1].insert(b);
		}
		if (!fb) {
			id.push_back(make_pair(b, id.size()));
			friends[id.size() - 1].insert(a);
		}
	}
	sort(id.begin(), id.end());
	for (int i = 0; i < id.size(); i++) {
	    vector< pair<int, int> > ans;
	    int mypos = id[i].second;
		for (set<int>::iterator it = friends[mypos].begin(); it != friends[mypos].end(); ++it) {
			int myfriens = *it;
			int posfr;
			for (int j = 0; j < id.size(); j++) {
				if (myfriens == id[j].first) {
					posfr = id[j].second;
					break;
				}
			}
			for (set<int>::iterator it2 = friends[posfr].begin(); it2 != friends[posfr].end(); ++it2) {
				int frmyfr = *it2;
				if (friends[mypos].count(frmyfr) == 0 && frmyfr != id[i].first) {
					int f = false;
					for (int k = 0; k < ans.size(); k++) {
						if (ans[k].first == frmyfr) {
							ans[k].second++;
							f = true;
						}			
					}
					if (!f) {
						ans.push_back(make_pair(frmyfr, 1));
					}
				}
			}
		}	
		vector<int> ans2;
		for (int j = 0; j < ans.size(); j++) {
			if (ans[j].second * 100 >= friends[mypos].size() * k) ans2.push_back(ans[j].first);
		}	
		cout << id[i].first << ": " << ans2.size() << " ";
		sort(ans2.begin(), ans2.end());
		for (int j = 0; j < ans2.size(); j++) {
			cout << ans2[j] << " ";
		}
		cout << endl;
	}
	


	return 0;
}


