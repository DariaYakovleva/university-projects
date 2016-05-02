#include "big_integer.h"

#include <cstring>
#include <stdexcept>
#define max(a, b) ((a>b)?a:b)


big_integer::big_integer()
{
    memset(d, 0, N * sizeof(int));
    size = 1;
    sgn = '+';
}

big_integer::big_integer(big_integer const& other)
{
    memset(d, 0, N * sizeof(int));
    size = other.size;
    for (int i = 0; i < other.size; i++)
        d[i] = other.d[i];
    sgn = other.sgn;
}

big_integer::big_integer(int a)
{ 
    memset(d, 0, N * sizeof(int));
    size = 0;
    if (a < 0) {
        sgn = '-';
    } else {
        sgn = '+';
    }
    if (a == 0) {
        size = 1;
    }
    a = abs(a);
    while (a > 0) {
        d[size] = a % BASE;
        a /= BASE;
        size++;
    }
}

big_integer::big_integer(std::string const& str)
{
    big_integer();
    int sign = 0;
    if (str[0] == '-')
        sign = 1;
    big_integer res = big_integer(0);
    for (int i = sign; i < str.size(); i++) {
        int cur = (str[i] - '0');
        res = res * big_integer(10) + big_integer(cur);
    }
    if (str[0] == '-') {
        res.sgn = '-';
    } else {
        res.sgn = '+';
    }
    *this = res;
        //throw std::runtime_error("invalid string");
}

big_integer::~big_integer()
{
    big_integer(-1);
}

big_integer& big_integer::operator=(big_integer const& other)
{
    memset(d, 0, N * sizeof(int));
    size = other.size;
    for (int i = 0; i < other.size; i++)
        d[i] = other.d[i];
    sgn = other.sgn;
    return *this;
}

big_integer& big_integer::operator+=(big_integer const& rhs)
{
    if (sgn != rhs.sgn) {
        //????
       if (rhs.sgn == '-')
       {
           big_integer tmp = rhs;
           tmp.sgn = '+';
           *this -= tmp;
           return *this;
       } else {
           big_integer tmp = *this;
           tmp.sgn = '+';
           *this = rhs;
           *this -= tmp;
           return *this;
       }
    }
    int carry = 0;
    for (int i = 0; i < max(size, rhs.size) || carry; i++) {
        if (i == size)
            size++;
        d[i] += rhs.d[i] + carry;
        carry = d[i] >= BASE;
        if (carry) d[i] -= BASE;
    }
    return *this;
}

big_integer& big_integer::operator-=(big_integer const& rhs)
{
    if (sgn != rhs.sgn) {
        big_integer tmp = rhs;
        tmp.sgn = sgn;
        *this += tmp;
        return *this;
    }
    if (sgn == '-') {
        big_integer tmp = *this;
        tmp.sgn = '+';
        *this = rhs;
        sgn = '+';
        *this -= tmp;
        return *this;
    }
    if (rhs > *this) {
        big_integer tmp = *this;
        *this = rhs;
        *this -= tmp;
        sgn = '-';
        return *this;
    }
    int carry = 0;
    for (int i = 0; i < rhs.size || carry; i++) {
        d[i]  -= rhs.d[i] + carry;
        carry = d[i] < 0;
        if (carry) d[i] += BASE;
    }
    while ((size > 1) && (d[size - 1] == 0))
        size--;
    return *this;
}

big_integer& big_integer::operator*=(big_integer const& rhs)
{
    big_integer c = big_integer();
    if (sgn == rhs.sgn) {
        c.sgn = '+';
    } else {
        c.sgn = '-';
    }
    for (int i = 0; i < size; i++)
        for (int j = 0, carry = 0; j < rhs.size || carry; j++) {
            long long cur = c.d[i + j] + d[i] * 1ll * (j < rhs.size ? rhs.d[j] : 0) + carry;
            c.d[i + j] = int (cur % BASE);
            carry = int (cur / BASE);
        }
    c.size = size + rhs.size + 1;
    while ((c.size > 1) && (c.d[c.size - 1] == 0))
        c.size--;
    *this = c;
    return *this;
}

