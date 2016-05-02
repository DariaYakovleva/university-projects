#include "testlib.h"
#include <iostream>
#include <cstdlib>
#include <time.h>


using namespace std;

const int N = 1000000000;

int main(int argc, char* argv[]) {
	registerGen(argc, argv);	
  	int seed = atoi(argv[1]);
  	int maxn = atoi(argv[2]);
  	int h = atoi(argv[3]);	
    int n = rnd.next(1, maxn);
    int m = rnd.next(1, maxn - n);
    int a;
    int b;
    int s = atoi(argv[4]);
    if (s == 1) {
    	a = rnd.next(min(n + 1, maxn), min(n + 100, maxn));
	    b = rnd.next(min(m + 1, maxn), min(m + 100, maxn));
    } else
    if (s == 2) {
    	a = n;
	    b = rnd.next(min(m + 1, maxn), min(m + 100, maxn));
    } else
    if (s == 3) {
    	a = rnd.next(min(n + 1, maxn), min(n + 100, maxn));
	    b = m;
    } else
	if (s == 4) {
    	a = n;
	    b = m;
    } else
    if (s == 5) {
    	a = rnd.next(1, max(1, n / 100));
	    b = rnd.next(1, max(1, m / 100));
    } else {
    	a = rnd.next(1, max(1, n - 1));
		b = rnd.next(1, max(1, m - 1));
	}
    	
    cout << h << endl;
    cout << n << " " << m << " " << a << " " << b << endl;

    return 0;
}