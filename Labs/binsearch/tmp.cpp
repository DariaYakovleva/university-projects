#include <iostream>

using namespace std;

int main()
{
    int k, n, left, right, a, m, x = 0;
    cin >> n;
    cin >> k;
    int MassN[n];
    for(int i = 0; i < n; i++) {
        cin >> a;
        MassN[i] = a;
    }
    int MassK[k];

    for(int i = 0; i < k; i++) {
        cin >> a;
        MassK[i] = a;
    }
    for(int i = 0; i < k; i++)
    {
        x = MassK[i];
        left = -1;
        right = n;
        while(right - left > 1)
        {
            m = (left + right)/2;
            if (x > MassN[m])
                left = m;
            else
                right = m;
        }
        if(right != n && MassN[right] == x)
            cout << "YES\n";
        else
            cout << "NO\n";
    }
}
