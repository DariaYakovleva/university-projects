#include <iostream>
#include <cstdio>
#include <algorithm>

using namespace std;

const int N = 2010;

struct my {
	int t, h, m, use;
}arr[N];

bool sr(my a, my b) {
	return (a.h < b.h) || ((a.h == b.h) && (a.m > b.m));
}

int n, m;
int main() {
    cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> arr[i].t >> arr[i].h >> arr[i].m;
		arr[i].use = 0;
	}
	sort(arr, arr + n, sr);
	int res1 = 0, ct = 0, ch = m;
	while (true) {
		int num = -1;
		for (int i = 0; i < n; i++) {
			if (arr[i].h <= ch && arr[i].t != ct && !arr[i].use && (num == -1 || arr[i].m > arr[num].m)) {
				num = i;
			}
		}
		if (num == -1) break;
		ct = arr[num].t;
		ch += arr[num].m;
		arr[num].use = 1;
		res1++;
	}
	for (int i = 0; i < n; i++) {
		arr[i].use = 0;
	}
	int res2 = 0;
 	ct = 1;
	ch = m;
	while (true) {
		int num = -1;
		for (int i = 0; i < n; i++) {
			if (arr[i].h <= ch && arr[i].t != ct && !arr[i].use && (num == -1 || arr[i].m > arr[num].m)) {
				num = i;
			}
		}
		if (num == -1) break;
		ct = arr[num].t;
		ch += arr[num].m;
		arr[num].use = 1;
		res2++;
	}
	cout << max(res1, res2);
	return 0;
}