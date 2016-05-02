#include "testlib.h"
using namespace std;

const int MOD = 1000000007;
const int M = 10000;
int n;
int m;

int const MAX_NM[] = {100, 100, 100, 1000, 10000}; 


string intToStr(int a) {
	string res = "";
	while (a > 0) {
		res += (char)(a % 10 + '0');
		a /= 10;
	}
	reverse(res.begin(), res.end());
	return res;
}

int main(int argc, char **argv)
{
	registerValidation(argc, argv);
    int group = atoi(validator.group().c_str());
    if (group == 1) {
	    n = inf.readInt(0, 100, "n");
   	 	inf.readSpace();
   		m = inf.readInt(0, 2, "m");    	 
    } else {
    	n = inf.readInt(0, M, "n");
    	inf.readSpace();
    	m = inf.readInt(0, M, "m");
    	ensuref(n + m <= MAX_NM[group], ("n + m > " + intToStr(MAX_NM[group])).c_str());
    }
    ensuref(n + m > 0, ("n + m <= 0");
    inf.readEoln();
    inf.readEof();
    return 0;
}