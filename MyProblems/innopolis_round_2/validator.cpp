#include "testlib.h"
using namespace std;

const int N = 500000;
const int M = 1000000000;
int n;
int s;
int x[N];
int lleft[N];
int rright[N];
int cost[N];

int main() {
    registerValidation();
    
    n = inf.readInt(1, N);
    inf.readSpace();
    s = inf.readInt(1, n);
    inf.readEoln();
    for (int i = 0; i < n; i++) {
    	x[i] = inf.readInt(1, M);
	    inf.readSpace();
    	lleft[i] = inf.readInt(1, M);
	    inf.readSpace();
    	rright[i] = inf.readInt(1, M);
        inf.readSpace();
    	cost[i] = inf.readInt(1, M);	
    	inf.readEoln();
    }
    inf.readEof();
    for (int i = 0; i < n; i++) {
    	ensuref(lleft[i] <= rright[i], "left > right");
    	if (i > 0) ensuref(x[i] != x[i - 1], "two coordinates are equal");
    }

    return 0;
}