#include <bits/stdc++.h>

using namespace std;

string start = "s";
string accept = "ac";
string reject = "rj";
string blank = "_";
string top = "^";
string lleft = "<";
string rright = ">";
vector<string> states;
vector<pair<string, string>> steps;

void print_states() {
	cout << "start: " << start << endl;
	cout << "accept: " << accept << endl;
	cout << "reject: " << reject << endl;
	cout << "blank: " << blank << endl;
}

void print_step(int pos) {
	cout << steps[pos].first << " -> " << steps[pos].second << endl;	
}

void add_step(string from, string st, string to, string nst, string go) {
	steps.push_back(make_pair(from + " " + st, to + " " + nst + " " + go));
}

void print_steps() {
	for (size_t i = 0; i < steps.size(); i++) {
		print_step(i);
	}
}

int main() {
	freopen("zero.out", "w", stdout);
	print_states();
	add_step(start, blank, accept, blank, top);
	add_step(start, "0", "n", blank, rright);
	add_step("n", "0", start, blank, rright);
	add_step("n", blank, reject, blank, rright);
	print_steps();		
}