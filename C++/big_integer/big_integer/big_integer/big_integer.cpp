#include "big_integer.h"

#include <iostream>
#include <cstring>
#include <stdexcept>
#define max(a, b) ((a>b)?a:b)
#define min(a, b) ((a<b)?a:b)

big_integer::big_integer()
{
    //std::cout<<" bigint() "<<std::endl;
    d.clear();
    d.push_back(0);
    sgn = '+';
}

big_integer::big_integer(big_integer const& other)
{
    //std::cout<<" bigint "<< other.d[0] <<std::endl;
    d.clear();
    d = other.d;
    sgn = other.sgn;
}

big_integer::big_integer(int a)
{
    //std::cout<<" int "<< a <<std::endl;
    d.clear();

    if (a < 0) {
        sgn = '-';
    } else {
        sgn = '+';
    }
    if (a == 0) {
        d.push_back(0);
    }
    if (a < 0) a = -a;
    while (a > 0) {
        d.push_back(a % BASE);
        a /= BASE;
    }
}

big_integer::big_integer(std::string const& str)
{
    //std::cout<<"str "<< str <<std::endl;
    int sign = 0;
    if (str[0] == '-')
        sign = 1;
    d.clear();
    sgn = '+';
    for (int i = sign; i < (int)str.size(); i++) {
        int cur = (str[i] - '0');
        *this = (*this) * big_integer(10) + big_integer(cur);
    }
    (*this).sgn = '+';
    if ((str[0] == '-') && ((*this) != 0)) {
        (*this).sgn = '-';
    }
        //throw std::runtime_error("invalid string");
}

big_integer::~big_integer()
{
    //std::cout<<" ~ "<< d[0] <<std::endl;
    d.clear();
}

big_integer& big_integer::operator=(big_integer const& other)
{
    //std::cout<<" = "<< other.d[0] <<std::endl;
    d = other.d;
    sgn = other.sgn;
    return *this;
}

big_integer& big_integer::operator+=(big_integer const& rhs)
{
    //std::cout<<" += "<< rhs.d[0] <<std::endl;

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

    d.resize(max(d.size(), rhs.d.size()));
    int carry = 0;
    for (int i = 0; i < d.size() || carry; i++) {
        if (i == d.size())
            d.push_back(0);
        int tmp;
        if (i >= rhs.d.size()) {
            tmp = 0;
        } else {
            tmp = rhs.d[i];
        }
        d[i] += tmp + carry;
        carry = d[i] >= BASE;
        if (carry) d[i] -= BASE;
    }
    while ((d.size() > 1) && (d[d.size() - 1] == 0))
        d.pop_back();
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
    d.resize(max(d.size(), rhs.d.size()));
    int carry = 0;
    for (int i = 0; i < rhs.d.size() || carry; i++) {
        if (i == d.size()) {
            d.push_back(0);
        }
        d[i] -= ((i < rhs.d.size())?rhs.d[i]:0) + carry;
        carry = d[i] < 0;
        if (carry) d[i] += BASE;
    }
    while ((d.size() > 1) && (d[d.size() - 1] == 0))
        d.pop_back();
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
    c.d.resize(max(c.d.size(), d.size() + rhs.d.size() + 2));
    for (int i = 0; i < d.size(); i++)
        for (int j = 0, carry = 0; j < rhs.d.size() || carry; j++) {
            long long cur = c.d[i + j] + d[i] * 1ll * (j < rhs.d.size() ? rhs.d[j] : 0) + carry;
            c.d[i + j] = int (cur % BASE);
            carry = int (cur / BASE);
        }

    while ((c.d.size() > 1) && (c.d[c.d.size() - 1] == 0))
        c.d.pop_back();
    *this = c;
    return *this;
}

big_integer abs(big_integer const& a) {
    big_integer b = a;
    b.sgn = '+';
    return b;
}

big_integer divide(big_integer a, int const& rhs)
{
    int carry = 0;
    for (int i = (int)a.d.size() - 1; i >= 0; i--) {
        long long cur = a.d[i] + carry * 1ll * BASE;
        a.d[i] = int (cur / rhs);
        carry = int (cur % rhs);
    }
    while (a.d.size() > 1 && a.d[a.d.size() - 1] == 0)
        a.d.pop_back();
    return a;
}


