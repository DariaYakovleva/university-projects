#include <iostream>
#include <string>
#include <cstdio>
#include <vector>

using namespace std;

string s = "S";
string ac = "AC";
string rj = "RJ";
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

	string rd = "rd", ac1 = "ac1", aa = "aa", aa0 = "aa0", aa1 = "aa1", oo = "oo", oo0 = "oo0", oo1 = "oo1", ch = "ch";
	add_step(s, "0", "_", rd, "0", top, "_", top);
	add_step(s, "1", "_", rd, "1", top, "_", top);

	add_step(rd, "0", "_", rd, "_", r, "0", r);
	add_step(rd, "1", "_", rd, "_", r, "1", r);

	add_step(rd, "a", "_", aa, "_", top, "_", l);
	add_step(rd, "o", "_", oo, "_", top, "_", l);

	add_step(rd, "_", "_", ac1, "_", top, "_", l); //end
	add_step(ac1, "_", "0", ac1, "0", l, "_", l); 
	add_step(ac1, "_", "1", ac1, "1", l, "_", l); 
	add_step(ac1, "_", "_", ac, "_", r, "_", top); 
//	add_step(ch, "_", "_", ac, "_", r, "_", r); 

	add_step(aa, "_", "0", aa0, "_", top, "_", l);
	add_step(aa, "_", "1", aa1, "_", top, "_", l);

	add_step(aa0, "_", "0", rd, "_", r, "0", r);
	add_step(aa0, "_", "1", rd, "_", r, "0", r);

	add_step(aa1, "_", "0", rd, "_", r, "0", r);
	add_step(aa1, "_", "1", rd, "_", r, "1", r);

	add_step(oo, "_", "0", oo0, "_", top, "_", l);
	add_step(oo, "_", "1", oo1, "_", top, "_", l);

	add_step(oo0, "_", "0", rd, "_", r, "0", r);
	add_step(oo0, "_", "1", rd, "_", r, "1", r);

	add_step(oo1, "_", "0", rd, "_", r, "1", r);
	add_step(oo1, "_", "1", rd, "_", r, "1", r);


	        	
	print_steps();		
}