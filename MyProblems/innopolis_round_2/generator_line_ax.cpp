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
	registerGen(argc, argv, 1);
	int n = atoi(argv[2]);
	int x = N;
	int s = 0;
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
    	int cost = N;
    	int xxx = xx[i];
    	int ll;
    	int rr;
       	if (i > 0 && i + 1 < n) {
    		ll = min(xxx - xx[i - 1], xx[i + 1] - xxx);
    		rr = max(xxx - xx[i - 1], xx[i + 1] - xxx);
    	} else {
    		if (i == 0) {
    			ll = xx[1] - xxx;
    			rr = xx[2] - xxx;
    		} else {
    			ll = xxx - xx[n - 2];
    			rr = xxx - xx[n - 3];
    		}
    	}
    	teleps[i] = telep(xxx, ll, rr, cost);
    }
    shuffle(teleps, teleps + n);
    for (int i = 0; i < n; i++) {
    	if (teleps[i].x < teleps[s].x) s = i;
    }
    cout << n << " " << s + 1 << endl;
    for (int i = 0; i < n; i++) {
    	cout << teleps[i].x << " " << teleps[i].l << " " << teleps[i].r << " " << teleps[i].cost << endl;
    }
    return 0;
}
