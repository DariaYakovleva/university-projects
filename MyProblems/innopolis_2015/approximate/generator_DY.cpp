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

int a, b, n;

int main(int argc, char* argv[]) {
	registerGen(argc, argv);
	int max_n = atoi(argv[2]);
    a = rnd.next(0, N);
    b = rnd.next(1, min(N, a));
    n = rnd.next(max_n / 10, max_n);
    cout << a << " " << b << " " << n << endl;
    return 0;
}