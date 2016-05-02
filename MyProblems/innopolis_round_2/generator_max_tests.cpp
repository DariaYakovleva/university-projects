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
set<int> have;
int xx[M];


int main(int argc, char* argv[]) {
	registerGen(argc, argv);
	int n = atoi(argv[2]);
	int ll = atoi(argv[3]);
	int rr = atoi(argv[4]);
	int x = atoi(argv[5]);
	int c = atoi(argv[6]);
	int s = rnd.next(1, n);
	if (x < n) {
		x = 2 * n;
	}
	for (int i = 0;  i < n; i++) {
		xx[i] = rnd.next(1, x);
		while (have.count(xx[i]) > 0) {
			xx[i] = rnd.next(1, x);
		}
		have.insert(xx[i]);
	}
	sort(xx, xx + n);

    for (int i = 0; i < n; i++) {
    	int cost = c;
    	if (c == N) cost -= rnd.next(0, 100);
	   	if (c == -1) cost = rnd.next(1, N);
    	int x1 = i + 1;
    	if (x != -1) {
	    	x1 = xx[i];
	    }
    	teleps[i] = telep(x1, ll, rr, cost);
    }
    shuffle(teleps, teleps + n);
    cout << n << " " << s << endl;
    for (int i = 0; i < n; i++) {
    	cout << teleps[i].x << " " << teleps[i].l << " " << teleps[i].r << " " << teleps[i].cost << endl;
    }
    return 0;
}
