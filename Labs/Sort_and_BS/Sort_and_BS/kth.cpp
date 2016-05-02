//
//  main.cpp
//  Sort_and_BS
//
//  Created by Дарья Яковлева on 23.05.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <ctime>

using namespace std;

const int N = 30000010;
int n, k, a, b, c;
int arr[N];

int qsort(int l, int r, int k) {
    srand(time(NULL));
    if (l == r) return arr[l];
    int x = arr[l + rand() % (r - l)];
    int i = l, j = r;
    while (i <= j) {
        while (arr[i] < x) i++;
        while (arr[j] > x) j--;
        if (i <= j) {
            swap(arr[i], arr[j]);
            i++;
            j--;
        }
    }
    if (j + 1 == i) {
        if (k <= j) return qsort(l, j, k);
        return qsort(i, r, k);
    }
    else {
        if (k == j + 1) return arr[k];
        if (k <= j) return qsort(l, j, k);
        return qsort(i, r, k);
    }
}

int main() {
    freopen("kth.in", "r", stdin);
    freopen("kth.out", "w", stdout);
    cin >> n >> k;
     cin >> a >> b >> c >> arr[0] >> arr[1];
     for (int i = 2; i < n; i++) {
         arr[i] = a * arr[i - 2] + b * arr[i - 1] + c;
     }
     cout << qsort(0, n - 1, k - 1) << endl;
     


}

