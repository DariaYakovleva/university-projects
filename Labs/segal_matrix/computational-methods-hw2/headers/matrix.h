//
// Created by Ignat Loskutov on 28/03/16.
//

#ifndef COMPUTATION_METHODS_HW2_MATRIX_H
#define COMPUTATION_METHODS_HW2_MATRIX_H

#include <cstddef>
#include <vector>
#include <ostream>
#include <cassert>

/// A simple immutable matrix class. T is considered a number, so it must support default construction (must be zero)
/// and +=, -= ans * operators. Supports construction in the following way:
/// matrix<int> m ({{1, 2, 3},
///                 {4, 5, 6},
///                 {7, 8, 9}});
template <class T>
struct matrix {
    /// The constructor. `data` must represent a matrix, i.e. data[i].size == data[j].size forall i, j.
    /// If it doesn't, the behaviour is undefined.
    matrix(std::vector<std::vector<T>> data)
        : rows{ data.size() }
        , cols{ data.at(0).size() }
        , data{ std::move(data) }
    {}

    const std::vector<T>& operator[](size_t index) const {
        return data[index];
    }

    matrix<T> operator+(const matrix<T> &rhs) const {
        assert(rows == rhs.rows);
        assert(cols == rhs.cols);
        std::vector<std::vector<T>> ans = data;
        for (size_t i = 0; i < rows; ++i)
            for (size_t j = 0; j < cols; ++j)
                ans[i][j] += rhs[i][j];
        return ans;
    }

    matrix<T> operator-(const matrix<T> &rhs) const {
        assert(rows == rhs.rows);
        assert(cols == rhs.cols);
        std::vector<std::vector<T>> ans = data;
        for (size_t i = 0; i < rows; ++i)
            for (size_t j = 0; j < cols; ++j)
                ans[i][j] -= rhs[i][j];
        return ans;
    }

    matrix<T> operator*(const matrix<T> &rhs) const {
        assert(cols == rhs.rows);
        std::vector<std::vector<T>> ans(rows, std::vector<T>(rhs.cols));
        for (size_t i = 0; i < rows; ++i)
            for (size_t j = 0; j < rhs.cols; ++j)
                for (size_t k = 0; k < cols; ++k)
                    ans[i][j] += data[i][k] * rhs[k][j];
        return ans;
    }

    matrix<T> operator*(const T& num) const {
        std::vector<std::vector<T>> ans(rows, std::vector<T>(cols));
        for (size_t i = 0; i < rows; i++) {
            for (size_t j = 0; j < cols; j++) {
                ans[i][j] = data[i][j] * num;
            }
        }
        return ans;
    }

    matrix<T> transpose() const {
        std::vector<std::vector<T>> ans(cols, std::vector<T>(rows));
        for (size_t i = 0; i < cols; i++) {
            for (size_t j = 0; j < rows; j++) {
                ans[i][j] = data[j][i];
            }
        }
        return ans;
    }

    T getAsT() const {
        assert(rows == 1);
        assert(cols == 1);
        return data[0][0];
    }


    const size_t rows;
    const size_t cols;
    const std::vector<std::vector<T>> data;
};

template <class T>
std::ostream & operator<<(std::ostream &os, const matrix<T> &m) {
    for (size_t i = 0; i < m.rows; ++i) {
        os << " ["[i == 0] << ' ';
        for (size_t j = 0; j < m.cols; ++j) {
            os << m.data[i][j] << ' ';
        }
        os << "\n]"[i == m.rows - 1];
    }
    return os;
}

template <class T>
std::vector<T> operator/(const std::vector<T> &lhs, const T &rhs) {
    std::vector<T> ans = lhs;
    for (T& t : ans)
        t /= rhs;
    return ans;
}

template <class T>
std::vector<T> operator*(const std::vector<T> &lhs, const T &rhs) {
    std::vector<T> ans = lhs;
    for (T& t : ans)
        t *= rhs;
    return ans;
}

template <class T>
std::vector<T>& operator-=(std::vector<T> &lhs, const std::vector<T> &rhs) {
    assert(lhs.size() == rhs.size());
    for (size_t i = 0; i < lhs.size(); ++i)
        lhs[i] -= rhs[i];
    return lhs;
}

#endif //COMPUTATION_METHODS_HW2_MATRIX_H
