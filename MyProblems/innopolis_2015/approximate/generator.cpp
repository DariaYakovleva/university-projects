#include <cstdio>
#include <iostream>
#include <cstdlib>

using namespace std;

/*
<#assign maxN = 50/>
<#list 1..100 as testNumber>
    generator ${testNumber} ${maxN} > $
</#list>
*/

int a, b, n;

int main(int argc, char* argv[]) {
	freopen("approximate.in", "w", stdout);
	int a = atoi(argv[1]);
	int b = atoi(argv[2]);
	int n = atoi(argv[3]);
    cout << a << " " << b << " " << n << endl;
    return 0;
}