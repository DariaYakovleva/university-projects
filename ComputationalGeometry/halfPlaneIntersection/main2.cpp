#include <bits/stdc++.h>

using namespace std;


const int N = 1000010;


struct point {
    double x;
    double y;
};


struct plane {
    long long a;
    long long b;
    long long c;
    long long num;
    plane(){};
    plane(long long x, long long y, long long z, long long nn) {
        a = x;
        b = y;
        c = z;
        num = nn;
    }
    long long vect(const plane& y) {
        return a * y.b - y.a * b;
    }
    long long scalar(const plane& y) {
        return a * y.a + y.b * b;
    }

//    bool operator<(const plane& y) const {
//        return atan2((double)b, (double)a) < atan2((double)y.b, (double)y.a);
//    }

    bool operator<(const plane& y) const {
        return atan2((double)-a, (double)b) < atan2((double)-y.a, (double)y.b);
    }


    bool operator==(const plane& y) const {
        return plane(a, b, c, num).vect(y) == 0 && plane(a, b, c, num).scalar(y) > 0;
    }

};

plane arr2[N];
vector<plane> arr;
int n;
//const double none = 1e-10 + 5;


point interPoint(plane x, plane y) {
    long long d = x.a * y.b - y.a * x.b;
//    if (d == 0) return point{none, none};
    double px = -(x.c * y.b - y.c * x.b) / (double)d;
    double py = -(x.a * y.c - y.a * x.c) / (double)d;
    return point{px, py};
}

long long rotation(plane x, plane y, plane z) {
    long long res = (long long)x.a * y.b * z.c + (long long)x.c * y.a * z.b + (long long)x.b * y.c * z.a -
                    (long long)x.c * y.b * z.a - (long long)x.b * y.a * z.c - (long long)x.a * y.c * z.b;
//    long double a = (long double)x.a * y.b * z.c + (long double)x.c * y.a * z.b + (long double)x.b * y.c * z.a;
//    long double b = (long double)x.c * y.b * z.a + (long double)x.b * y.a * z.c + (long double)x.a * y.c * z.b;
//    assert(fabs(res - (a - b)) < 0.00001);
    return res;
}

bool compare(plane x, plane y) {
	return (x < y) || (x == y && x.c < y.c);
}


plane lesss(plane x, plane y) {
	if (x < y) return x;
	if (((y.b != 0) && (x.c * y.b == y.c * x.b)) || (y.b == 0 && (x.c * y.a == x.a * y.c))) {
//		cout << x.a << " " << x.b << " " << x.c << "|" << y.a << " " << y.b <<  " " << y.c << endl;
		if (x.num < y.num) return x;
		return y;
	}
	double x0;
	double y0;
	if (x.b != 0) {
		x0 = 0.0;
		y0 = -x.c / (double)x.b;
	} else {
		x0 = -x.c / (double)x.a;
		y0 = 0.0;
	}
	double res = y.a * x0 + y.b * y0 + y.c;
	if (res > 0) return x;
	return y;	
}

vector<plane> result1;
vector<plane> result;

bool isZero(plane x, plane y) {
    if (!(x.vect(y) == 0 && x.scalar(y) < 0)) return false;
    point yy;
    if (y.a == 0) {
        yy.x = 0.0;
        yy.y = -y.c / (double)y.b;
    } else {
        yy.x = -y.c / (double)y.a;
        yy.y = 0.0;
    }
    return (x.a * yy.x + x.b * yy.y + x.c) <= 0;
}

bool isPP(plane x, plane y) {
//	cout << x.a << " " << x.b << " " << x.c << " " << y.a <<" "<< y.b <<" " << y.c << endl;
    if (x.vect(y) != 0) return false;
    point yy;
    if (y.a == 0) {
        yy.x = 0.0;
        yy.y = -y.c / (double)y.b;
    } else {
        yy.x = -y.c / (double)y.a;
        yy.y = 0.0;
    }
    return (x.a * yy.x + x.b * yy.y + x.c) > 0;
}

vector<point> edges;

