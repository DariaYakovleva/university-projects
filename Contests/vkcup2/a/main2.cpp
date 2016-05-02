#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <set>

using namespace std;

const int N = 310;

vector< pair<long long, long long> > friends;
vector<long long> id;


int main() {
	long long n, d;
	cin >> n >> d;
	for (int i = 0; i < n; i++) {
		long long a, b;
		cin >> a >> b;
		bool f = false;
		for (int j = 0; j <id.size(); j++) {
			if (id[j] == a) {
				f = true;
			}
		}
		if (!f) id.push_back(a);
		f = false;
		for (int j = 0; j <id.size(); j++) {
			if (id[j] == b) {
				f = true;
			}
		}
		if (!f) id.push_back(b);
		friends.push_back(make_pair(a, b));
	}	

	vector< pair<long long, long long> > ans2;
	sort(id.begin(), id.end());
	for (int h =0 ; h <id.size(); h++) {
		long long im = id[h];
		int cnt = 0;
		ans2.clear();
		for (int i = 0; i < friends.size(); i++) {
			long long fr = -1;
			if (friends[i].first == im) fr = friends[i].second;
			if (friends[i].second == im) fr = friends[i].first;
			if (fr != -1) {
				cnt++;
				for (int j = 0; j < friends.size(); j++) {
					long long frfr = -1;
					if (friends[j].first == fr) frfr = friends[j].second;
					if (friends[j].second == fr) frfr = friends[j].first;
					if (frfr != -1 && frfr != im) {
						bool bad = false;
						for (int g = 0; g < friends.size(); g++) {
							long long hm = -1;
							if (friends[g].first == im) hm = friends[g].second;
							if (friends[g].second == im) hm = friends[g].first;
							if (hm == frfr) bad = true;
						}
						if (bad) continue;
						bool f = false;
						for (int k = 0; k < ans2.size(); k++) {
							if (ans2[k].first == frfr) {
								ans2[k].second++;
								f = true;
								break;   
							}
						}	
						if (!f) {
							ans2.push_back(make_pair(frfr, 1));
						}
					}
				}		
			}
		}
		vector<long long> ans;
		for (int j = 0; j < ans2.size(); j++) {
//			if (ans2[j].second / cnt * 100 >= cnt * d / 100) ans.push_back(ans2[j].first);
			if (ans2[j].second * 10000 >= cnt * d * cnt) ans.push_back(ans2[j].first);
		}
		sort(ans.begin(), ans.end());
		cout <<  im << ": " << ans.size() << " " ;
		for (int j = 0; j < ans.size(); j++) {
			cout << ans[j] << " ";
		}
		cout << endl;

	}

	return 0;
}