big_integer abs(big_integer const& a) {
    big_integer b = a;
    b.sgn = '+';
    return b;
}

big_integer& big_integer::operator/=(big_integer const& rhs)
{
    big_integer res = big_integer();
    if (sgn == rhs.sgn) {
        res.sgn = '+';
    } else {
        res.sgn = '-';
    }
    big_integer curValue = big_integer(0);
    for (int i = size - 1; i >= 0; i--)
    {
        for (int j = curValue.size; j >= 1; j--)
            curValue.d[j] = curValue.d[j - 1];
        if (curValue.d[curValue.size])
            curValue.size++;
        curValue.d[0] = d[i];
        // подбираем максимальное число x, такое что b * x <= curValue
        int x = 0;
        int l = 0, r = BASE;
        while (l <= r)
        {
            int m = (l + r) / 2;
            big_integer cur = abs(rhs) * big_integer(m);
            if (cur <= curValue)
            {
                x = m;
                l = m + 1;
            }
            else
                r = m - 1;
        }
        res.d[i] = x;
        curValue = curValue - abs(rhs) * big_integer(x);
    }
    int pos = size;
    while (pos > 0 && !res.d[pos])
        pos--;
    res.size = pos + 1;
    *this = big_integer(res);
    return *this;
}

big_integer& big_integer::operator%=(big_integer const& rhs)
{
    big_integer res = big_integer();
    big_integer curValue = big_integer();
    for (int i = size - 1; i >= 0; i--)
    {
        for (int j = curValue.size; j >= 1; j--)
            curValue.d[j] = curValue.d[j - 1];
        if (curValue.d[curValue.size])
            curValue.size++;
        curValue.d[0] = d[i];
        // подбираем максимальное число x, такое что b * x <= curValue
        int x = 0;
        int l = 0, r = BASE - 1;
        while (l <= r)
        {
            int m = (l + r) / 2;
            big_integer cur = abs(rhs) * big_integer(m);
            if (cur <= curValue)
            {
                x = m;
                l = m + 1;
            }
            else
                r = m - 1;
        }
        res.d[i] = x;
        curValue = curValue - abs(rhs) * big_integer(x);
    }
    int pos = curValue.size;
    while (pos > 0 && !curValue.d[pos])
        pos--;
    curValue.size = pos + 1;
    if (curValue == 0) {
        curValue.sgn = '+';
    } else {
    if (sgn == rhs.sgn) {
        curValue.sgn = sgn;
    } else {
        if (curValue != 0) {
            big_integer tmp = rhs;
            tmp.sgn = '+';
            curValue = tmp - curValue;
            if (rhs.sgn == '-') {
                curValue.sgn = '-';
            } else {
                curValue.sgn = '+';
            }
        }
    }
    }
    *this = curValue;
    return *this;
}

big_integer& big_integer::operator&=(big_integer const& rhs)
{
    for (int i = 0; i < max(size, rhs.size); i++)
    {
        d[i] &= rhs.d[i];
    }
    if (abs(*this) == 0) {
        sgn = '+';
    }
    return *this;
}

big_integer& big_integer::operator|=(big_integer const& rhs)
{
    for (int i = 0; i < max(size, rhs.size); i++)
    {
        d[i] |= rhs.d[i];
    }
    if (abs(*this) == 0) {
        sgn = '+';
    }
    return *this;
}

big_integer& big_integer::operator^=(big_integer const& rhs)
{
    for (int i = 0; i < max(size, rhs.size); i++)
    {
        d[i] ^= rhs.d[i];
    }
    if (abs(*this) == 0) {
        sgn = '+';
    }
    return *this;
    return *this;
}

big_integer& big_integer::operator<<=(int rhs)
{
    for (int i = size + rhs - 1; i >= rhs; i--) {
        d[i] = d[i - rhs];
    }
    for (int i = 0; i < rhs; i++)
        d[i] = 0;
    if (*this == 0) {
        sgn = '+';
    }
    return *this;
}

