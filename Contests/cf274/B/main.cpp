#include <iostream>
#include <cstdio>
#include <algorithm>
#include <set>
#include <vector>

using namespace std;

const int N = 100010;
int n, ll, x, y;
int arr[N];

set<int> s;


int bs(int st, int a) {
	int l = st - 1, r = n;
	while (r - l > 1) {
		int m = (l + r) / 2;
		if (arr[m] - arr[st] < a) {
			l = m;
		} else {
			r = m;
		}	
    }
    if (r < n && arr[r] - arr[st] == a) return -1;
    return 1;

}

int main() {	
	cin >> n >> ll >> x >> y;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	bool xx = false;
	for (int i = 0; i < n; i++) {
		int res = bs(i, x);
		if (res == -1) {
			xx = true;
			break;
		} else {
			if (arr[i] - x >= 0 && arr[i] - x <= ll) s.insert(arr[i] - x);
			if (arr[i] + x >= 0 && arr[i] + x <= ll) s.insert(arr[i] + x);
				
		}
	}
	int one = -1;
	for (int i = 0; i < n; i++) {
		int res = bs(i, y);
		if (res == -1) {
			if (xx) {
				cout << 0 << endl;
			} else {
				cout << 1 << endl << x << endl;
			}
			return 0;
		}
		else {
			if (xx) continue;
			if (s.find(arr[i] + y) != s.end()) {
				one = arr[i] + y;	
			}	
			if (s.find(arr[i] - y) != s.end()) {
				one = arr[i] - y;	
			}
		}
	}
	for (std::set<int>::iterator i = s.begin(); i != s.end(); ++i) {
		int res = bs(0, *i + y);
		if (res == -1) {
			one = *i;
		}
		res = bs(0, *i - y);
		if (res == -1) {
			one = *i;
		}			
	}

	if (one != -1) {
		cout << 1 << endl << one << endl;
	} else if (xx) {
		cout << 1 << endl << y << endl;
	} else {
		cout << 2 << endl << x << " " << y << endl;
	}

	    
	return 0;
}