#include <iostream>
#include <string>
#include <cstdio>
#include <vector>

using namespace std;

string s = "s";
string ac = "ac";
string rj = "rj";
string b = "_";
string top = "^";
string l = "<";
string r = ">";
vector<string> states;
vector<pair<string, string>> steps;

void print_states() {
	cout << "start: " << s << endl;
	cout << "accept: " << ac << endl;
	cout << "reject: " << rj << endl;
	cout << "blank: " << b << endl;
}

void print_step(int pos) {
	cout << steps[pos].first << " -> " << steps[pos].second << endl;	
}

void add_step(string from, string st1, string st2, string to, string nst1, string go1, string nst2, string go2) {
	steps.push_back(make_pair(from + " " + st1 + " " + st2, to + " " + nst1 + " " + go1 + " " + nst2 + " " + go2));
}

void print_steps() {
	for (size_t i = 0; i < steps.size(); i++) {
		print_step(i);
	}
}

const int s_s = 10;
string symb[s_s] = {"0", "1", "*", "2", "3", "4", "a", "_", "o", "x"};


int main() {
	freopen("postfixlogic.out", "w", stdout);
//	print_states();
  	cout << 2 << endl;

	string rd = "rd", nx = "nx", cmp = "cmp", vala = "vala", valo = "valo", nxa = "nxa", ac1 = "ac1";
	add_step(s, "0", "_", rd, "0", top, "_", top);
	add_step(s, "1", "_", rd, "1", top, "_", top);

	add_step(rd, "0", "_", nx, "_", r, "0", r);
	add_step(rd, "1", "_", nx, "_", r, "1", r);


	add_step(nx, "0", "_", cmp, "0", r, "_", top);
	add_step(nx, "1", "_", cmp, "1", r, "_", top);

	add_step(nx, "_", "_", ac1, "_", l, "_", l); 
	add_step(ac1, "_", "0", ac, "0", top, "_", top); //end
	add_step(ac1, "_", "1", ac, "1", top, "_", top); //end

	add_step(cmp, "a", "_", vala, "_", l, "_", l);
	add_step(cmp, "o", "_", valo, "_", l, "_", l);

	add_step(vala, "0", "0", nxa, "_", r, "0", top);
	add_step(vala, "0", "1", nxa, "_", r, "0", top);
	add_step(vala, "1", "0", nxa, "_", r, "0", top);
	add_step(vala, "1", "1", nxa, "_", r, "1", top);

	add_step(valo, "0", "0", nxa, "_", r, "0", top);
	add_step(valo, "0", "1", nxa, "_", r, "1", top);
	add_step(valo, "1", "0", nxa, "_", r, "1", top);
	add_step(valo, "1", "1", nxa, "_", r, "1", top);

	add_step(nxa, "_", "0", rd, "0", top, "_", top);
	add_step(nxa, "_", "1", rd, "1", top, "_", top);




	        	
	print_steps();		
}