#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
const int N = 100010;
int n, arr[N], m = 1000000010;

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
    	cin >> arr[i];
    }
    int k = 0;
    for (int i = 0; i < n; i++) {
    	int m = 0;
       	for (int j = 0; j < n; j++)
       	    if (arr[j] > arr[m]) m = j;
       	int l = m, r = m;
       	while (l > 0 && arr[l - 1] == arr[l]) l--;
       	while (r + 1 < n && arr[r + 1] == arr[r]) r++;
       	int pmin = 0, pmax = 0;
       	if (r + 1 < n) {
       		pmax =  arr[r + 1];
       		pmin = arr[r + 1];
       	}
       	if (l - 1 >= 0) {
       		pmax = max(pmax, arr[l - 1]);
       		pmin = min(pmin, arr[l - 1]);
       	}
       	k += min(r - l + 1, arr[m] - pmax);
       	int nh;
       	if (arr[m] - pmax < r - l + 1) 
       		nh = pmax;
        else
        	nh = pmin;
       	for (int j = l; j <= r ; j++) arr[j] = nh;

    }          
    cout << min(k, n) << endl;
   	return 0;
}