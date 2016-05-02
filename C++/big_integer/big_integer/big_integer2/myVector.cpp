//
//  main.cpp
//  big_integer
//
//  Created by Дарья Яковлева on 02.05.14.
//  Copyright (c) 2014 __MyCompanyName__. All rights reserved.
//
#ifndef MY_VECTOR
#define MY_VECTOR
#include <iostream>
#include <string>
#include <cstdio>
#include <vector>
#include <cassert>


const int M = 16;


struct myVector {
    int small;
    int* a;
    std::vector<int>* v;

    myVector() {
        std::cout << "init () " << std::endl;
        small = 1;
        a = new int(0);
        //v = new std::vector<int>;
    }

    myVector(int x) {
        std::cout << "init x" << x << std::endl;
        small = 2;
        a = new int(x);
    }

    ~myVector() {
        std::cout << "~ " << std::endl;
        if (!small) {
            (*a)--;
            if (*a == 0) {
                delete v;
                delete a;
            }
            return;
        }
        delete a;
    }

    void make_copy() {
        std::cout << "make_copy " << std::endl;
        if (small)
            return;
        if ((*a) > 1) {
            std::vector<int> * nv = new std::vector<int>(*v);
            (*a)--;
            if (*a == 0) {
                delete a;
                delete v;
            }
            a = new int(1);
            std::cout << "????????????? " << (*v).size() << " " <<(*v)[0]<< std::endl;
            v = nv;
        }
    }

    int size() const {
        std::cout << "size " << std::endl;
        if (small) {
            if (small > 1)
                return 1;
            return 0;
        }
        return (*v).size();
    }

    int& operator[](int pos) {
        std::cout << "[] " << pos <<" "<<std::endl;
        assert(pos < size());
        if (small) {
            return (*a);
        }
        make_copy();
        return (*v)[pos];
    }

    int const& operator[](int pos) const {
        std::cout << "ps size " <<pos <<" "<< size() << std::endl;
        assert(pos < size());
        std::cout << "[] const " <<pos << std::endl;
        if (small) {
            std::cout << "[] const a" <<pos << std::endl;
            return (*a);
        }
        return (*v)[pos];
    }

    myVector& operator=(myVector const& other) {
        std::cout << "= " << std::endl;
        make_copy();
        if (other.small) {
            if (small) {
                small = other.small;
                *a = *other.a;
            }
            else {
                v->clear();
                if (other.small > 1) {
                    v->push_back(*other.a);
                }
            }
        }
        else {
            if (!small) {
                --(*a);
                if (*a == 0) {
                    delete v;
                    delete a;
                }
            }
            else {
                small = 0;
                delete a;
            }
            v = other.v;
            a = other.a;
            ++(*a);
        }
        return *this;
    }
    void clear() {
        std::cout << "clear " << std::endl;
        make_copy();
        if (small) {
            small = 1;
            (*a) = 0;
        } else {
            delete v;
            delete a;
            a = new int(0);
            small = 1;
        }
    }

    void push_back(int x) {
        std::cout << "push_Back " <<x<< std::endl;
        if (small) {
            if (small > 1) {
                v = new std::vector<int>;
                (*v).push_back(*a);
                (*a) = 1;
                small = 0;
            } else {
                small++;
                (*a) = x;
            }
            return;
        }
        make_copy();
        (*v).push_back(x);
    }
    void pop_back() {
        assert(size() > 0);
        std::cout << "pop_back " << std::endl;
        if (small) {
            (*a) = 0;
            small = 1;
            return;
        }
        make_copy();
        (*v).pop_back();
    }

    void resize(int x) {
        std::cout << "resize " << x<<std::endl;
        if (small) {
            if (x == 0) {
                (*a) = 0;
                small = 1;
            }
            if (x == 1) {
                if (small <= 1) {
                    small = 2;
                    (*a) = 0;
                }
            }
            if (x > 1) {
                v = new std::vector<int>;
                if (small > 1)
                    v->push_back(*a);
                small = 0;
                v->resize(x);
                *a = 1;
            }
            return;
        }
        make_copy();
        (*v).resize(x);
    }
};
#endif
