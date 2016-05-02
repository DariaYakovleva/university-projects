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
	int x = atoi(argv[3]);
	int c = atoi(argv[4]);
	int s = 0;
	if (x != -1) {
		if (x < n) {
			x = 2 * n;
		}
		have.clear();
		for (int i = 0;  i < n; i++) {
			xx[i] = rnd.next(1, x);
			while (have.count(xx[i]) > 0) {
				xx[i] = rnd.next(1, x);
			}
			have.insert(xx[i]);
		}
		sort(xx, xx + n);

	}
    for (int i = 0; i < n; i++) {
    	int cost = c;
    	if (c == N) cost -= rnd.next(0, 100);
	   	if (c != 1) cost = rnd.next(1, c);
    	int xxx = i + 1;
       	int ll = 2 * xxx - xxx;
    	int rr = 2 * xxx + 1 - xxx;
    	if (x != -1) {
    		xxx = xx[i];
    		if (i * 2 + 1 < n) {
    			ll = xx[i * 2 + 1] - xxx;
    		} else {
    			ll = min(N, xx[i] * 2 + 1);
    		}
    		if (i * 2 + 2 < n) {
	    		rr = xx[i * 2 + 2] - xxx;
    		} else {
    			rr = min(N, xx[i] * 2 + 2);
    		} 
    	}
    	teleps[i] = telep(xxx, ll, rr, cost);
    }
    shuffle(teleps, teleps + n);
    for (int i = 0; i < n; i++) {
    	if (teleps[i].x < teleps[s].x)  s = i;
    }
    cout << n << " " << s + 1 << endl;
    for (int i = 0; i < n; i++) {
    	cout << teleps[i].x << " " << teleps[i].l << " " << teleps[i].r << " " << teleps[i].cost << endl;
    }
    return 0;
}