big_integer& big_integer::operator/=(big_integer const& rhs)
{
    int fsgn = sgn;
    big_integer v = rhs;
    (*this).sgn = '+';
    v.sgn = '+';
    if (*this < v) {
        *this = big_integer(0);
        return *this;
    }
    //начальная инициализация
    int n = v.d.size();
    if (n == 1) {
        *this = divide(*this, v.d[0]);
        if (fsgn != rhs.sgn)
        {
            *this = -(*this);
        }
        return *this;
    }
    int m = d.size() - v.d.size();
    big_integer q = big_integer(0);
    q.d.resize(m + 1);
    //Нормализация
    int dd = (BASE / (v.d[n - 1] + 1));
    *this = (*this) * dd;
    v = v * dd;
    if (d.size() == n + m)//аккуратная проверка на d==1
    {
        d.push_back(0);
    }
    //Цикл по j
    for (int j = m; j >= 0; j--)
    {

        long long cur = (long long)(d[j + n]) *
        (long long)(BASE) + d[j + n - 1];
        int tempq = (int)(cur / v.d[n - 1]);
        int tempr = (int)(cur % v.d[n - 1]);
        while ((tempr < BASE) &&
            (tempq == BASE || (long long)tempq * (long long)v.d[n - 2] >
                                    (long long)tempr  * (long long)BASE + d[j + n - 2]))
        {
            tempq--;
            tempr += v.d[n - 1];
        }
        //Умножить и вычесть
        big_integer tmp_u = big_integer(0);
        tmp_u.d.resize(n + 1);
        for (int i = j; i <= j + n; i++) {
            tmp_u.d[i - j] = d[i];
        }
        while (tmp_u.d.size() > 1 && tmp_u.d[tmp_u.d.size() - 1] == 0)
            tmp_u.d.pop_back();
        tmp_u -= tempq * v;

        q.d[j] = tempq;
        if (tmp_u < 0) {
            q.d[j]--;
            tmp_u = tmp_u + v;
        }
        for (int i = j; i <= j + n; i++) {
            if (i - j < tmp_u.d.size()) {
              d[i] = tmp_u.d[i - j];
            } else {
                d[i] = 0;
            }
        }
    }
    while (q.d.size() > 1 && q.d[q.d.size()- 1] == 0)
        q.d.pop_back();
    if (fsgn == rhs.sgn) {
        q.sgn = '+';
    } else {
        q.sgn = '-';
    }
    *this = q;
    return *this;
}


big_integer& big_integer::operator%=(big_integer const& rhs)
{
    *this = *this - rhs * (*this / rhs);
    return *this;
}

big_integer invert(big_integer a)
{
    for (int i = 0; i < a.d.size(); i++) {
        a.d[i] = BASE - a.d[i] - 1;
      //  //std::cout << "inv =" << BASE << " " <<a.d[i]<<std::endl;
    }
    return a;
}

big_integer& big_integer::operator&=(big_integer const& rhs)
{
    big_integer a = *this;
    big_integer b = rhs;

    a.d.resize(max(a.d.size(), b.d.size()));
    b.d.resize(max(a.d.size(), b.d.size()));
    if (a.sgn == '-')
    {
        a = invert(a) - 1;
    }
    if (b.sgn == '-')
    {
        b = invert(b) - 1;
    }

    for (int i = 0; i < a.d.size(); i++)
    {
        a.d[i] &= b.d[i];
    }
    while (a.d.size() > 1 && a.d[a.d.size() - 1] == 0) {
        a.d.pop_back();
    }
    if (a.sgn == '-' && b.sgn == '-') {
        a = invert(a) - 1;
    } else {
        a.sgn = '+';
    }
    if (a == 0)
    {
        a.sgn = '+';
        }
    *this = a;
    return *this;
}

big_integer& big_integer::operator|=(big_integer const& rhs)
{
    big_integer a = *this;
    big_integer b = rhs;

    a.d.resize(max(a.d.size(), b.d.size()));
    b.d.resize(max(a.d.size(), b.d.size()));
    if (a.sgn == '-')
    {
        a = invert(a) - 1;
    }
    if (b.sgn == '-')
    {
        b = invert(b) - 1;
    }

    for (int i = 0; i < a.d.size(); i++)
    {
        a.d[i] |= b.d[i];
    }

    while (a.d.size() > 1 && a.d[a.d.size() - 1] == 0) {
        a.d.pop_back();
    }
    if (a.sgn == '-' || b.sgn == '-') {
        a.sgn = '-';
        a = invert(a) - 1;
    } else {
        a.sgn = '+';
    }
    if (a == 0)
    {
        a.sgn = '+';
        }
    *this = a;
    return *this;
}

