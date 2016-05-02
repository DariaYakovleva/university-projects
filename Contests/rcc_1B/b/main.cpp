#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>
#include <set>
#include <math.h>

using namespace std;

const int N = 1000010;
pair<int, int> arr[N];
double sum = 0;
int cnt = 0;
//set<int> val;
int n;

void go(int v, long long s, int d, double ver) {
	if (d == n) {
//		val.insert(s);
		sum += (double)s * ver;
		return;			                 	
	}	
	int l = v + d + 1;
	int r = l + 1;
//	cout << v << " " << l << " " << r << endl;
	if (arr[v].first < arr[v].second) {
		go(l, s + arr[v].first, d + 1, ver);
	} else if (arr[v].first == arr[v].second) {
		go(l, s + arr[v].first, d + 1, ver / 2.0);
		go(r, s + arr[v].second, d + 1, ver / 2.0);
	} else {
		go(r, s + arr[v].second, d + 1, ver);
	}
	return;
}

int main() {
	int test;
	scanf("%d", &test);
	for (int t = 0; t < test; t++) {
		scanf("%d", &n);
		sum = 0.0;
		cnt = 0;
//		val.clear();	
		int p = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i + 1; j++) {
				scanf("%d %d", &arr[p].first, &arr[p].second);
				p++;
			}
		}
		go(0, 0, 0, 1.0);
//		for (set<int>::iterator it = val.begin(); it != val.end(); it++) {
//			sum += (long long)(*it);
//			cnt++;
//		}
		printf("%.9f\n", sum);
	}

	return 0;
	
}