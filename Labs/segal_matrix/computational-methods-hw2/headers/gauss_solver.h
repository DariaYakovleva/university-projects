//
// Created by ignat on 28.03.16.
//

#ifndef COMPUTATION_METHODS_HW2_GAUSS_SOLVER_H
#define COMPUTATION_METHODS_HW2_GAUSS_SOLVER_H


#include "system_solver.h"
#include <cassert>

template <class T>
struct gauss_solver : public system_solver<T> {
    matrix<T> solve(const matrix<T> &coefficients, const matrix<T> &free) override {
        assert(coefficients.cols == coefficients.rows);
        assert(free.rows == coefficients.rows);
        assert(free.cols == 1);
        std::vector<std::vector<T>> data = coefficients.data;
        std::vector<std::vector<T>> free_data = free.data;

        for (size_t col = 0; col < coefficients.cols; ++col) {
            size_t other_row = col;
            for (size_t nrow = col + 1; nrow < coefficients.rows; ++nrow)
                if (abs(data[nrow][col]) > abs(data[other_row][col]))
                    other_row = nrow;
            swap(data[col], data[other_row]);
            swap(free_data[col], free_data[other_row]);

            free_data[col] = free_data[col] / data[col][col];
            data[col] = data[col] / data[col][col];

            for (size_t row = 0; row < coefficients.rows; ++row) {
                if (col != row) {
                    T coeff = data[row][col] / data[col][col];
                    data[row] -= data[col] * coeff;
                    free_data[row] -= free_data[col] * coeff;
                }
            }
        }

        return free_data;
    }
};

#endif //COMPUTATION_METHODS_HW2_GAUSS_SOLVER_H
