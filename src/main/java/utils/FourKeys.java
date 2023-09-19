package utils;

import java.util.Objects;

public class FourKeys<X,Y,Z,M> {
    private final X x;
    private final Y y;
    private final Z z;
    private final M m;

    public FourKeys(X x, Y y, Z z, M m) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.m = m;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FourKeys<?, ?, ?, ?> fourKeys)) return false;
        return Objects.equals(getX(), fourKeys.getX()) && Objects.equals(getY(), fourKeys.getY()) && Objects.equals(getZ(), fourKeys.getZ()) && Objects.equals(getM(), fourKeys.getM());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ(), getM());
    }

    @Override
    public String toString() {
        return "FourKeys{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", m=" + m +
                '}';
    }
}
