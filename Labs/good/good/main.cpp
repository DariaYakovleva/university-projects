
#define ll long long
#define lod long double
#include <iostream>
#include<stdio.h>
#include<string.h>
#include<string>
#include<math.h>
#include<vector>
#include<algorithm>
#include<cstdlib>
#include<queue>
#include<cmath>
#include<set>
#include<ctime>

using namespace std;

const int N = 220, INF = (int)1e11;
const lod EPS = 1e-9;


int n;
lod P[N][N];



int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
        for (int j = 0; j <= n; j++)
        {
            cin >> P[i][j];
        }
    for (int i = 0; i < n; i++)
    {
        lod mul;
        if ((fabs(P[i][i]) > EPS) && ((P[i][i] > 1 + EPS) || (P[i][i] < 1 - EPS)))
        {
            mul = P[i][i];
            for (int j = 0; j <= n; j++)
            {
                P[i][j] = P[i][j] / mul;
            }
        }
        for (int r = 0; r < n; r++)
            if (i != r)
            {
                mul = P[r][i];
                for (int j = 0; j <= n; j++)
                {
                    P[r][j] -= P[i][j] * mul;
                }
            }
    }
    
    
    for (int j = 0; j < n; j++)
        printf("%.6Lf\n", P[j][n]);

    
    return 0;
}