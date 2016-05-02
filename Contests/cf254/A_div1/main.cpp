#include <iostream>      
#include <cstdio>
#include <algorithm> 
using namespace std;

const int N = 510;
int vertex[N];
int n, m;
double maxi = 0.0;

int main() {
	
    std::cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> vertex[i];
    }
    for (int i = 0; i < m; i++) {
    	int a, b, c;
    	std::cin >> a >> b >> c;
    	maxi = max(maxi, (double)(vertex[a - 1] + vertex[b - 1]) / c);
    }
    printf("%.11lf", maxi);

}