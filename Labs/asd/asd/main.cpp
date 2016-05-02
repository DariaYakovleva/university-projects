#include <cmath>
#include <iostream>

using namespace std;




void trans()
{
    
    double a[2][2][2][2], t[2][2], q[2][2];

    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
            for (int k = 0; k < 2; k++)
                for (int h = 0; h < 2; h++)
                    cin >> a[k][h][i][j];
    t[0][0] = -1;
    t[0][1] = 1;
    t[1][0] = 2;
    t[1][1] = 1;
    q[0][0] = -1.0/3;
    q[0][1] = 1.0/3;
    q[1][0] = 2.0/3;
    q[1][1] = 1.0/3;
    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
            for (int k = 0; k < 2; k++)
                for (int h = 0; h < 2; h++)
                    if ((i == 1) && (j == 0) && (k == 0) && (h == 1))
            {
                double res = 0;
                for (int s = 0; s < 2; s++)
                    for (int t1 = 0; t1 < 2; t1++)
                        for (int l = 0; l < 2; l++)
                            for (int m = 0; m < 2; m++)
                            {
                            res += a[s][t1][l][m] * q[i][s] * q[j][t1] * t[l][k] * t[m][h];
                                cout << s + 1 << " " <<  t1 + 1 << " " <<  l + 1 << " " << m + 1 << " : " <<
                                 q[i][s] << " " <<  q[j][t1] << " " << t[l][k]<<  " "<< a[s][t1][l][m] <<
                                " " << a[s][t1][l][m] * q[i][s] * q[j][t1] * t[l][k] * t[m][h] <<endl;
                            }
                cout << res << " ";
            }

}

void sver()
{
    double a[2][2][2][2];
    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
            for (int k = 0; k < 2; k++)
                for (int h = 0; h < 2; h++)
                    cin >> a[k][h][i][j];
    int res = 0;
    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
        {

            res += a[i][j][j][i];
            
            
        }
    cout << res << " ";
}

void tr()
{
    const int N = 3;
    double a[N][N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                    cin >> a[j][k][i];
    for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                cout << a[k][j][i] << " ";
    
}

void sym()
{
    const int N = 3;
    double a[N][N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                cin >> a[j][k][i];
    for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                cout << 1.0/2*(a[i][j][k] - a[i][k][j]) << " ";
}

void asym()
{
    const int N = 3;
    double a[N][N][N];
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            for (int k = 0; k < N; k++)
                cin >> a[j][k][i];
    for (int k = 0; k < N; k++)
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                cout << 1.0/6*(a[k][i][j] + a[j][k][i] + a[i][j][k]- a[k][j][i]- a[j][i][k]- a[i][k][j]) << " ";
}

int main() {
    
    sym();

}
