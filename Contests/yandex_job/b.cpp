#include <iostream>
#include <cmath>
#include <cstdio>
#include <vector>
#include <string>
#include <algorithm>
#include <map>

using namespace std;

string months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
int cntDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
map<string, int> numMonth;
string days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

void init() {
	for (int i = 0; i < 12; i++) {
		numMonth[months[i]] = i;	
	}
}

int day = 0;
int month = 0;
int year = 0;
void parseDate(string date) {
	int pos = 0;
	string mm = "";
	day = 0;
	month = 0;
	year = 0;
	while (date[pos] != ' ') {
		day = day * 10 + date[pos] - '0';
		pos++;		
	}
	pos++;
	while (date[pos] != ' ') {
		mm += date[pos];
		pos++;		
	}	
	month = numMonth[mm];
	pos++;
	while (date[pos] != ' ') {
		year = year * 10 + date[pos] - '0';
		pos++;		
	}		
}

bool isLeap(int y) {
	return (y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0));
}

int getDays() {
	int cnt = 0;
	for (int y = 1980; y < year; y++) {
		cnt += 365;
		if (isLeap(y)) {
			cnt++;			
		}
	}
	for (int m = 0; m < month; m++) {
		cnt += cntDays[m];
		if ((m == 1) && isLeap(year)) cnt++;
	}
	cnt += day;
	return cnt;
}




int main() {
	freopen("input.txt", "r", stdin);
	freopen("output.txt", "w", stdout);
	init();
	string s;
	while (getline(cin, s)) {
//	for (int yy = 1980; yy <= 2100; yy++) {
//		for (int mm = 0; mm < 12; mm++) {
//			for (int dd = 0; dd < cntDays[mm]; dd++) {
//				s = to_string(dd + 1) + " " + months[mm] + " " + to_string(yy);
				s += ' ';	
				parseDate(s);
				int d = getDays();
				cout << days[d % 7]  << " " << s << endl;  
//			}
//		}
	}

	return 0;
}