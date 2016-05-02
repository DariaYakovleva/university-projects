#include "testlib.h"

using namespace std;
int main() {
    registerValidation();

    int n = inf.readInt(1, 200);
    inf.readSpace();
    int k = inf.readInt(1, n);
    inf.readSpace();
    int a = inf.readInt(1, n);
    inf.readSpace();
    int b = inf.readInt(1, n);
    inf.readEoln();
    ensure(a * k <= n && n <= b * k);
    int prev = -1;
    for (int i = 0; i < n; i++) {
        prev = inf.readInt(prev + 1, 1000000000);
        if (i < n - 1) {
            inf.readSpace();
        }
    }
    inf.readEoln();
    inf.readEof();

    return 0;
}