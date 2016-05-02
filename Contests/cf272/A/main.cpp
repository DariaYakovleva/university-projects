#include <iostream>
#include <cstdio>
#define ll long long

using namespace std;
const ll N = 1000000007;
ll a, b;

int main() {
	cin >> a >> b;
	cout << (b * a * (a + 1) / 2 + a) << endl;
	cout << (((b * (b - 1) / 2) % N) * ((b * a * (a + 1) / 2 + a) % N)) % N << endl; //??????
	cout << (((b * (b - 1) / 2) % N) * (a * (b * (a + 1) + 2) / 2) % N) % N;                 //????



}