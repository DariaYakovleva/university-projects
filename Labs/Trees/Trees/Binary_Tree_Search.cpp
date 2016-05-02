//
//  main.cpp
//  Trees
//
//  Created by Дарья Яковлева on 04.04.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//

#include <iostream>
#include <cstdio>
#include <cmath>
#include <string>


using namespace std;

const int N = 100010, INF = 1000000000;


struct vertex
{
    int value;
    vertex *left, *right, *parent;
    
};

vertex *root;


vertex* newv(int a)
{
    vertex *v = new vertex;
    v->value = a;
    v->left = NULL;
    v->right = NULL;
    v->parent = NULL;
    return v;
}

vertex* search(vertex* x, int key)
{
    if (x->value == key)
        return x;
    if (x->value > key)
    {
        if (x->left == NULL)
        {
            return x;
        }
        else
            return search(x->left, key);
    }
    else
    {
        if (x->right == NULL)
        {
            return x;
        }
        else
            return search(x->right, key);
    }
}

vertex* next(int a)
{
    if (root == NULL)
        return NULL;
    vertex* x = search(root, a);
    if (x->value <= a)
    {
        if (x->right != NULL)
        {
            x = x->right;
            while (x->left != NULL)
                x = x->left;
            return x;
        }
        while (x->parent != NULL && x == x->parent->right)
            x = x->parent;
        return x->parent;
    }
    return x;
}

vertex* prev(int a)
{
    if (root == NULL)
        return NULL;
    vertex* x = search(root, a);
    if (x->value >= a)
    {
        if (x->left != NULL)
        {
            x = x->left;
            while (x->right != NULL)
                x = x->right;
            return x;
        }
        while (x->parent != NULL && x == x->parent->left)
            x = x->parent;
        return x->parent;
    }
    return x;
}


void insert(vertex *x, int a)
{
    if (a < x->value)
    {
        if (x->left == NULL)
        {
            vertex*v = newv(a);
            v->parent = x;
            x->left = v;
        }
        else
        {
            insert(x->left, a);
        }
    }
    else
    {
        if (x->right == NULL)
        {
            vertex*v = newv(a);
            v->parent = x;
            x->right = v;
        }
        else
        {
            insert(x->right, a);
        }
    }
}

void deletev(vertex* t, vertex *v)
{
    vertex *p = v->parent;
    if (v->left == NULL && v->right == NULL)
    {
        if (p == NULL)
        {
            root = NULL;
            return;
        }
        if (p->left == v)
            p->left = NULL;
        if (p->right == v)
            p->right = NULL;
    }
    else
    {
        if (v->left == NULL || v->right == NULL)
        {
            if (v->left == NULL)
            {
                if (p == NULL)
                {
                    root = p = v->right;
                    root->parent = NULL;
                    return;
                }
                if (p->left == v)
                    p->left = v->right;
                else
                    p->right = v->right;
                v->right->parent = p; 
            }
            
            if (v->right == NULL)
            {
                if (p == NULL)
                {
                    root = p = v->left;
                    root->parent = NULL;
                    return;
                }
                if (p->left == v)
                    p->left = v->left;
                else
                    p->right = v->left;
                v->left->parent = p; 
            }
        }
        else
        {
            vertex *nv = next(v->value);
            v->value = nv->value;
            if (nv->parent->left == nv)
            {
                nv->parent->left = nv->right;
                if (nv->right != NULL)
                    nv->right->parent = nv->parent;
            }
            else
            {
                nv->parent->right = nv->right;
                if (nv->right != NULL)
                    nv->right->parent = nv->parent;
            }
        }
    }
    
}


int main() 
{
    freopen("bstsimple.in", "r", stdin);
    freopen("bstsimple.out", "w", stdout);
    root = new vertex;
    root = NULL;
    string s;
    while (cin >> s)
    {
        int a;
        cin >> a;
        if (s[0] == 'i')
        {
            if (root == NULL)
            {
                root = newv(a);
            }
            else
            {
                if (search(root, a)->value != a)
                    insert(root, a);
            }
        }
        if (s[0] == 'd')
        {
            if ((root != NULL) && (search(root, a)->value == a))
                deletev(root, search(root, a));
        }
        if (s[0] == 'e')
        {
            if ((root != NULL) && (search(root, a)->value == a))
                cout << "true" << endl;
            else
                cout << "false" << endl;
        }
        if (s[0] == 'n')
        {
            vertex* res = next(a);
            if (res == NULL)
                cout << "none" << endl;
            else
                cout << res->value << endl;
        }
        if (s[0] == 'p')
        {
            vertex* res = prev(a);
            if (res == NULL)
                cout << "none" << endl;
            else
                cout << res->value << endl;
        }
        
    }
    
    return 0;
}