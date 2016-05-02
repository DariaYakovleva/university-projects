#include <bits/stdc++.h>

using namespace std;
const int N = 11;

int n;
int nn;
double alpha = 0.7;
double b = 100000.0;
vector<double> p[N * N];
vector<double> q[N * N];
double res[N * N];

vector<double> crossover(const vector<double>& p, const vector<double>& d) {
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

vector<double> add(const vector<double>& a, const vector<double>& b) {
	vector<double> res;
	for (int i = 0; i < n; i++) {
		res.push_back(a[i] + b[i]);
	}
	return res;
}

vector<double> sub(const vector<double>& a, const vector<double>& b) {
	vector<double> res;
	for (int i = 0; i < n; i++) {
		res.push_back(a[i] - b[i]);
	}
	return res;
}

vector<double> mul(const vector<double>& a, double x) {
	vector<double> res;
	for (int i = 0; i < n; i++) {
		res.push_back(a[i] * x);
	}
	return res;
}


int my_rand(int a, int b, int c) {
	int res = rand() % nn;
	while (res == a || res == b || res == c) {
		res = rand() % nn;
	}
	return res;
}

double get_value(const vector<double>& v) {
	for (int i = 0; i < n; i++) {
		cout << v[i] << " ";
	}
	cout << endl;
	double cur_res;
	string s;
	getline(cin, s);
//	cout << "!" << s << endl;
	if (s[0] == 'B') {
		exit(0);
	} else {
		cur_res = stod(s);
//		cout << "d" << cur_res << endl;
	}
	return cur_res;
}

void init() {
	for (int i = 0; i < nn; i++) {
		for (int j = 0; j < n; j++) {
			double x = ((double) rand() / (double)RAND_MAX) * 20 - 10.0;
			p[i].push_back(x);
		}
		res[i] = get_value(p[i]);
		b = min(b, res[i]);
	}
}


int main() {
	srand(time(0));
	cin >> n;
	nn = 8 * n;
	string s;
	getline(cin, s);
	cout.precision(11);
	init();
	while (true) {
		for (int i = 0; i < nn; i++) q[i].clear();
		for (int i = 0; i < nn; i++) {
//			cout << i << endl;
			int a = my_rand(i, i, i);
			int b = my_rand(i, i, a);
			int c = my_rand(i, a, b);
//			cout << a << b << c << endl;
			vector<double> d = add(p[a], mul(sub(p[b], p[c]), alpha));
			vector<double> e = crossover(p[i], d);
			for (int i = 0; i < n; i++) e[i] = max(-10.0, min(10.0, e[i]));
			double cur_res = get_value(e);
			if (cur_res < res[i]) {
				res[i] = cur_res;
				if (cur_res < b) b = res[i];
				q[i] = e;
			} else {
				q[i] = p[i];
			}
		}
		for (int i = 0; i < nn; i++) p[i] = q[i];	
	}

		
}