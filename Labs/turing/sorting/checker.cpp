#include <iostream>
#include <cstdio>
#include <string>
#include <vector>

using namespace std;

vector< vector<string> > jumps;

string input1;
string input2;
string input3;



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
int pos1 = 1;
int pos2 = 1;
int pos3 = 1;

bool check() {
	input1 = "_" + input1 + "____________________________________________________________________________________________________________";
	input2 = "____________________________________________________________________________________________________________________________";
	input3 = "___________________________________________________________________________________________________________________________";
	string state = "S";

	while (true) {
		bool have = false;
		int ju = -1;
		for (size_t i = 0; i < jumps.size(); i++) {
			vector<string> jump = jumps[i];
			if (state == jump[0] && input1[pos1] == jump[1][0] && input2[pos2] == jump[2][0] && input3[pos3] == jump[3][0]) {
				state = jump[5];
				input1[pos1] = jump[6][0];
				input2[pos2] = jump[8][0];
				input3[pos3] = jump[10][0];
				if (jump[7] == ">") pos1++;
				if (jump[7] == "<") pos1--;
				if (jump[9] == ">") pos2++;
				if (jump[9] == "<") pos2--;
				if (jump[11] == ">") pos3++;
				if (jump[11] == "<") pos3--;
				have = true;
				ju = i;
				break;
			}	
		}
		if (state == "AC") return true;
		if (state == "RJ") {
			return false;
		}
		if (ju > -1) cout << "rule: " << jumps[ju][0] << endl;
		cout << "1! " << pos1 << " " << input1 << endl;
		cout << "2! " << pos2 << " " << input2 << endl;
		cout << "3! " << pos3 << " " << input3 << endl;
		cout << endl;
		if (!have) return false;
	}
}

int main() {
	getline(cin, input1);
	freopen("sorting.out", "r", stdin);
	string s;
//	for (int i = 0; i < 4; i++) {
//		getline(cin, s);
//	}
	int n;
	cin >> n;
	getline(cin, s);
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
	cout << input1 << "res = " << input1[pos1] << endl;		
	cout << input2 << "res = " << input2[pos2] << endl;		
	cout << input3 << "res = " << input3[pos3] << endl;		


}