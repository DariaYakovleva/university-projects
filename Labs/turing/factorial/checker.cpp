#include <iostream>
#include <cstdio>
#include <string>
#include <vector>

using namespace std;

vector< vector<string> > jumps;

string input;


void parse(string s) {
	s += " ";
	vector<string> cur;
	int pos = 0;
	while (pos < (int)s.size()) {
		string cs = "";
		while (s[pos] != ' ') {
			cs += s[pos];
			pos++;
		}
		cur.push_back(cs);
		while (s[pos] == ' ') pos++;
	}
	jumps.push_back(cur);
}

//s ac rj _

bool check() {
	input = "____" + input + "____________________________________________________________________________________________";
	string state = "s";
	int pos = 4;
	while (true) {
		bool have = false;
		int ju = -1;
		for (size_t i = 0; i < jumps.size(); i++) {
			vector<string> jump = jumps[i];
			if (state == jump[0] && input[pos] == jump[1][0]) {
				input[pos] = jump[4][0];
				state = jump[3];
				if (jump[5] == ">") pos++;
				if (jump[5] == "<") pos--;
				have = true;
				ju = i;
				break;
			}	
		}
		if (state == "ac") return true;
		if (state == "rj") {
			for (size_t j = 0; j < jumps[ju].size(); j++) {
				cout << jumps[ju][j] << " ";
			}				
			cout << ", pos = " << pos << endl;
			return false;
		}
		cout << pos << " " << input << " " << endl;
		if (!have) return false;
	}
}

int main() {
	getline(cin, input);
	freopen("factorial.out", "r", stdin);
	string s;
	for (int i = 0; i < 4; i++) {
		getline(cin, s);
	}
	while (getline(cin, s)) {
		parse(s);
	}
//	for (int i = 0; i < jumps.size(); i++) {
//		for (int j = 0; j < jumps[i].size(); j++) {
//			cout << jumps[i][j] << " ";
//		}
//		cout << endl;
//	}
	if (check()) {
		cout << "ACCEPTED" << endl;
	} else {
		cout << "REJECTED" << endl;
	}
	cout << input << endl;		


}