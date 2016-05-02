public class BinarySearch {                               
    //Pre: a - sorted && x <= a[i] && l <= i < r
    //Post: (result = min i && x <= a[i]) || (result = n) 
    static int SearchR (int[] a, int x, int l, int r) {
        // (l < 0 || a[l] < x) && (r > n || a[r] >= x)
        if (r - l <= 1) {
            // length((l, r]) = 1 && (l < 0 || a[l] < x) && (r > n || a[r] >= x)
            return r;
            //  result = r
        } else {           
            // length((l, r]) > 1 && x (l < 0 || a[l] < x) && (r > n || a[r] >= x)
            int m = (l + r) / 2;
            // length((l, r]) > 1 && (l < 0 || a[l] < x) && (r > n || a[r] >= x)
            // m = (l + r) / 2 && m in (l, r)
            if (a[m] < x) {
                // (l < 0 || a[l] < x) && (r > n || a[r] >= x)                    
                // (a[m] < x) &&  if (i < m) {a[i] < x} && (r > n || a[r] >= x)
                // (a[m] < x) && (r > n || a[r] >= x)
                return SearchR(a, x, m, r);
                // result = min i && x = a[i] && m < i <= r 
            } else {
                // (l < 0 || a[l] < x) && (r > n || a[r] >= x)
                // a[m] >= x &&  if (i > m) {a[i] >= x} && (r > n || a[r] >= x)
                // (a[m] >= x) && (l < 0 || a[l] >= x)
                return SearchR(a, x, l, m);
                // result = min i && x = a[i] && l < i <= m
            }
        } 
    }    

    
    //Pre: a - sorted && x <= a[i] && 0 <= i < n
    //Post: (result = min i && x <= a[i]) || (result = n) 
    static int SearchI (int[] a, int x, int n) {
        int m;
        int l = -1;
        int r = n;
        // (l == -1) && (r == n)
        // inv: (l < 0 || a[l] < x) && (r == n || a[r] >= x)
        while (r - l > 1) {
            // (l < 0 || a[l] < x) && (r == n || a[r] >= x)
            m = (l + r) / 2;
            //  0 <= m < n
            if (a[m] < x) {
                // a[m] < x && if (i < m) {a[i] < x} && (l < 0 || a[l] < x) && (r > n || a[r] >= x)
                // (a[m] < x) && (r > n || a[r] >= x)
                l = m;
                // l == m && (a[l] < x) && (r > n || a[r] >= x) 
            } else {
                // a[m] >= x && if (i > m) {a[i] >= x} && (l < 0 || a[l] < x) && (r > n || a[r] >= x)
                // (l < 0 || a[l] < x) && (a[m] >= x)
                r = m;
                // r == m && (l < 0 || a[l] < x) && (a[r] >= x) 
            }
            // (l < 0 || a[l] < x) && (r == n || a[r] >= x)
        }
        // (l < 0 || a[l] < x) && (r == n || a[r] >= x) && length((l, r]) = 1
        // (r == n || (a[r] >= x && r - min))
        return r;
        // result = r
    }

    // Pre:  
    // Post: output: place of x and number of x
    public static void main(String[] args) {
        int n = 0;       
        int x = Integer.parseInt(args[0]);
        int a [] = new int[args.length - 1];
        //inv: if (k < i) {a[k] - read}
        for (int i = 1; i < args.length; i++) {
            a[n] = Integer.parseInt(args[i]); 
            n++;                                                
        }

        // int[] a - sorted && a.length == n
        int t = SearchI(a, x, n);
        // t == place of x
        System.out.print(t);
        System.out.print(" ");
        if ((t == n) || (a[t] == a[n - 1])) {                     
            // x is not in a || elements till the end of a == x
            System.out.print(n - t);
        } else {
            // x in a && !(elements till the end of a == x)
            System.out.print(SearchI(a, x + 1, n) - t);
        }
	System.out.println();

        // System.out.println(SearchR(a, x, -1, n));
        //System.out.println(SearchI(a, x, n));
                                  
    }
}