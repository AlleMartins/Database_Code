package utils;

import java.util.Objects;

public class FiveKeys<X,Y,Z,M,N> {

    private final X x;
    private final Y y;
    private final Z z;
    private final M m;
    private final N n;

    public FiveKeys(final X x, final Y y, final Z z, final M m, final N n) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.m = m;
        this.n = n;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Z getZ() {
        return z;
    }

    public M getM() {
        return m;
    }

    public N getN() {
        return n;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FiveKeys<?, ?, ?, ?, ?> fiveKeys)) return false;
        return Objects.equals(getX(), fiveKeys.getX()) && Objects.equals(getY(), fiveKeys.getY()) && Objects.equals(getZ(), fiveKeys.getZ()) && Objects.equals(getM(), fiveKeys.getM()) && Objects.equals(getN(), fiveKeys.getN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ(), getM(), getN());
    }

    @Override
    public String toString() {
        return "FiveKeys{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", m=" + m +
                ", n=" + n +
                '}';
    }
}
