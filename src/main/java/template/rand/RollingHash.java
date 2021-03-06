package template.rand;

import template.primitve.generated.datastructure.IntegerDequeImpl;

public class RollingHash {
    HashData hd;
    IntegerDequeImpl dq;
    int h;

    public RollingHash(HashData hd) {
        this.hd = hd;
        dq = new IntegerDequeImpl(hd.pow.length);
    }

    public void reset() {
        h = 0;
        dq.clear();
    }

    public void addLast(int x) {
        h = hd.mod.plus(h, (long) hd.pow[dq.size()] * x);
        dq.addLast(x);
    }

    public void removeFirst() {
        h = hd.mod.subtract(h, dq.removeFirst());
        h = hd.mod.mul(h, hd.inv[1]);
    }

    public int hash(boolean verbose) {
        int ans = h;
        if (verbose) {
            ans = hd.mod.plus(ans, hd.pow[dq.size()]);
        }
        return ans;
    }
}
