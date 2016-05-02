#include "testlib.h"

using namespace std;
int main() {
    registerValidation();

    int n = inf.readInt(2, 100000);
    inf.readSpace();
    inf.readInt(1, 1000000000);
    inf.readEoln();
    for (int i = 0; i < n; i++) {
        inf.readInt(1, 1000000000);
        if (i < n - 1) {
            inf.readSpace();
        }
    }
    inf.readEoln();
    inf.readEof();

    return 0;
}