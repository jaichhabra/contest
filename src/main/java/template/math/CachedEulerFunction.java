package template.math;

import template.primitve.generated.datastructure.IntegerHashMap;

public class CachedEulerFunction {
    private static int boundary = 1 << 16;
    private static MultiplicativeFunctionSieve sieve = new MultiplicativeFunctionSieve(boundary, false, true, false);
    private static IntegerHashMap map = new IntegerHashMap(64, true);
//    private static PollardRho rho = new PollardRho();

    public static int get(int x) {
        return get(x, 2);
    }

    public static int get(int x, int begin) {
        if (x <= boundary) {
            return sieve.euler[x];
        }
        int ans = map.getOrDefault(x, -1);
        if (ans == -1) {
            int factor = findPrimeFactor(x, begin);
            int y = x;
            int exp = 1;
            while (y % factor == 0) {
                y /= factor;
                exp *= factor;
            }
            ans = get(y, factor + 1) * (exp - exp / factor);
            //ans = calc(x);
            map.put(x, ans);
        }
        return ans;
    }

    private static int findPrimeFactor(int x, int begin) {
        for (int i = Math.max(2, begin); i * i <= x; i++) {
            if (x % i == 0) {
                return i;
            }
        }
        return x;
    }
}
