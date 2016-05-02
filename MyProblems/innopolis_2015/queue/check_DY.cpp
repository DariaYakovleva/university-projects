#include "testlib.h"
#include <string>
#include <vector>
#include <cstdio>

const long long MOD = 1000000007;


int main(int argc, char* argv[])
{
    registerTestlibCmd(argc, argv);    
    long long y1 = ans.readLong();
    long long y2 = ouf.readLong();
    if (y2 >= MOD || y2 < 0) {
		quitf(_pe, "incorrect answer %s", vtos(y2).c_str());	
	}
    if (y1 != y2) {
    	quitf(_wa, "expected %s, found %s", vtos(y1).c_str(), vtos(y2).c_str());		
    }
    quitf(_ok, "answer is correct");
}