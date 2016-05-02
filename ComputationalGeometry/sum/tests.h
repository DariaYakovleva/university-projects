//
// Created by Daria on 16.09.2015.
//

#ifndef SUM_TESTS_H
#define SUM_TESTS_H

#include <utility>
#include <string>
#include <cassert>

using namespace std;

pair<string, string> genTest(int id) {
    const int N = 39;
    const string a[N] = {"211111111111111111111111111111111111111"};
    const string b[N] = {"311111111111111111111111111111111111111"};
    assert(0 <= id && id < N);
    return make_pair(a[id], b[id]);
}
#endif //SUM_TESTS_H
