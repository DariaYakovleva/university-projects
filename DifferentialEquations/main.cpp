#include <iostream>
#include <cstdio>
#include <cmath>
#include <string.h>
       	
using namespace std;

int main() {
	freopen("output2.txt", "w", stdout);
    float y1 = 0.7;
    float y2 = -0.5;
    float h = 0.1;
	for (float x = 0; x < 4.05; x += h) {
		cout << "x = " << x << "; y(1) = " << y1 << "; y(2) = " << y2 << endl;
//		cout << y2 << endl;
		y1 = y1 + h * cos(x + y1);
		y2 = y2 + h * sin(x - y2);		                            	
	}


}