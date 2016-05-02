#include <cstdio>
#include <cmath>

int sign(long long a) {
	return (a > 0) - (a < 0);
}

struct Ratio {
	long long num, den;
	
	Ratio() {}
	
	Ratio(long long a, long long b) :
		num(a), den(b) {}
	
	double operator!() {
		return (double) num / den;
	}
};

Ratio operator-(Ratio const& a, Ratio const& b) {
	return Ratio(a.num * b.den - b.num * a.den, a.den * b.den);
}

double const EPS = 1e-19;

bool better(Ratio const& now, Ratio const& best, Ratio const& need) {
	return fabs(!(now - need)) + EPS < fabs(!(best - need));
}

int main() {
	freopen("approximate.in", "r", stdin);
	freopen("approximate2.out", "w", stdout);
	int a, b, n;
	scanf("%d%d%d", &a, &b, &n);
	int ans = 1;
	Ratio const need(a, b);
	Ratio best(2 * a > b ? 1 : 0, 1);
	if (a * 2 == b) {
		++ans;
	}
	for (int y = 2; y <= n; ++y) {
		int x = (long long) y * a / b;
		Ratio now1 = Ratio(x, y);
		Ratio now2 = Ratio(x + 1, y);
		bool ok1 = better(now1, best, need);
		bool ok2 = better(now2, best, need);
		if (!ok1 || !ok2) {
			ans += ok1 + ok2;
			if (ok1) best = now1;
			if (ok2) best = now2;
		} else {
			if (better(now1, now2, need)) {
				++ans;
				best = now1;
			} else if (better(now2, now1, need)) {
				++ans;
				best = now2;
			} else {
				ans += 2;
				best = now1;
			}
		}
	}
	printf("%d\n", ans);
}