double square() {
	edges.clear();
    int m = result.size();
    if (m == 0) return 0.0;
    if (m == 1) return -1.0;
    for (int i = 0; i < m; i++) {
        if (!isZero(result[i], result[(i + 1) % m])) {
            point p = interPoint(result[i], result[(i + 1) % m]);
            edges.push_back(p);
        } else {
            return 0.0;
        }
    }
//    for (int i = 0; i < result.size() - 2; i++) {
//        if (rotation(result[i], result[i + 1], result[i + 2]) <= 0) return 0.0;
//    }
    double res = 0.0;
    for (int i = 0; i < edges.size(); i++) {
        point p1 = edges[i];
        point p2 = edges[(i + 1) % edges.size()];
        res += (p1.x - p2.x) * (p1.y + p2.y);
    }
    return fabs(res / 2);
}


bool used[N];
int intersection() {
    sort(arr2, arr2 + n, compare);
    used[0] = false;
    plane cur = arr2[0];
    for (int i = 1; i <= n; i++) {
    	if (i == n) {
    		if (arr.empty()) return -1;
    		arr2[n] = arr[0];
    	}
        if (arr2[i] == arr2[i - 1]) {
            cur = lesss(cur, arr2[i]);
        } else {
        	arr.push_back(cur);
        	cur = arr2[i];
        }
        used[i] = false;
    }
    arr[0] = cur;

//    for (int i = 0; i < arr.size(); i++) {
//    	cout << arr[i].num << " "; //" {" << arr[i].a << "," << arr[i].b << "," << arr[i].c << "} ";
//    }
//    cout << endl;
    
    n = arr.size();
    if (n <= 1) return -1;
    for (int i = 0; i < n; i++) {
        if (arr[i].vect(arr[(i + 1) % n]) < 0) return -1;
        plane b = arr[(i + 1) % n];
        if ((arr[i].vect(b) == 0) && (arr[i].scalar(b) < 0)) {
            if (isZero(arr[i], b)) return 0;
//            else return -1;
        }
    }
    for (int i = 0; i < n; i++) {
        arr.push_back(plane(arr[i].a, arr[i].b, arr[i].c, arr[i].num));
    }
    n = arr.size();

    
    result1.clear();
    result1.push_back(arr[0]);
    result1.push_back(arr[1]);

    for (int i = 2; i < n; i++) {
        while (result1.size() >= 2 && rotation(arr[i], result1[result1.size() - 2], result1[result1.size() - 1]) <= 0) {
            result1.pop_back();
        }
        result1.push_back(arr[i]);
    }
    int st = -1;
    for (int i = 0; i < result1.size(); i++) {
        if (used[result1[i].num]) {
            st = result1[i].num;
            break;
        }
        used[result1[i].num] = true;
    }
//    cout << result1.size() << " " << st << endl;
    for (int i = 0; i < result1.size(); i++) {
	    plane b = result1[(i + 1) % result1.size()];
        if (isPP(result1[i], b)) return -1;
        if (isZero(result1[i], b)) return 0;
    }

	if (st == -1) return 0;

    for (int i = 0; i < (int)result1.size(); i++) {
        if (st == result1[i].num) {
            result.push_back(result1[i]);
            i = (i + 1) % (int)result1.size();
            while (st != result1[i].num) {
                result.push_back(result1[i]);
                i = (i + 1) % (int)result1.size();
            }
            break;
        }
    }    
    return 1;

}

long long gcd(long long a, long long b) {
    while (b != 0) {
        long long c = b;
        b = a % b;
        a = c;
    }
    return a;
}


int main() {
//    freopen("input.in", "r", stdin);
//    freopen("output.out", "w", stdout);
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
//        cin >> arr2[i].a >> arr2[i].b >> arr2[i].c;
        scanf("%lld %lld %lld", &arr2[i].a, &arr2[i].b, &arr2[i].c); 
        arr2[i].num = i;
        long long g = gcd(abs(arr2[i].a), gcd(abs(arr2[i].b), abs(arr2[i].c)));
        arr2[i].a /= g;
        arr2[i].b /= g;
        arr2[i].c /= g;
    }
    int res = intersection();
    if (res < 1) {
        cout << res << endl;
    } else {
        cout.precision(10);
        double sq = square();
        if (sq < 0) {
            cout << -1 << endl;
        } else {
            cout << sq << endl;
        }
    }
    return 0;
}