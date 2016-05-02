#include <cstddef>
#include "tests.h"
#include <iostream>
//#include <boost/multiprecision/cpp_int.hpp>
#include <gmpxx.h>

using namespace std;

int main()
{
    int id;
    cin >> id;
    pair<string, string> ab = genTest(id);
//    boost::multiprecision::cpp_int first;
//    first.assign(ab.first);
//    boost::multiprecision::cpp_int second;
//    second.assign(ab.second);
    mpz_class a, b;
    a = mpz_class(ab.first);
    b = mpz_class(ab.second);
    a += b;
    cout <<  a.get_str(10) << endl;
//    cout << first + second << endl;

    return 0;
}