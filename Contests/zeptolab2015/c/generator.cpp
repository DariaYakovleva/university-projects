#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <time.h>


using namespace std;

const int N = 1000000000;

int main(int argc, char* argv[]) {
    freopen("teleports.in", "w", stdout);
    srand(time(NULL) + atoi(argv[0]));
    int n = rand() % 10;
    cout << n << endl;
    for (int i = 1; i <= n; i++) {
    	int l = rand() % i;
    	int r = rand() % (n - i + 1);
    	int cost = rand() % 10;
    	cout << l << " " << r << " " << cost << endl;
    }
    cout << n << endl;
    for (int i = 0; i < n; i++) {
    	cout << i + 1 << endl;
    }
    return 0;
}