#include "testlib.h"

using namespace std;
int main() {
    registerValidation();

    int n = inf.readInt(1, 100);
    inf.readSpace();
    int k = inf.readInt(n, 2 * n - 1);
    inf.readEoln();
    int x[2] = {0, 0};
    for (int i = 0; i < k; i++) {
        int u = inf.readInt(1, 2);
        x[u - 1]++;
        ensure(i == k - 1 || max(x[0], x[1]) < n);
        if (i < k - 1) {
            inf.readSpace();
        }
    }
    ensure(max(x[0], x[1]) == n);
    inf.readEoln();
    inf.readEof();

    return 0;
}