#include <cstdio>
#include <iostream>
#include <vector>
#include <string>

using namespace std;

string s = "S";
string ac = "AC";
string rj = "RJ";
string b = "_";
string top = "^";
string l = "<";
string r = ">";
vector<string> states;
vector< pair<string, string> > steps;

void print_states() {
	cout << "start: " << s << endl;
	cout << "accept: " << ac << endl;
	cout << "reject: " << rj << endl;
	cout << "blank: " << b << endl;
}

void print_step(int pos) {
	cout << steps[pos].first << " -> " << steps[pos].second << endl;	
}

void add_step(string from, string st1, string st2, string st3, string to, string nst1, string go1, string nst2, string go2, string nst3, string go3) {
	steps.push_back(make_pair(from + " " + st1 + " " + st2 + " " + st3, to + " " + nst1 + " " + go1 + " " + nst2 + " " + go2 + " " + nst3 + " " + go3));
}

void print_steps() {
	for (size_t i = 0; i < steps.size(); i++) {
		print_step(i);
	}
}


int main() {
	freopen("sorting.out", "w", stdout);
//	print_states();
  	cout << 3 << endl;

  	string rd = "rd", so = "so", mi = "mi", mi2 = "mi2", goe2 = "goe2", fm = "fm", nextn = "nextn", les = "les", goe3 = "goe3", remm = "remm", cmp = "cmp", remm2 = "remm2";

  	add_step(s, "0", "_", "_", rd, "0", top, "_", top, "_", top);
  	add_step(s, "1", "_", "_", rd, "1", top, "_", top, "_", top);

  	add_step(rd, "0", "_", "_", rd, "_", r, "0", r, "_", top); //copy 1 to 2
  	add_step(rd, "1", "_", "_", rd, "_", r, "1", r, "_", top);
  	add_step(rd, "|", "_", "_", rd, "_", r, "|", r, "_", top);
  	add_step(rd, "_", "_", "_", so, "_", l, "_", l, "_", top);


  	add_step(so, "_", "0", "_", so, "_", top, "0", l, "_", top); // go to begin in 2
  	add_step(so, "_", "1", "_", so, "_", top, "1", l, "_", top);
  	add_step(so, "_", "x", "_", so, "_", top, "x", l, "_", top);
  	add_step(so, "_", "|", "_", so, "_", top, "|", l, "_", top);
  	add_step(so, "_", "_", "_", mi2, "_", top, "_", r, "_", top);

  	add_step(mi2, "_", "0", "_", mi, "_", top, "0", top, "_", top); // miss xxx
  	add_step(mi2, "_", "1", "_", mi, "_", top, "1", top, "_", top);
  	add_step(mi2, "_", "|", "_", mi2, "_", top, "|", r, "_", top);
  	add_step(mi2, "_", "x", "_", mi2, "_", top, "x", r, "_", top);
  	add_step(mi2, "_", "_", "_", "en", "_", l, "_", top, "_", top); // ends

  	add_step(mi, "_", "0", "_", mi, "_", top, "0", r, "0", r); // write first to 3
  	add_step(mi, "_", "1", "_", mi, "_", top, "1", r, "1", r);
  	add_step(mi, "_", "|", "_", goe2, "_", top, "|", top, "_", top);
  	add_step(mi, "_", "_", "_", goe2, "_", top, "_", top, "_", top);

  	add_step(goe2, "_", "0", "_", goe2, "_", top, "0", r, "_", top); //go to end 2
  	add_step(goe2, "_", "1", "_", goe2, "_", top, "1", r, "_", top);
  	add_step(goe2, "_", "|", "_", goe2, "_", top, "|", r, "_", top);
  	add_step(goe2, "_", "x", "_", goe2, "_", top, "x", r, "_", top);
  	add_step(goe2, "_", "_", "_", fm, "_", top, "_", l, "_", l);  	

	add_step(goe3, "_", "|", "0", goe3, "_", top, "|", top, "0", r); // go to end cur 3 _ | ?
	add_step(goe3, "_", "|", "1", goe3, "_", top, "|", top, "1", r);
	add_step(goe3, "_", "|", "_", fm, "_", top, "|", l, "_", l);

	add_step(nextn, "_", "0", "0", nextn, "_", top, "0", l, "0", top); // go to next n 2 left
	add_step(nextn, "_", "1", "0", nextn, "_", top, "1", l, "0", top);
	add_step(nextn, "_", "|", "0", goe3, "_", top, "|", top, "0", top);
	add_step(nextn, "_", "_", "0", remm2, "_", top, "_", top, "0", top);
	add_step(nextn, "_", "0", "1", nextn, "_", top, "0", l, "1", top); // go to next n 2 left
	add_step(nextn, "_", "1", "1", nextn, "_", top, "1", l, "1", top);
	add_step(nextn, "_", "|", "1", goe3, "_", top, "|", top, "1", top);
	add_step(nextn, "_", "_", "1", remm2, "_", top, "_", top, "1", top);
	add_step(nextn, "_", "x", "0", nextn, "_", top, "x", l, "0", top);
	add_step(nextn, "_", "x", "1", nextn, "_", top, "x", l, "1", top); 


  	add_step(fm, "_", "0", "0", fm, "_", top, "0", l, "0", l); // find min left
  	add_step(fm, "_", "0", "1", fm, "_", top, "0", l, "1", l);
  	add_step(fm, "_", "1", "0", fm, "_", top, "1", l, "0", l);
  	add_step(fm, "_", "1", "1", fm, "_", top, "1", l, "1", l);

  	add_step(fm, "_", "|", "0", les, "_", top, "|", r, "0", top);
  	add_step(fm, "_", "|", "1", les, "_", top, "|", r, "1", top);
  	add_step(fm, "_", "|", "_", cmp, "_", top, "|", r, "_", r); //equal length

  	add_step(cmp, "_", "0", "0", cmp, "_", top, "0", r, "0", r); //cmp to right
  	add_step(cmp, "_", "0", "1", les, "_", top, "0", top, "1", top); 
  	add_step(cmp, "_", "1", "0", nextn, "_", top, "1", top, "0", top); 
  	add_step(cmp, "_", "1", "1", cmp, "_", top, "1", r, "1", r);
  	add_step(cmp, "_", "|", "_", nextn, "_", top, "|", l, "_", l);
  	add_step(cmp, "_", "_", "_", nextn, "_", top, "_", l, "_", l);

  	add_step(fm, "_", "0", "_", nextn, "_", top, "0", top, "_", r);
  	add_step(fm, "_", "1", "_", nextn, "_", top, "1", top, "_", r);
  	add_step(fm, "_", "x", "0", nextn, "_", top, "x", top, "0", top);
  	add_step(fm, "_", "x", "1", nextn, "_", top, "x", top, "1", top);
  	add_step(fm, "_", "x", "_", nextn, "_", top, "x", top, "_", top);

  	add_step(fm, "_", "_", "0", les, "_", top, "_", r, "0", top);
  	add_step(fm, "_", "_", "1", les, "_", top, "_", r, "1", top);
  	add_step(fm, "_", "_", "_", remm2, "_", top, "_", top, "_", r); // equal len? go to rem min !

  	string les2 = "les2", les3 = "les3", les4 = "les4", les21 = "les21";

  	add_step(les, "_", "0", "0", les, "_", top, "0", l, "0", top); // set new min in 3
  	add_step(les, "_", "0", "1", les, "_", top, "0", l, "1", top); // les -- go to left in 2
  	add_step(les, "_", "1", "0", les, "_", top, "1", l, "0", top);
  	add_step(les, "_", "1", "1", les, "_", top, "1", l, "1", top);
  	add_step(les, "_", "|", "0", les21, "_", top, "|", top, "0", top);
  	add_step(les, "_", "|", "1", les21, "_", top, "|", top, "1", top);

  	add_step(les21, "_", "|", "0", les21, "_", top, "|", top, "0", l);
  	add_step(les21, "_", "|", "1", les21, "_", top, "|", top, "1", l);
  	add_step(les21, "_", "|", "_", les2, "_", top, "|", r, "_", r);

  	add_step(les2, "_", "0", "0", les2, "_", top, "0", r, "0", r); // rewrite to right
  	add_step(les2, "_", "0", "1", les2, "_", top, "0", r, "0", r);  	
  	add_step(les2, "_", "1", "0", les2, "_", top, "1", r, "1", r);
  	add_step(les2, "_", "1", "1", les2, "_", top, "1", r, "1", r);

  	add_step(les2, "_", "|", "0", les3, "_", top, "|", top, "0", top);
  	add_step(les2, "_", "|", "1", les3, "_", top, "|", top, "1", top);
  	add_step(les2, "_", "|", "_", les4, "_", top, "|", top, "_", top);
  	add_step(les2, "_", "_", "0", les3, "_", top, "_", top, "0", top);
  	add_step(les2, "_", "_", "1", les3, "_", top, "_", top, "1", top);
  	add_step(les2, "_", "_", "_", les4, "_", top, "_", top, "_", top);

  	add_step(les3, "_", "|", "0", les3, "_", top, "|", top, "_", r); //delete useless sym in 3
  	add_step(les3, "_", "|", "1", les3, "_", top, "|", top, "_", r);
  	add_step(les3, "_", "|", "_", les4, "_", top, "|", top, "_", l);
  	add_step(les3, "_", "_", "0", les3, "_", top, "_", top, "_", r);
  	add_step(les3, "_", "_", "1", les3, "_", top, "_", top, "_", r);
  	add_step(les3, "_", "_", "_", les4, "_", top, "_", top, "_", l);

  	add_step(les4, "_", "|", "_", les4, "_", top, "|", top, "_", l); // go throw _ in 3 and go to fm
  	add_step(les4, "_", "_", "_", les4, "_", top, "_", top, "_", l);
  	add_step(les4, "_", "|", "0", fm, "_", top, "|", l, "0", top);
  	add_step(les4, "_", "|", "1", fm, "_", top, "|", l, "1", top);
  	add_step(les4, "_", "_", "0", fm, "_", top, "_", l, "0", top);
  	add_step(les4, "_", "_", "1", fm, "_", top, "_", l, "1", top);

 	string tob3 = "tob3", delm = "delm", delm2 = "delm2", delx = "delx";

 	add_step(remm2, "_", "_", "0", remm2, "_", top, "_", top, "0", l);
 	add_step(remm2, "_", "_", "1", remm2, "_", top, "_", top, "1", l);
 	add_step(remm2, "_", "_", "_", remm, "_", top, "_", top, "_", r);

  	add_step(remm, "_", "_", "0", remm, "0", r, "_", top, "0", r); //write to ans
  	add_step(remm, "_", "_", "1", remm, "1", r, "_", top, "1", r);
  	add_step(remm, "_", "_", "_", tob3, "|", r, "_", top, "_", l);


  	add_step(tob3, "_", "_", "0", tob3, "_", top, "_", top, "0", l); // go to begin 3
  	add_step(tob3, "_", "_", "1", tob3, "_", top, "_", top, "1", l);
  	add_step(tob3, "_", "_", "_", delm, "_", top, "_", r, "_", r);                                                                	

  	add_step(delm, "_", "0", "0", delm, "_", top, "0", r, "0", r); // find min ans delelte right
  	add_step(delm, "_", "0", "1", delm2, "_", top, "0", top, "1", top);

  	add_step(delm, "_", "x", "0", delm2, "_", top, "x", top, "0", top);
  	add_step(delm, "_", "x", "1", delm2, "_", top, "x", top, "1", top);

  	add_step(delm, "_", "0", "_", delm2, "_", top, "0", top, "_", l);
  	add_step(delm, "_", "1", "0", delm2, "_", top, "1", top, "0", top);
  	add_step(delm, "_", "1", "1", delm, "_", top, "1", r, "1", r);
  	add_step(delm, "_", "1", "_", delm2, "_", top, "1", top, "_", l);
  	add_step(delm, "_", "|", "0", delm2, "_", top, "|", top, "0", top);
  	add_step(delm, "_", "|", "1", delm2, "_", top, "|", top, "1", top);
  	add_step(delm, "_", "|", "_", delx, "_", top, "|", l, "_", l);
  	add_step(delm, "_", "_", "_", delx, "_", top, "_", l, "_", l);

  	add_step(delm2, "_", "0", "0", delm2, "_", top, "0", r, "0", top); // continue right
  	add_step(delm2, "_", "0", "1", delm2, "_", top, "0", r, "1", top);
  	add_step(delm2, "_", "0", "_", delm2, "_", top, "0", r, "_", l);
  	add_step(delm2, "_", "1", "0", delm2, "_", top, "1", r, "0", top);
  	add_step(delm2, "_", "1", "1", delm2, "_", top, "1", r, "1", top);
  	add_step(delm2, "_", "1", "_", delm2, "_", top, "0", r, "_", l);

  	add_step(delm2, "_", "x", "0", delm2, "_", top, "x", r, "0", top);
  	add_step(delm2, "_", "x", "1", delm2, "_", top, "x", r, "1", top);


  	add_step(delm2, "_", "|", "0", delm2, "_", top, "|", top, "0", l);
  	add_step(delm2, "_", "|", "1", delm2, "_", top, "|", top, "1", l);
  	add_step(delm2, "_", "|", "_", delm, "_", top, "|", r, "_", r); // ok

	add_step(delx, "_", "|", "_", so, "_", top, "|", top, "_", r); // delete value
	add_step(delx, "_", "_", "_", so, "_", top, "_", top, "_", r); // go to so
	add_step(delx, "_", "0", "0", delx, "_", top, "x", l, "_", l);
	add_step(delx, "_", "1", "1", delx, "_", top, "x", l, "_", l);

	string en = "en";
	string en1 = "en1";
	add_step(en, "|", "_", "_", en1, "_", l, "_", top, "_", top);
	add_step(en1, "0", "_", "_", en1, "0", l, "_", top, "_", top);
	add_step(en1, "1", "_", "_", en1, "1", l, "_", top, "_", top);
	add_step(en1, "|", "_", "_", en1, "|", l, "_", top, "_", top);
	add_step(en1, "_", "_", "_", ac, "_", r, "_", top, "_", top);

	        	
	print_steps();		

	return 0;
}
