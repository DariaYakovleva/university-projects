#include "testlib.h"
using namespace std;

int a;
int b;
int n;

int const MAX_A[] = {10000000, 1000, 1000000, 10000000}; 
int const MAX_B[] = {10000000, 1000, 1000000, 10000000}; 
int const MAX_N[] = {10000000, 1000, 1000000, 10000000}; 
int const N = 1000000000;


int main(int argc, char **argv)
{
	registerValidation(argc, argv);
    int group = atoi(validator.group().c_str());
	a = inf.readInt(0, N, "a");
	inf.readSpace();
	b = inf.readInt(1, N, "b");    	 
	inf.readSpace();
	n = inf.readInt(1, MAX_N[group], "n");    	 
//    ensuref(n + m > 0, "n + m <= 0");
    inf.readEoln();
    inf.readEof();
    return 0;
}