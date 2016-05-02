#include "testlib.h"
using namespace std;

const int MAX = 1000000000;
int n;

int const MAX_N[] = {1000000, 100, 1000, 1000000}; 

int main(int argc, char **argv)
{
	registerValidation(argc, argv);
    int group = atoi(validator.group().c_str());
    n = inf.readInt(1, MAX_N[group], "n");
    inf.readEoln();
    for (int i = 0; i < n; i++) {
    	int a = inf.readInt(1, MAX, ("p[" + vtos(i) + "]").c_str());	
    	if (i < n - 1) inf.readSpace();
    }
    inf.readEoln();
    inf.readEof();
    return 0;
}