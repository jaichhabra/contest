package template.math;

/**
 * Composition
 */
public class Composite {
    final Factorial factorial;
    final Modular modular;

    public Composite(Factorial factorial) {
        this.factorial = factorial;
        this.modular = factorial.getModular();
    }


    public Composite(int limit, Modular modular) {
        this(new Factorial(limit, modular));
    }

    public int composite(int m, int n) {
        if (n > m) {
            return 0;
        }
        return modular.mul(modular.mul(factorial.fact(m), factorial.invFact(n)), factorial.invFact(m - n));
    }
}