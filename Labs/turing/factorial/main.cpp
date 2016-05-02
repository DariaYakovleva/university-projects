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

void add_step(string from, string st, string to, string nst, string go) {
	steps.push_back(make_pair(from + " " + st, to + " " + nst + " " + go));
}

void print_steps() {
	for (size_t i = 0; i < steps.size(); i++) {
		print_step(i);
	}
}

const int s_s = 10;
string symb[s_s] = {"0", "1", "*", "2", "3", "4", "!", "_", "$", "x"};

void to_end(string state, string stop, string next) { //go to end while current position isn't equal to stop
	for (int i = 0; i < s_s; i++) {
		if (symb[i] != stop) add_step(state, symb[i], state, symb[i], r); 	 	
	}
	add_step(state, stop, next, stop, top); 	
}

void to_begin(string state, string stop, string next) { //go to begin while current position isn't equal to stop
	for (int i = 0; i < s_s; i++) {
		if (symb[i] != stop) add_step(state, symb[i], state, symb[i], l); 	 	
	}
	add_step(state, stop, next, stop, top); 	
}


int main() {
	freopen("factorial.out", "w", stdout);
	print_states();
	string go = "go", ff = "ff", ff2 = "ff2";

	//create a*b


	add_step(s, "1", ff, "1", l); 
	add_step(s, "0", ff, "0", l); 
	add_step(ff, "_", ff2, "*", l);
	add_step(ff2, "_", go, "1", top);

	// start multiply with GO
	string setv = "setv";
	to_end(go, "_", setv);
	string gob = "gob";
	add_step(setv, "_", gob, "!", top);
	string rep = "rep";
	to_begin(gob, "_", rep);

	add_step(rep, "_", rep, "_", r); //rep -- replace cur and go to set 0 in ans
	string go2 = "go2";
	add_step(rep, "0", go2, "2", r);
	add_step(rep, "1", go2, "3", r);
	add_step(rep, "*", go2, "+", r);
	string go22 = "go22";	
	add_step(rep, "!", go22, "!", r);
	string setz = "setz";
	to_end(go2, "_", setz);  // go2 -- go to end while not blank
	string go2l = "go2l";
	add_step(setz, "_", go2l, "0", l); //setz -- set 0

	add_step(go2l, "1", go2l, "1", l);
	add_step(go2l, "0", go2l, "0", l);
	add_step(go2l, "*", go2l, "*", l);
	add_step(go2l, "+", rep, "*", r);
	add_step(go2l, "2", rep, "0", r);
	add_step(go2l, "3", rep, "1", r);
	add_step(go2l, "!", go2l, "!", l);
	add_step(go2l, "_", go2, "_", r);
	string setv2 = "setv2";		
	to_end(go22, "_", setv2);
	string gob2 = "gob2";
	add_step(setv2, "_", gob2, "!", top);
	string cb = "cb";
	string cb2 = "cb2";
	to_begin(gob2, "_", cb2);  //cb2 -- go to *

	to_end(cb2, "*", cb);

	add_step(cb, "*", cb, "*", r); //cb -- replace second num pos
	string gobr0 = "gobr0";
	string gobr1 = "gobr1", star = "star";
	add_step(cb, "0", gobr0, "2", r);
	add_step(cb, "1", gobr1, "3", r);
	add_step(cb, "!", star, "!", r); // now we should gp to star
	                                     
	add_step(gobr0, "0", gobr0, "0", r); //gobr0 -- set 0
	add_step(gobr0, "1", gobr0, "1", r);
	add_step(gobr0, "!", gobr0, "!", r);
	string rcb = "rcb";
	add_step(gobr0, "_", rcb, "0", l);

	add_step(gobr1, "0", gobr1, "0", r); // gobr1 -- set 1
	add_step(gobr1, "1", gobr1, "1", r);
	add_step(gobr1, "!", gobr1, "!", r);
	add_step(gobr1, "_", rcb, "1", l);


	add_step(rcb, "1", rcb, "1", l);
	add_step(rcb, "0", rcb, "0", l);
	add_step(rcb, "2", cb, "0", r);
	add_step(rcb, "3", cb, "1", r);
	add_step(rcb, "!", rcb, "!", l);

	// multiply 
	string mul = "mul", mul1 = "mul1", mul0 = "mul0", mset0 = "mset0", mgol = "mgol", nextm = "nextm", clr = "clr";
	to_begin(star, "*", mul);
	add_step(mul, "*", mul, "*", l);
	add_step(mul, "0", mul0, "x", top);
	add_step(mul, "1", mul1, "x", top);
	add_step(mul, "_", "toe", "_", r); //end mul


	//add 0 to end
	to_end(mul0, "_", mset0);
	add_step(mset0, "_", mgol, "0", l);
	to_begin(mgol, "x", nextm);
	add_step(nextm, "x", nextm, "x", l);
	add_step(nextm, "0", mul, "0", top);
	add_step(nextm, "1", mul, "1", top);
	add_step(nextm, "_", mul, "_", top);


	//go to SUM
	to_end(mul1, "!", "sv3");
	add_step("sv3", "!", "sv4", "!", r); 
	to_end("sv4", "!", "sv");
	add_step("sv", "!", "ge", "$", r); 


	//SUM start from everything GE!
	string ge = "ge", sum = "sum", sl0 = "sl0", sl1 = "sl1", s0 = "s0", s1 = "s1", sr = "sr", ost = "ost", ggo = "ggo", ensum = "ensum";
	to_end(ge, "_", sum);
	add_step(sum, "_", sum, "_", l);
	add_step(sum, "0", sl0, "2", l);
	add_step(sum, "1", sl1, "3", l);
	add_step(sum, "!", ensum, "!", l);

	add_step(sl0, "0", sl0, "0", l);
	add_step(sl0, "1", sl0, "1", l);
	add_step(sl0, "2", s0, "0", l);
	add_step(sl0, "3", s0, "1", l);
	add_step(sl0, "$", s0, "!", l);
	add_step(sl0, "!", sl0, "!", l);

	add_step(s0, "0", sr, "2", r);
	add_step(s0, "1", sr, "3", r);

	add_step(sr, "0", sr, "0", r);
	add_step(sr, "1", sr, "1", r);
	add_step(sr, "!", sr, "!", r);
	add_step(sr, "2", sum, "0", l);
	add_step(sr, "3", sum, "1", l);
	
	add_step(sl1, "0", sl1, "0", l);
	add_step(sl1, "1", sl1, "1", l);
	add_step(sl1, "2", s1, "0", l);
	add_step(sl1, "3", s1, "1", l);
	add_step(sl1, "$", s1, "!", l);
	add_step(sl1, "!", sl1, "!", l);

	add_step(s1, "0", sr, "3", r);

	add_step(s1, "1", ost, "2", l);


	add_step(ost, "0", ggo, "1", r);
	add_step(ost, "1", ost, "0", l);

	to_end(ggo, "!", sr); 	

	add_step(ensum, "0", ensum, "0", l);
	add_step(ensum, "1", ensum, "1", l);
	add_step(ensum, "2", ensum, "0", l);
	add_step(ensum, "3", ensum, "1", l);
	add_step(ensum, "!", mul0, "!", top);


	// repeat mul
	string toe = "toe", tol = "tol", tost2 = "tost2", tost = "tost", dub = "dub", 
	dub0 = "dub0", dub1 = "dub1", sd0 = "sd0", sd1 = "sd1", dubl = "dubl", dub3 = "dub3";

	to_end(toe, "_", tol);
	add_step(tol, "_", tol, "_", l); 
	add_step(tol, "0", tol, "_", l); 
	add_step(tol, "1", tol, "_", l); 
	add_step(tol, "!", tost2, "*", l); 
	to_begin(tost2, "_", tost);
	add_step(tost, "x", tost, "_", r);
	add_step(tost, "_", tost, "_", r);
	add_step(tost, "0", tost, "_", r);
	add_step(tost, "1", tost, "_", r);
	add_step(tost, "*", dub, "_", r);
	
	add_step(dub, "0", dub0, "_", r);
	add_step(dub, "_", dub, "_", r);
	add_step(dub, "1", dub1, "_", r);
	add_step(dub, "!", dub3, "_", r);

	to_begin(dubl, "_", dub);
	to_end(dub0, "_", sd0);
	to_end(dub1, "_", sd1);
	add_step(sd0, "_", dubl, "0", l);
	add_step(sd1, "_", dubl, "1", l);

	add_step(dub3, "0", dub3, "_", r);
	add_step(dub3, "1", "sub", "1", top);

	// sub
	string sub = "sub", sub2 = "sub2", sub3 = "sub3", sub4 = "sub4", sub5 = "sub5", check = "check"; 
	to_end(sub, "_", sub2);
	add_step(sub2, "_", sub2, "_", l);
	add_step(sub2, "0", sub2, "0", l);
	add_step(sub2, "1", sub3, "0", r);
	add_step(sub3, "0", sub3, "1", r);
	add_step(sub3, "_", check, "_", l);

	add_step(check, "0", check, "0", l);
	add_step(check, "1", sub4, "1", top);
	add_step(check, "*", clr, "*", top); //END factorial

	to_begin(sub4, "_", sub5);
	add_step(sub5, "_", go, "_", r);

		
	
	
	// clear 

	string clr2 = "clr2", clr3 = "clr3", clr4 = "clr4";
	to_end(clr, "_", clr2);
	add_step(clr2, "0", clr2, "_", l);
	add_step(clr2, "1", clr2, "_", l);
	add_step(clr2, "_", clr2, "_", l);
	add_step(clr2, "*", clr3, "_", l);
	to_begin(clr3, "_", clr4);
	add_step(clr4, "_", ac, "_", r);
            	
	print_steps();		
}