#include <iostream>
#include <cstdio>
#include <cmath>

using namespace std;

float f1(float x, float y) {
	return sqrt(1 + x * x + y * y);
}

float f2(float x, float y) {
	return sin(x * y);
}


int main() {
	freopen("output22.txt", "w", stdout);
    float y1 = -2.0;
    float y2 = 3.0;
    float h = 0.1;
	for (float x = -1.0; x < 1.05; x += h) {
		cout << "x = " << x << "; y(1) = " << y1 << "; y(2) = " << y2 << endl;
//		cout << y2 << endl;
		float k1 = f1(x, y2);
		float k2 = f1(x + h/2, y2 + h * k1 / 2);
		float k3 = f1(x + h/2, y2 + h * k2 / 2);
		float k4 = f1(x + h, y2 + h * k3);
		float y11 = y1;
		y1 = y1 + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
		k1 = f2(y11, y2);
		k2 = f2(y11 + h/2, y2 + h * k1 / 2);
		k3 = f2(y11 + h/2, y2 + h * k2 / 2);
		k4 = f2(y11 + h, y2 + h * k3);
		y2 = y2 + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4);
	}


}