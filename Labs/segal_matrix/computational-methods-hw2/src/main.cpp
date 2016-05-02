//
// Created by Ignat Loskutov on 28/03/16.
//
#include <iostream>
#include <vector>
#include "matrix.h"
#include "gauss_solver.h"
#include <gradient_descent_solver.h>
#include <memory>

using namespace std;

int main() {
    unique_ptr<iterative_solver<double>> solver2 = make_unique<gradient_descent_solver<double>>();
    matrix<double> m2({
                              {1, 1},
                              {1, 3}
                      });
    matrix<double> fr2({
                               {-3},
                               {-7}
                      });
    matrix<double> init({
                                {1},
                                {1}
                        });
    cout << solver2->solve(m2, fr2, init, 3);
}