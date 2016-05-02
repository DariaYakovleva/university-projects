//#include "testlib.h"
#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <time.h>


using namespace std;

const int N = 1000000000;

int main(int argc, char* argv[]) {
    freopen("tests.in", "r", stdin);
    srand(time(NULL));
    int test;
    int n;
    int ll;
    int rr;
    int c;
    int x;
    cin >> test;
    for (int j = 0; j < test; j++) {
    cin >> n;
    cin >> ll >> rr;
    cin >> x;
    cin >> c;
    string name;
    cin >> name;
    string name2 = "./tests/" + name + ".in";
    cerr << "create test " << j << " name=" << name << endl;
    freopen(name2.c_str(), "w", stdout);
    cout << n << " " << 1 << endl;
    for (int i = 1; i <= n; i++) {
    	int cost = c;
    	if (c == N) cost -= rand() % 10;
    	int xx = i;
    	if (c == -1) cost = rand() % N + 1;
    	if (x != -1) xx = rand() % N + 1; 
    	cout << xx << " " << ll << " " << rr << " " << cost << endl;
    }
    }
    return 0;
}