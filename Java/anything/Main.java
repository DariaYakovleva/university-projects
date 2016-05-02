//package com.company;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        int d = scanner.nextInt();
        int m = gcd(abs(c),abs(d));
        c = c / m;
        d = d / m;
        System.out.print(c+" "+d);
    }

    public static int gcd(int a, int b) {
        while(true)
        {
            if(a==0)
            {
                return b;
            }
            if(b==0)
            {
                return a;
            }
            if (a>b)
            {
                a=a-b;
                if(a==0||a==b)
                {
                    return a;
                }
                else
                {
                    return gcd(a, b);
                }
            }
            if (a<b)
            {
                b=b-a;
                if(a==0||a==b)
                {
                    return a;
                }
                else
                {
                    return gcd(a, b);
                }
            }
        }
    }
}
