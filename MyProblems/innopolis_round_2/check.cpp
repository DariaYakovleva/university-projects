#include "testlib.h"
#include <string>
#include <vector>
#include <cstdio>

const int N = 500010;
int n;
int left[N];
int right[N];
int cost[N];


int main(int argc, char * argv[])
{
    registerTestlibCmd(argc, argv);
    
    n = inf.readInt();
//    s = inf.readInt();
//    for (int i = 0; i < n; i++) {
//    	left[i] = inf.readInt();
//    	right[i] = inf.readInt();
//    	cost[i] = inf.readInt();	
//    }
    for (int i = 0; i < n; i++) {
    	long long y1 = ans.readLong();
    	long long y2 = ouf.readLong();
    	if (y1 != y2) {
    		quitf(_wa, "line %d: expected %s, found %s", i, vtos(y1).c_str(), vtos(y2).c_str());		
    	}
    	if (y1 != -1) {
			std::string s1;
			std::string s2;
			s1 = ans.readToken();
			s2 = ouf.readToken();
			if (s1 != s2) {
				if (s2 != "YES" && s2 != "NO") {
					quitf(_pe, "line %d: incorrect answer %s", i, s2.c_str());	
				}
				quitf(_wa, "line %d: expected %s, found %s", i, s1.c_str(), s2.c_str());		
			}
    	}
    } 
    quitf(_ok, "answer is correct");
}