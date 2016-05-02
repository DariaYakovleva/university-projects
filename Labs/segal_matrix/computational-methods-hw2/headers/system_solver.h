//
// Created by Ignat Loskutov on 28/03/16.
//

#ifndef COMPUTATION_METHODS_HW2_SYSTEM_SOLVER_H
#define COMPUTATION_METHODS_HW2_SYSTEM_SOLVER_H

#include "matrix.h"

template <class T>
struct system_solver {
    virtual matrix<T> solve(const matrix<T> &coefficients, const matrix<T> &free) = 0;
};


#endif //COMPUTATION_METHODS_HW2_SYSTEM_SOLVER_H
