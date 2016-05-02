#include <iostream>

using namespace std;

const int N = 10;
long long arr[N];

int main()
{
    long long n;
    cin >> n;
    arr[0] = 1;
    for(int i = 1; i < N; i++)
    {
        arr[i] = i * 10 * arr[i - 1];
    }
    for (int i = N - 1; i >= 0; i--) {
    	if (n / arr[i] != 0) {
    		cout << n / arr[i] << " " << i << endl;
    		n = n % arr[i];
    	}
    }
    return 0;
}