big_integer& big_integer::operator>>=(int rhs)
{
    for (int i = 0; i < size; i++) {
        d[i] = d[i + rhs];
    }
    if (*this == 0) {
        sgn = '+';
    }
    return *this;
}

big_integer big_integer::operator+() const
{
    return *this;
}

big_integer big_integer::operator-() const
{
    big_integer r = *this;
    if (r.sgn == '+') {
        r.sgn = '-';
    } else {
        r.sgn = '+';
    }
    return r;
}

big_integer big_integer::operator~() const
{
    big_integer r;
    r = *this + 1;
    r = -r;
    return r;
}

big_integer& big_integer::operator++()
{
    *this += big_integer(1);
    return *this;
}

big_integer big_integer::operator++(int)
{
    big_integer r = *this;
    ++*this;
    return r;
}

big_integer& big_integer::operator--()
{
    *this -= big_integer(1);
    return *this;
}

big_integer big_integer::operator--(int)
{
    big_integer r = *this;
    --*this;
    return r;
}

big_integer operator+(big_integer a, big_integer const& b)
{
    return a += b;
}

big_integer operator-(big_integer a, big_integer const& b)
{
    return a -= b;
}

big_integer operator*(big_integer a, big_integer const& b)
{
    return a *= b;
}

big_integer operator/(big_integer a, big_integer const& b)
{
    return a /= b;
}

big_integer operator%(big_integer a, big_integer const& b)
{
    return a %= b;
}

big_integer operator&(big_integer a, big_integer const& b)
{
    return a &= b;
}

big_integer operator|(big_integer a, big_integer const& b)
{
    return a |= b;
}

big_integer operator^(big_integer a, big_integer const& b)
{
    return a ^= b;
}

big_integer operator<<(big_integer a, int b)
{
    return a <<= b;
}

big_integer operator>>(big_integer a, int b)
{
    return a >>= b;
}

bool operator==(big_integer const& a, big_integer const& b)
{
    if (a.sgn != b.sgn) {
        return false;
    }
    for (int i = 0; i < max(a.size, b.size); i++) {
        if (a.d[i] != b.d[i])
            return false;
    }
    return true;
}

bool operator!=(big_integer const& a, big_integer const& b)
{
    return !(a == b);
}

bool operator<(big_integer const& a, big_integer const& b)
{
    if ((a.sgn == '-') && (b.sgn == '+')) {
        return true;
    }
    if ((a.sgn == '+') && (b.sgn == '-')) {
        return false;
    }
    if (a == b) {
        return false;
    }
    if (a.size < b.size) {
        if (a.sgn == '+') {
            return true;
        }
        return false;
    }
    if (a.size > b.size) {
        if (a.sgn == '+') {
            return false;
        }
        return true;
    }
    int pos = a.size - 1;
    while (a.d[pos] == b.d[pos])
        pos--;
    if (a.d[pos] < b.d[pos]) {
        if (a.sgn == '+') {
            return true;
        }
        return false;
    }
    return false;
}

bool operator>(big_integer const& a, big_integer const& b)
{
    return !((a < b) || (a == b));
}

bool operator<=(big_integer const& a, big_integer const& b)
{
    return !(a > b);
}

bool operator>=(big_integer const& a, big_integer const& b)
{
    return !(a < b);
}

std::string to_string(big_integer const& a)
{
    std::string s = "";
    big_integer cur = a;
    cur.sgn = '+';
    if (cur == 0) {
        s += '0';
    }
    while (cur > 0) {
        big_integer ch = cur % big_integer(10);
        s += (ch.d[0] + '0');
        cur /= big_integer(10);
    }
    if (a.sgn == '-')
        s += a.sgn;
    reverse(s.begin(), s.end());
    return s;
}

std::ostream& operator<<(std::ostream& s, big_integer const& a)
{
    return s << to_string(a);
}
