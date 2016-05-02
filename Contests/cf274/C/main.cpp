#include <iostream>
#include <cstdio>
#include <vector>


using namespace std;
const int N = 5010;
int n, k, b, a;
int arr[N][N];

int abs(int a) {
	if (a < 0) return -a;
	return a;
}

int main() {

	cin >> n >> a >> b >> k;
	k++;
	for (int i = 0; i < k; i++)
		for (int j = 0; j < n; j++)
			arr[i][j] = 0;
	b--; a--;
	arr[0][a] = 1;
	for (int i = 0; i < k - 1; i++) 
		for (int j = 0; j < n; j++)	{
			int l =  max(0, j - abs(j - b) + 1), r = min(j + abs(j - b), n);
				arr[i + 1][x] = (arr[i + 1][x] + arr[i][j]) % 1000000007;	
			}		
		}
	int res = 0;
	for (int i = 0; i < n; i++) res = (res + arr[k - 1][i]) % 1000000007;
	cout << res << endl;

	      
	return 0;
}