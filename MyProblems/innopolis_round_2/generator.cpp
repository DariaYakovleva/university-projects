#include "testlib.h"
#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <time.h>
#include <set>


using namespace std;

const int N = 1000000000;
const int M = 500010;
/*
<#assign maxN = 50/>
<#list 1..100 as testNumber>
    generator ${testNumber} ${maxN} > $
</#list>
*/

struct telep {
	int x;
	int l;
	int r;
	int cost;
	telep() {}
	telep(int a, int b, int c, int d) {
		x = a;
		l = b;
		r = c;
		cost = d;
	}
};

telep teleps[M];
int xx[M];
set<int> have;

int main(int argc, char* argv[]) {
	registerGen(argc, argv);
	int nn = atoi(argv[2]);
	int x = atoi(argv[3]);
	int cc = atoi(argv[4]);
//    freopen("teleports.in", "w", stdout);
    int n = nn;
    if (n > 3000) n = rnd.next(nn / 2, nn);
    int s = rnd.next(1, n);
//    cerr << n << endl;
	if (x < n) {
		x = 2 * n;
	}
	for (int i = 0; i < n; i++) {
		xx[i] = rnd.next(1, x);
		while (have.count(xx[i]) > 0) {
			xx[i] = rnd.next(1, x);
		}
		have.insert(xx[i]);
	}
	sort(xx, xx + n);
    for (int i = 0; i < n; i++) {
    	int x1 = xx[i];
    	int l = abs(x1 - xx[rnd.next(1, max(1, n - 2)) - 1]) + 1;
    	int r = abs(x1 - xx[rnd.next(1, max(1, n - 2)) - 1]) + 1;
        if (l > r) swap(l, r);
    	int cost = rnd.next(1, cc);
    	teleps[i] = telep(x1, l, r, cost);
    }
    shuffle(teleps, teleps + n);
    cout << n << " " << s << endl;
    for (int i = 0; i < n; i++) {
    	cout << teleps[i].x << " " << teleps[i].l << " " << teleps[i].r << " " << teleps[i].cost << endl;
    }
    return 0;
}