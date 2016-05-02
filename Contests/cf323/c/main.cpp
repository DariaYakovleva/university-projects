#include <bits/stdc++.h>

using namespace std;


const int N = 500010;
int n;
int arr[N];
int arr2[N];
vector<int> res;

int gcd(int a, int b) {
	while (b != 0) {
		int c = b;
		b = a % b;
		a = c;
	}
	return a;
}

int main() {
	cin >> n;
	for (int i = 0; i < n * n; i++) {
		cin >> arr[i];
	}
	if (n == 1) {
		cout << arr[0] << endl;
		return 0;
	}
	sort(arr, arr + n * n);
	arr2[0] = arr[n * n - 1];
	res.push_back(arr2[0]);
	arr2[1] = arr[n * n - 2];
	res.push_back(arr2[1]);
	arr2[2] = gcd(res[0], res[1]);
	arr2[3] = gcd(res[0], res[1]);
	sort(arr2, arr2 + 4);
	int pos = 4;
	for (int j = 2; j < n; j++) {
		int i = 0;
		while (pos - i - 1 >= 0 && arr[n * n - i - 1] == arr2[pos - i - 1]) {
			i++;
		}
		int a = arr[n * n - i - 1];
		res.push_back(a);
		arr2[pos] = a;
		pos++;
		for (int k = 0; k < (int)res.size() - 1; k++) {
			arr2[pos] = gcd(res[k], a);
			pos++;
			arr2[pos] = gcd(res[k], a);
			pos++;
		}
		sort(arr2, arr2 + pos);
	}
	for (int i = 0; i < (int)res.size(); i++) {
		cout << res[i] << " ";
	}
}