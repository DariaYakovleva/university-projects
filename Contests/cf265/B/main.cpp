#include <cstdio>
#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>

using namespace std;
const int N = 1010;
int arr[N];

int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	int i = 0, res = 0;
	while (i < n) {
	    int beg = i;
	    while ((i < n) && (arr[i] == 1)) {
		    i++;
		}
		if (i != beg)
			res += (i - beg) + 1;
		i++;
	}
	cout << max(res - 1, 0) << endl;
    return 0;
}