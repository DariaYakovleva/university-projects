#include <iostream>
#include <cstdio>

using namespace std;

int n;

struct Treap {
    int x;
    int y;
    Treap *Left;
    Treap *Right;

    Treap() {
        Left = NULL;
        Right = NULL;
    }

    Treap(int x, int y, Treap *left = NULL, Treap *right = NULL) {
        this->x = x;
        this->y = y;
        this->Left = left;
        this->Right = right;
    }
};

Treap* Find(Treap *tree, int x) { // Найти номер элемента с ключом x
    if (tree == NULL) return NULL;
    if (tree->x == x) { return tree; }
    if (tree->x > x) { return Find(tree->Left, x); }
    if (tree->x < x) { return Find(tree->Right, x); }
}

Treap* Merge(Treap *L, Treap *R) {
    if (L == NULL) return R;
    if (R == NULL) return L;
    if (L->y > R->y) {
        Treap* newR = Merge(L->Right, R);
        return new Treap(L->x, L->y, L->Left, newR);
    }
    else {
        Treap* newL = Merge(L, R->Left);
        return new Treap(R->x, R->y, newL, R->Right);
    }
}

pair<Treap*, Treap*> Split(Treap* tree, int x) {
    Treap* n = NULL;
    if (tree == NULL) return make_pair(n, n);
    Treap newTree;
    pair<Treap*, Treap*> ans;
    if (tree->x <= x) {
        if (tree->Right == NULL) {
            ans.second = NULL;
            ans.first = tree;
        } else {
            pair<Treap*, Treap*> tmp = Split(tree->Right, x);
            ans.second = tmp.second;
            Treap* res = new Treap(tree->x, tree->y, tree->Left, tmp.first);
            ans.first = res;
        }
    } else {
        if (tree->Left == NULL) {
            ans.first = NULL;
            ans.second = tree;
        } else {
            pair<Treap*, Treap*> tmp = Split(tree->Left, x);
            ans.first = tmp.first;
            Treap* res = new Treap(tree->x, tree->y, tmp.second, tree->Right);
            ans.second = res;
        }
    }
    return ans;
}

Treap* Insert(Treap* tree, int x) { // Добавление элемента в дерево
    Treap* elem = new Treap(x, rand() % 100);
    if (tree == NULL) {
        return elem;
    }
    pair<Treap*, Treap*> tmp = Split(tree, x);
    Treap* left = Merge(tmp.first, elem);
    return Merge(left, tmp.second);
}

Treap* Delete(Treap* tree, int x) { // Удаление элемента из дерева
    if (tree == NULL) return NULL;
    Treap m;
    pair<Treap*, Treap*> tmp = Split(tree, x - 1);
    pair<Treap*, Treap*> tmp2 = Split(tmp.second, x);
    return Merge(tmp.first, tmp2.second);
}

void print(Treap *tree, int h) { // Вывод дерева tree на экран (h - высота)
    if (tree == NULL) {
        return;
    }
    print(tree->Left, h + 6);
    for (int i = 1; i <= h; i++) {
        cout << ' ';
    }
    cout << tree->x;
    print(tree->Right, h + 6);
}

int main() {
    Treap *tree = NULL;
    int x;
    cin >> n;
    for (int i = 0; i < n; i++) { // Ввод элементов в дерево
        cin >> x;
        tree = Insert(tree, x);
    }
    print(tree, 3);
    cout << endl;
    Treap* res = Find(tree, 4);
    cout << res->x;
    return 0;
}