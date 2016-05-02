#include "testlib.h"

using namespace std;
int main() {
    registerValidation();

    int n = inf.readInt(1, 100);
    inf.readEoln();
    for (int i = 0; i < n; i++) {
        string s = inf.readString("[a-zA-Z][a-z]*");
        ensure(s.size() <= 30);
    }
    inf.readEof();

    return 0;
}