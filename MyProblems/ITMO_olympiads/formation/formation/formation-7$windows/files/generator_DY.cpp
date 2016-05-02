#include "testlib.h"
#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <time.h>
#include <set>


using namespace std;

const int N = 1000000000;
/*
<#assign maxN = 50/>
<#list 1..100 as testNumber>
    generator ${testNumber} ${maxN} > $
</#list>
*/

int n, m;

int main(int argc, char* argv[]) {
	registerGen(argc, argv);
	int n = atoi(argv[2]);
    int len = min(rnd.next(2, atoi(argv[3])), n);
    int st = rnd.next(0, n - len);
//    cerr << "len st " << st << " " << len << endl;
    cout << n << endl;
    for (int i = 0; i < st; i++) {
    	int a = rnd.next(1, N);
    	cout << a;
    	if (i < n - 1) cout << " ";
    }
    int c1 = rnd.next(1, N);
    int c2 = rnd.next(1, N);
    for (int i = st; i < min(st + len, n); i++) {
    	int a = rnd.next(1, 2);
    	if (a == 1) {
    		cout << c1;
    	} else {
    		cout << c2;
    	}
    	if (i < n - 1) cout << " ";
    }

    for (int i = st + len; i < n; i++) {
    	int a = rnd.next(1, N);
    	cout << a;
    	if (i < n - 1) cout << " ";
    }

    cout << endl;
    return 0;
}