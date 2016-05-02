//
// Created by Daniyar Itegulov on 29/03/16.
//

#ifndef COMPUTATION_METHODS_HW2_NG_ITERATIVE_SOLVER_H
#define COMPUTATION_METHODS_HW2_NG_ITERATIVE_SOLVER_H

#include <system_solver.h>
#include <vector>

template <class T>
class iterative_solver : public system_solver<T> {

    static const size_t ITER_DEFAULT = 100;

public:
    virtual matrix<T> solve(const matrix<T> &coefficients, const matrix<T> &free,
                            const matrix<T> &initial_approx, size_t iterations) = 0;

    matrix<T> solve(const matrix<T> &coefficients, const matrix<T> &free) override {
        return solve(coefficients,
                     free,
                     matrix<T>(std::vector<std::vector<T>>(coefficients.rows, std::vector<T>(1))),
                     ITER_DEFAULT);
    }
};

#endif //COMPUTATION_METHODS_HW2_NG_ITERATIVE_SOLVER_H
