package template.math;

import template.primitve.generated.LongList;
import template.primitve.generated.MultiWayIntegerStack;

public class Factorization {
    /**
     * factorize all number in [1, n], and only return their prime factors
     */
    public static MultiWayIntegerStack factorizeRangePrime(int n) {
        int size = 0;
        for (int i = 1; i <= n; i++) {
            size += n / i;
        }
        MultiWayIntegerStack stack = new MultiWayIntegerStack(n + 1, size);
        boolean[] isComp = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (isComp[i]) {
                continue;
            }
            for (int j = i; j <= n; j += i) {
                isComp[j] = true;
                stack.addLast(j, i);
            }
        }
        return stack;
    }

    /**
     * factorize all number in [1, n]
     */
    public static MultiWayIntegerStack factorizeRange(int n) {
        int size = 0;
        for (int i = 1; i <= n; i++) {
            size += n / i;
        }
        MultiWayIntegerStack stack = new MultiWayIntegerStack(n + 1, size);
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                stack.addLast(j, i);
            }
        }
        return stack;
    }

    /**
     * Find all factors of x, and return them unordered.
     */
    public static LongList factorizeNumber(long x) {
        LongList ans = new LongList();
        for (long i = 1; i * i <= x; i++) {
            if (x % i != 0) {
                continue;
            }
            ans.add(i);
            if (i * i != x) {
                ans.add(x / i);
            }
        }
        return ans;
    }

    /**
     * Find all prime factors of x, and return them ordered.
     */
    public static LongList factorizeNumberPrime(long x) {
        LongList ans = new LongList();
        for (long i = 2; i * i <= x; i++) {
            if (x % i != 0) {
                continue;
            }
            ans.add(i);
            while (x % i == 0) {
                x /= i;
            }
        }
        if (x > 1) {
            ans.add(x);
        }
        return ans;
    }
}