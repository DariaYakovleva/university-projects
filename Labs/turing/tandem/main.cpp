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
	freopen("tandem.out", "w", stdout);
	print_states();
	string chl = "chl";
	string chr = "chr";
	string fl = "fl";
	string fl2 = "fl2";
	string fr = "fr";
	string chl2 = "chl2";
	string chl3 = "chl3";
	string fr4 = "fr4";
	string frp4 = "frp4";
	string fl3 = "fl3";				

	add_step(start, "0", fr, "2", rright);
	add_step(start, "1", fr, "3", rright);

	add_step(fr, "0", fr, "0", rright);
	add_step(fr, "1", fr, "1", rright);
	add_step(fr, blank, chr, blank, lleft);
	add_step(fr, "2", chr, "2", lleft);
	add_step(fr, "3", chr, "3", lleft);

	add_step(chr, "0", fl, "2", lleft);
	add_step(chr, "1", fl, "3", lleft);
	add_step(chr, "2", reject, "2", top);
	add_step(chr, "3", reject, "3", top);

	add_step(fl, "0", fl, "0", lleft);
	add_step(fl, "1", fl, "1", lleft);
	add_step(fl, "2", chl, "2", rright);
	add_step(fl, "3", chl, "3", rright);

	add_step(chl, "0", fr, "2", rright);
	add_step(chl, "1", fr, "3", rright);
	add_step(chl, "2", fl2, "4", lleft);
	add_step(chl, "3", fl3, "4", lleft);

	add_step(fl2, "3", fl2, "3", lleft);
	add_step(fl2, "2", fl2, "2", lleft);
	add_step(fl2, "_", chl2, "_", rright);
	add_step(fl2, "4", fl2, "4", lleft);

	add_step(chl2, "2", frp4, "_", rright);
	add_step(chl2, "3", reject, "3", top); //reject

	add_step(fl3, "3", fl3, "3", lleft);
	add_step(fl3, "2", fl3, "2", lleft);
	add_step(fl3, "4", fl3, "4", lleft);
	add_step(fl3, "_", chl3, "_", rright);

	add_step(chl3, "3", frp4, "_", rright);
	add_step(chl3, "2", reject, "2", top); //reject

	add_step(frp4, "3", frp4, "3", rright);
	add_step(frp4, "2", frp4, "2", rright);
	add_step(frp4, "4", fr4, "4", rright);

	add_step(fr4, "4", fr4, "4", rright);
	add_step(fr4, "_", accept, "_", top);
	add_step(fr4, "3", fl3, "4", lleft);
	add_step(fr4, "2", fl2, "4", lleft);


	
	
	print_steps();		
}