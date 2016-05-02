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
#include <string>
#include <vector>

using namespace std;

const int N = 200;
int n, m, k;
int arr[N], arr2[N];
vector< pair<int, int> > g;
bool good = true;

bool check() {
    for (int i = 0; i < g.size(); i++) {
        int x = min(g[i].first, g[i].second), y = max(g[i].first, g[i].second);
        int tmp = min(arr2[x], arr2[y]);
        arr2[y] = max(arr2[x], arr2[y]);
        arr2[x] = tmp;
    }
    int pos = 0;
    while ((arr2[pos] == 0) && (pos < n)) pos++;
    while ((arr2[pos] == 1) && (pos < n)) pos++;
    return (pos == n);
}
int cnt = 0;
void bt(int pos) {
    if (pos == n) {
        for (int i = 0; i < n; i++)
            arr2[i] = arr[i];
        if (!check()) {
            good = false;
            for (int i = 0; i < n; i++)
                cout << arr[i] << " " ;
            cout << endl;
        }
        return;
    }
    arr[pos] = 0;
    bt(pos + 1);
    arr[pos] = 1;
    bt(pos + 1);
}

int main() {
   // freopen("netcheck.in", "r", stdin);
  //  freopen("netcheck.out", "w", stdout);
    cin >> n >> m >> k;
    for (int i = 0; i < k; i++) {
        int a;
        cin >> a;
        for (int j = 0; j < a; j++) {
            int b, c;
            cin >> b >> c;
            b--; c--;
            g.push_back(make_pair(b, c));
        }
    }
    bt(0);
    if (good)
        cout << "Yes" << endl;
    else 
        cout << "No" << endl;
}

