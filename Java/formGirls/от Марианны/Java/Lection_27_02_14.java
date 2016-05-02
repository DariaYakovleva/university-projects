/**
 * Программирование по контракту.
 * Тройки Хоара(доказательства).
 */
 
// Pre: b >= 0
// Post: result == a^b
long power(long a, long b) {}

// Pre: x >= 0
// Post: r^2 = x, r >= 0
double sqrt(double x) {}

/**
 * Проблемы:
 * Иногда доказательство сложнее самой программы.
 * (Теорема Райса) Не существует алгоритма, который для любой программы сможет доказать какое-то нетривиальное свойство.
 */

// Pre: --
// Post: r.length == a.length && количество вхождений одинаково
//       forall i < j, r[i] <= r[j]
int[] sort(int[] a) {}

// assert (condition), java -ea

// Тройки Хоара
// <P S, Q> - блок выполнит постусловие при выполнении предусловия

// <P1, S1, Q1> Q1 => P2 <P2, S2, Q2> -- Последовательно

// <P, S, Q> = if (c) { <P1, S1, Q1> } else { <P2, S2, Q2 } -- Условие
// Q1 => Q | => Q1 || Q2 => Q
// Q2 => Q |

// P && C => P1
// P && !C => P2

// <P, S, Q> == while (c) <P', S', Q'>
// 1) P && C => P'
// 2) Q' && C => P'
// 3) (P || Q') && !C => Q
// 4) (P || Q') && C => P'
// I = (P || Q') - инвариант цикла

// I && C => P'
// I && !C => Q

// Pre: b >= 0
// Post: result == a^b
long power(long a, long b) {
    // b' >= 0
    long r = 1;
    // b' >= 0 && r == 1

    // inv: r * a ^ b == a' ^ b'
    while (b != 0) {
	// r * a ^ b == a'^b', b > 0
	if (b % 2 == 1) {
	    // r * a ^ b == a'^b', b - нечетное 
	    r *= a;
	    b--;
	    // r * a ^ b == a'^b', b - четное 
	} else {
	    // r * a ^ b == a'^b', b - четное 
	}
	// r * a ^ b == a'^b', b - четное 
	b /= 2;
	a *= a;
	// r * a ^ b == a'^b'
    }
    // r == a'^b'

    // r == a'^b'
    return r;
}


// C++
// pre: b >= 0
// inv: a - сохраняется, a >= 0
int increment(int b) {
    static int a = 0;
    a += b;
    return a;
}
