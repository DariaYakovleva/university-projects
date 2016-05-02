#include <cstddef>
#include "tests.h"
#include <iostream>
#include <boost/numeric/interval.hpp>
#include <gmpxx.h>
typedef boost::numeric::interval<double> IN;

using namespace std;

struct point {
    double x;
    double y;
};

struct interval_point {
    IN x;
    IN y;
};
struct long_point {
    mpq_class x;
    mpq_class y;
};
interval_point to_interval(point a) {
    interval_point ia;
    IN x = a.x;
    IN y = a.y;
    ia = {x, y};
    return ia;
}

long_point to_long(point a) {
    long_point la;
    mpq_class x = mpq_class(a.x);
    mpq_class y = mpq_class(a.y);
    la = {x, y};
    return la;
}

int left_turn(point a, point b, point c) {
    double l = (b.x - a.x) * (c.y - a.y);
    double r = (c.x - a.x) * (b.y - a.y);
//    double eps = std::numeric_limits<double>::epsilon() * 8;
    double eps = (fabs(l) + fabs(r)) * std::numeric_limits<double>::epsilon() * 8;
    double s = l - r;
    if (s < -eps)
        return -1;
    if (s > eps)
        return 1;
    interval_point ia = to_interval(a);
    interval_point ib = to_interval(b);
    interval_point ic = to_interval(c);
    IN s2 = (ib.x - ia.x) * (ic.y - ia.y) - (ic.x - ia.x) * (ib.y - ia.y);
//    using namespace boost::numeric::interval_lib::compare::certain;
    if (!boost::numeric::zero_in(s2)) {
        if (s2 > 0.) {
            return 1;
        }
        return -1;
    }
    long_point la = to_long(a);
    long_point lb = to_long(b);
    long_point lc = to_long(c);
    mpq_class ldet = (lb.x - la.x) * (lc.y - la.y) - (lc.x - la.x) * (lb.y - la.y);
    if (cmp(ldet, 0.) > 0)
        return 1;
    if (cmp(ldet, 0.) < 0)
        return -1;
    return 0;
}

bool intersect(point a, point b, point c, point d) {
    return (max(min(a.x, b.x), min(c.x, d.x)) <= min(max(a.x, b.x), max(c.x, d.x))) &&
           (max(min(a.y, b.y), min(c.y, d.y)) <= min(max(a.y, b.y), max(c.y, d.y)));
}

bool intersection(point a, point b, point c, point d) {
    return intersect(a, b, c, d) && (left_turn(a, b, c) * left_turn(a, b, d) <= 0) && (left_turn(c, d, a) * left_turn(c, d, b) <= 0);
}

int main() {
//    point a = {3.0, 5.0};
//    point b = {-3.0, -5.0};
//    point c =  {3.0, 5.0 + std::numeric_limits<double>::epsilon()};
//    cout.precision(100);
//    cout << 1 + (std::numeric_limits<double>::epsilon()) * .5 << endl;
//    cout << 1 + (std::numeric_limits<double>::epsilon()) << endl;
//    int res = left_turn(a, b, c); // ожидается collinear
//    cout << res << endl;
    int id;
    cin >> id;
    vector<double> points = genTest(id);
    for (size_t i = 0; i < points.size(); i += 8) {
        point a = {points[i], points[i + 1]};
        point b = {points[i + 2], points[i + 3]};
        point c = {points[i + 4], points[i + 5]};
        point d = {points[i + 6], points[i + 7]};
        if (intersection(a, b, c, d)) {
            cout << "Y";
        } else {
            cout << "N";
        }
    }
    cout << endl;
    return 0;
}