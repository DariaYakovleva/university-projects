#include "testlib.h"
#include <cstdio>
#include <iostream>
#include <cstdlib>
#include <time.h>
#include <set>


using namespace std;

const int MOD = 1000000007;
const int N = 10000;
/*
<#assign maxN = 50/>
<#list 1..100 as testNumber>
    generator ${testNumber} ${maxN} > $
</#list>
*/

int n, m;

int main(int argc, char* argv[]) {
	registerGen(argc, argv);
	int max_n = atoi(argv[2]);
	int max_m = atoi(argv[3]);
    n = rnd.next(0, max_n);
    m = rnd.next(0, max_m);
    cout << n << " " << m << endl;
    return 0;
}