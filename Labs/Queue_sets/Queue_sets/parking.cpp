//
//  parking.cpp
//  Queue_sets
//
//  Created by Дарья Яковлева on 22.03.14.
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

int n, m;

vertex *root;


vertex* next(vertex* x)
{
    if (x->right != NULL)
    {
        x =  x->right;
        while (x->left != NULL)
            x = x->left;
        return x;
    }
    while (x->parent != NULL && x == x->parent->right)
        x = x->parent;
    return x->parent;
}

vertex* search(vertex* x, int key)
{
    if (x->value == key)
        return x;
    if (x->value > key)
    {
        if (x->left != NULL)
            return search(x->left, key);
        else
            return x;
    }
    if (x->right != NULL)
        return search(x->right, key);
    else
        return next(x);
}

vertex* newv(int a)
{
    vertex *v = new vertex;
        v->value = a;
        v->left = NULL;
        v->right = NULL;
        v->parent = NULL;
    return v;
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
            return;
        }
        else
        {
            insert(x->right, a);
        }
    }
}

void deletev(vertex *v)
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
                if (p->right == v)
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
                if (p->right == v)
                    p->right = v->left;
                v->left->parent = p; 
            }
        }
        else
        {
            vertex *nv = next(v);
            v->value = nv->value;
            if (v->parent == NULL)
                root = v;
            if (nv->parent == NULL)
            {
                nv = nv->right;
                if (nv != NULL)
                    nv->parent= NULL;
            }
            else
            {
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
    
}


void make_bst()
{
    root = new vertex;
    root->value = 0;
    root->left = NULL;
    root->right = NULL;
    root->parent = NULL;
    for (int i = 1; i < n; i++)
        insert(root, i);
    
}

int main() 
{
    freopen("parking.in", "r", stdin);
    freopen("parking.out", "w", stdout);
    cin >> n >> m;
    make_bst();
    for (int i = 0; i < m; i++)
    {
        string s;
        
        int a;
        cin >> s >> a;
        a--;
        if (s[1] == 'n')
        {
            vertex* v = search(root, a);
            if (v == NULL)
                v = search(root, 0);
            cout << v->value + 1 << endl;
            deletev(v);
        }
        else
        {
            if (root == NULL)
                root = newv(a);
            else
                insert(root, a);
        }
    }
    
    return 0;
}