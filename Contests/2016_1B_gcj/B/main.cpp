#include <iostream>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>
#include <set>

using namespace std;
int n;
string c, jj;


string tostr(long long x) {
	string res = "";
	int cnt = 0;
	while (x > 0) {
		res += (char)(x % 10 + '0');
		x /= 10;
		cnt++;
	}
	while (cnt < n) {
		res += '0';
		cnt++;
	}
	reverse(res.begin(), res.end());
	return res;
}

long long toint(string a) {
	long long res = 0;
	for (char c: a) {
		res = res * 10 + (c - '0');
	}
	return res;
}

//-1 <
//1 >
pair<string, string> bt(string aa, string bb, int pos, int cmp) {
	string a = aa;
	string b = bb;

	pair<string, string> res;
	if (pos == n) {
		res.first = a;
		res.second = b;
//		cout << a << " " << b << endl;
		return res;		
	}
	if (a[pos] != '?' && b[pos] != '?') {
		if (cmp == 0) {
			if (a[pos] < b[pos]) return bt(a, b, pos + 1, -1);
			if (a[pos] > b[pos]) return bt(a, b, pos + 1, 1);
		}
		return bt(a, b, pos + 1, cmp);
	}            
	if (a[pos] == '?' && b[pos] == '?') {
		if (cmp == 0) {
			a[pos] = '1';
		    b[pos] = '0' + (a[pos] - '0' - 1 + 10) % 10;
			pair<string, string> tmp = bt(a, b, pos + 1, 1); 	
			res = tmp;
			b[pos] = a[pos];
			tmp = bt(a, b, pos + 1, 0); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			b[pos] = '0' + (a[pos] - '0' + 1) % 10;
			tmp = bt(a, b, pos + 1, -1); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			return res;
		}
		if (cmp < 0) {
			a[pos] = '9';
			b[pos] = '0';			
		}
		if (cmp > 0) {
			a[pos] = '0';
			b[pos] = '9';						
		}
		return bt(a, b, pos + 1, cmp);
	} 
	if (a[pos] != '?' && b[pos] == '?') {
		if (cmp == 0) {
		    b[pos] = '0' + (a[pos] - '0' - 1 + 10) % 10;
			pair<string, string> tmp = bt(a, b, pos + 1, 1); 	
			res = tmp;
			b[pos] = a[pos];
			tmp = bt(a, b, pos + 1, 0); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			b[pos] = '0' + (a[pos] - '0' + 1) % 10;
			tmp = bt(a, b, pos + 1, -1); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			return res;
		}
		if (cmp < 0) {
			b[pos] = '0';			
		}
		if (cmp > 0) {
			b[pos] = '9';						
		}
		return bt(a, b, pos + 1, cmp);
	} 
	if (a[pos] == '?' && b[pos] != '?') {
		if (cmp == 0) {
		    a[pos] = '0' + (b[pos] - '0' - 1 + 10) % 10;
			pair<string, string> tmp = bt(a, b, pos + 1, -1); 	
			res = tmp;
			a[pos] = b[pos];
			tmp = bt(a, b, pos + 1, 0); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			a[pos] = '0' + (b[pos] - '0' + 1) % 10;
			tmp = bt(a, b, pos + 1, 1); 	
			if (abs(toint(tmp.first) - toint(tmp.second)) < abs(toint(res.first) - toint(res.second))) res = tmp;
			return res;

		}
		if (cmp < 0) {
			a[pos] = '9';			
		}
		if (cmp > 0) {
			a[pos] = '0';						
		}
		return bt(a, b, pos + 1, cmp);
	} 	
	return res;
}

int main() {

	int t;
	cin >> t;
	for (int tt = 0; tt < t; tt++) {
		cin >> c >> jj;
		n = (int)c.size();
        pair<string, string> res = bt(c, jj, 0, 0);               		
        string a = res.first;
        string b = res.second;
		cout << "Case #" << tt + 1 << ": " << a << " " << b << endl;			
	}

	return 0;
}
