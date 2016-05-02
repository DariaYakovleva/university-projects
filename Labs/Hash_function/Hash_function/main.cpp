
#include <iostream>
#include <cstdio>
#include <string>
#include <cmath>
using namespace std;

struct elem {
    int x;
    elem *next;
    elem(){}
    elem(int a) {
        x = a;
        next  = NULL;
    }
};

const int N = 1000007;
elem* hash_table[N];

int get_hash(int a) {
    int c = abs(a);
    return c % N;
}

void insert(int a) {
    int pos = get_hash(a);
    elem* p = hash_table[pos];
    if (hash_table[pos] == NULL) {
        hash_table[pos] = new elem(a);
        return;
    }
    while (p->next != NULL) {
        p = p->next;
    }
    p->next = new elem(a);
}
void deleted(int a) {
    int pos = get_hash(a);
    
    if (hash_table[pos]->x == a) {
        hash_table[pos] = hash_table[pos]->next;
        return;
    }
    elem* p = hash_table[pos];
    elem* pred = p;
    while (p->x != a) {
        pred = p;
        p = p->next;
    }
    pred->next = p->next;
}
bool exist(int a) {
    int pos = get_hash(a);
    elem* p = hash_table[pos];
    while (p != NULL) {
        if (p->x == a) {
            return true;
        }
        p = p->next;
    }
    return false;
}

void init() {
    for (int i = 0; i < N; i++) {
        hash_table[i] = NULL;
    }
}

int main() {
    freopen("set.in", "r", stdin);
    freopen("set.out", "w", stdout);
    init();
    char* s = new char[20];
    while (scanf("%s", s)) {
        int a;
        scanf("%d\n", &a);
        if (s[0] == 'i') {
            if (!exist(a)) {
                insert(a);
            }
        }
        if (s[0] == 'd') {
            if (exist(a)) {
                deleted(a);
            }
        }
        if (s[0] == 'e') {
            if (exist(a))
                printf("true\n");
            else
                printf("false\n");
        }
    }
}