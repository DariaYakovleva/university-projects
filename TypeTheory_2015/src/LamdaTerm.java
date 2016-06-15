/**
 * Created by Daria on 23.04.2016.
 */
abstract class LambdaTerm implements Expression {
    static final int N = 100000010;
    static long MOD = 700000000 + 7;
    static long primeNumber = 31;

    long power(long n) {
        long a = primeNumber;
        long res = 1;
        a %= MOD;
        while (n > 0) {
            if (n % 2 != 0)
                res = (res * a) % MOD;
            a = (a * a) % MOD;
            n >>= 1;
        }
        return res;
    }
    long getHash(String s) {
        long res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = (res + s.charAt(i) * power(i)) % MOD;
        }
        return res;
    }
}
