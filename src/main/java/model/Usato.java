package model;

import java.util.Objects;

public class Usato {
    private final String idProcess;
    private final String idProduct;

    public Usato(String idProcess, String idProduct) {
        this.idProcess = idProcess;
        this.idProduct = idProduct;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public String getIdProduct() {
        return idProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usato usato)) return false;
        return Objects.equals(getIdProcess(), usato.getIdProcess()) && Objects.equals(getIdProduct(), usato.getIdProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProcess(), getIdProduct());
    }

    @Override
    public String toString() {
        return "Usato{" +
                "idProcess='" + idProcess + '\'' +
                ", idProduct='" + idProduct + '\'' +
                '}';
    }
}
