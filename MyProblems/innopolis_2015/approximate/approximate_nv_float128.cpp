#include <fstream>
#include <math.h>

__float128 const EPS = 1e-10;

using namespace std;

int main() {
    ifstream cin("approximate.in");
    ofstream cout("approximate.out");
    long long a, b, n;
    cin >> a >> b >> n;
    int ans = 0;
    long long x = -1, y = 1;
    __float128 bf, fr = a * 1.0 / b;
    
    for (long long i = 1; i <= n; i++) {
        bool f = false;
        long long rr = (i * a) / b;
        for (long long r = rr; r <= rr + 1; r++) {
            bf = x * 1.0 / y;
            __float128 cf = r * 1.0 / i;
            if (fabs(fr - bf) > fabs(cf - fr)) {
                x = r, y = i;
                f = true;
            }    
        }
        if (f) {
            bf = x * 1.0 / y;
            for (long long r  = rr; r <= rr + 1; r++) {
                __float128 cf = r * 1.0 / i;
                if (fabs(fabs(fr - bf) - fabs(cf - fr)) < EPS) {
                    x = r, y = i;
                    ans++;
                }    
            }
        }
    }
    cout << ans;
}