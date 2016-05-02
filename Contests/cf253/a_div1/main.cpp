#include <iostream>                                                                                         
#include <cstdio>
#include <algorithm>

//RGBYW

using namespace std;
int n, ost, mini = 110, bt[110];
pair< int, int > card[110]; 

bool check() {
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (!(((bt[card[i].first] || bt[card[j].first]) && (card[i].first != card[j].first)) || 
				(card[i].first == card[j].first && card[i].second == card[j].second)  || 
				((bt[card[i].second] || bt[card[j].second]) && (card[i].second != card[j].second)))) {
					return false;
			}
		}
	}
	return true;		
}

void adv(int pos, int k) {
	if (pos == 10) {
		if (check())
			mini = min(mini, k);
		return;
	}
	bt[pos] = 0;
	adv(pos + 1, k);
	bt[pos] = 1;
	adv(pos + 1, k + 1);
}

int main() {
	scanf("%d\n", &n);
	for (int i = 0; i < n; i++) {
		char a, b, c;
		scanf("%c%c%c", &a, &b, &c);
		if (a == 'R') card[i].first = 0; 
		if (a == 'G') card[i].first = 1; 
		if (a == 'B') card[i].first = 2; 
		if (a == 'Y') card[i].first = 3; 
		if (a == 'W') card[i].first = 4;
		card[i].second = (int)(b - '0') + 4; 
		
	}
	adv(0, 0);
	cout << mini << endl;

	return 0;                  
}