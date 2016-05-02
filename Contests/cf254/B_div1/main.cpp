#include <iostream>
#include <cstdio>
#define ll long long

using namespace std;

const int N = 100010;

ll x;
int n, d, a[N], b[N];

ll getNextX() {
    x = (x * 37 + 10007) % 1000000007;
    return x;
}

void initAB() {
    for(int i = 0; i < n; i++){
        a[i] = i + 1;
    }
    for(int i = 0; i < n; i++){
        swap(a[i], a[getNextX() % (i + 1)]);
    }
    for(int i = 0; i < n; i++){
        if (i < d)
            b[i] = 1;
        else
            b[i] = 0;
    }
    for(int i = 0; i < n; i++){
        swap(b[i], b[getNextX() % (i + 1)]);
    }
}

int main() {
    cin >> n >> d >> x;
	initAB();
	
	return 0;
}