big_integer& big_integer::operator^=(big_integer const& rhs)
{
    big_integer res = (*this & (~rhs)) | ((~*this) & rhs);
    *this = res;
    return *this;
    big_integer a = *this;
    big_integer b = rhs;
    a.d.resize(max(a.d.size(), b.d.size()));
    b.d.resize(max(a.d.size(), b.d.size()));
    a.d.push_back(0);
    b.d.push_back(0);
    if (a.sgn == '-')
    {
        a = invert(a) - 1;
    }
    if (b.sgn == '-')
    {
        b = invert(b) - 1;
    }
    for (int i = 0; i < a.d.size() ; i++)
    {
        a.d[i] ^= b.d[i];
    }
    while (a.d.size() > 1 && a.d[a.d.size() - 1] == 0) {
        a.d.pop_back();
    }
    if ((a.sgn == '-' && b.sgn == '+') || (b.sgn == '-' && a.sgn == '+')) {
        a = invert(a) - 1;
    } else {
        a.sgn = '+';
    }
    if (a == 0)
    {
        a.sgn = '+';
        }
    *this = a;
    return *this;
}

big_integer& big_integer::operator<<=(int rhs)
{
    int shl = rhs / 30;
    d.resize(max(d.size(), d.size() + shl));
    for (int i = d.size() + shl - 1; i >= shl; i--) {
        d[i] = d[i - shl];
    }
    for (int i = 0; i < shl; i++)
        d[i] = 0;
    while ((d.size() > 1) && (d[d.size() - 1] == 0)) {
        d.pop_back();
    }
    *this = *this * (1 << (rhs % 30));
    if (abs(*this) == 0) {
        sgn = '+';
    }
    return *this;
}

big_integer& big_integer::operator>>=(int rhs)
{
    int shl = rhs / 30;
    for (int i = 0; i < d.size() - shl; i++) {
        d[i] = d[i + shl];
    }
    d.resize(min(d.size(), d.size() - shl));
    while ((d.size() > 1) && (d[d.size() - 1] == 0)) {
        d.pop_back();
    }
    if ((abs(*this) % (1 << (rhs % 30))) != 0 && sgn == '-')
    {
        if (sgn == '-')
        {
            *this -= (1 << (rhs % 30));
        } else
        {
            *this += (1 << (rhs % 30));
        }
    }
    *this = *this / (1 << (rhs % 30));

    if (abs(*this) == 0) {
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
    if (r == big_integer(0)) {
        return r;
    }
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
    if (a.d.size() != b.d.size()) {
        return false;
    }
    for (int i = 0; i < a.d.size(); i++) {
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
    if (a.d.size() < b.d.size()) {
        if (a.sgn == '+') {
            return true;
        }
        return false;
    }
    if (a.d.size() > b.d.size()) {
        if (a.sgn == '+') {
            return false;
        }
        return true;
    }
    int pos = a.d.size() - 1;
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
    //std::cout << "print str"<<std::endl;
    std::string s = "";
    big_integer cur = a;
    cur.sgn = '+';
    if (cur == 0) {
        s += '0';
    }
    while (cur > 0) {
        //std::cerr << "   " << (cur % big_integer(10)).d.small << ' ' << (cur / big_integer(10)).d[0] << std::endl;
        big_integer ch = cur % big_integer(10);
        //std::cerr << ch.d.size() << ' ' << ch.d[0] << std::endl;
        s += (ch.d[0] + '0');
        cur /= big_integer(10);
    }
    if (a.sgn == '-')
        s += a.sgn;
    std::string res = "";
    for (int i = s.size() - 1; i >= 0; i--) {
        res += s[i];
    }
    //reverse(s.begin(), s.end());
    return res;
}

std::ostream& operator<<(std::ostream& s, big_integer const& a)
{
    return s << to_string(a);
}
