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

const int N = 100010;
int n;
int arr[N];

void antiqs(int l, int r) {
    
    arr[1] = 1;
    arr[2] = 3;
    arr[3] = 2;
    for (int i = 4; i <= n; i++) {
        int tmp = arr[(i + 1) / 2];
        arr[(i + 1) / 2] = i;
        arr[i] = tmp;
    }
    
}

int main() {
    freopen("antiqs.in", "r", stdin);
    freopen("antiqs.out", "w", stdout);
    cin >> n;
    antiqs(1, n);
    for (int i = 1; i <= n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

