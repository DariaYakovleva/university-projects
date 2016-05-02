#include <bits/stdc++.h>

using namespace std;

int n;
const double EPS = 1e-7;

vector<double> random_result() {
	vector<double> res;
	for (int i = 0; i < n; i++) {
		res.push_back((double) rand() / (double)RAND_MAX);
	}
	return res;
}


double f(const vector<double>& xx) {
	for (double x: xx) {
		cout << x << " ";
	}
	cout << endl;
	double res;
	cin >> res;
	return res;
}

vector<double> local_search() {
	vector<double> x = random_result();
	vector<double> px = x;
	for (size_t i = 0; i < px.size(); i++) px[i] -= EPS;
//	double alpha = (double) rand() / (double)RAND_MAX;
	for (int i = 0; i < 100; i++) {
		vector<double y;
		double ans = f(x);
		double pans = f(px);
		for (size_t i = 0; i < pxx.size(); i++) {
			y.push_back(x[i] - ans * (x[i] - px[i]) / (ans - pans)); 
		}
		if (f(y) < f(x)) {
			px =  x;
			x = y;
		} else {
			return x;
		}
	}
	return x;
}

vector<double> new_base(const vector<double>& p, const vector<double>& d) {
	vector<double> res;
	for (int i = 0; i < n; i++) {
		double prob = (double) rand() / (double)RAND_MAX;
//		cout << "P" << prob << endl;
		if (prob <= ((double)1 / (double)n)) {
			res.push_back(d[i]);
		} else {
			res.push_back(p[i]);
		}
	}
	return res;
} 


vector<double> solve() {
	vector<double> x = random_result(); // random solution
	vector<double> b = x; //the best solution
	vector<double> h = x; //the base for next round
	for (int i = 0; i < 1000 * n; i++) {
		vector<double> y = local_search();
		if (f(y) < f(b)) {
			b = y;		
		}
		h = new_base(h, y);
		x = large_mutation(h);
	}
	return b;
}

//1000n
int main() {
	srand(time(NULL));
	cin >> n;

		
}