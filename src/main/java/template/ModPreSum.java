package template;

public class ModPreSum {
    private int[] pre;
    private NumberTheory.Modular mod;

    public ModPreSum(int[] a, NumberTheory.Modular mod) {
        int n = a.length;
        pre = new int[n];
        pre[0] = mod.valueOf(a[0]);
        for (int i = 1; i < n; i++) {
            pre[i] = mod.plus(pre[i - 1], a[i]);
        }
    }

    /**
     * get a[l] + a[l + 1] + ... + a[r]
     */
    public long intervalSum(int l, int r) {
        if (l == 0) {
            return pre[r];
        }
        return mod.subtract(pre[r], pre[l - 1]);
    }

    /**
     * get a[0] + a[1] + ... + a[i]
     */
    public long prefix(int i) {
        return pre[i];
    }

    /**
     * get a[i] + a[i + 1] + ... + a[n - 1]
     */
    public long post(int i) {
        if (i == 0) {
            return pre[pre.length - 1];
        }
        return mod.subtract(pre[pre.length - 1], pre[i - 1]);
    }
}