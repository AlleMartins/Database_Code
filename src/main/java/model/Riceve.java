package model;

import java.util.Objects;

public class Riceve {
    private final String partitaIva;
    private final String idDelivery;

    public Riceve(String partitaIva, String idDelivery) {
        this.partitaIva = partitaIva;
        this.idDelivery = idDelivery;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public String getIdDelivery() {
        return idDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Riceve riceve)) return false;
        return Objects.equals(getPartitaIva(), riceve.getPartitaIva()) && Objects.equals(getIdDelivery(), riceve.getIdDelivery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPartitaIva(), getIdDelivery());
    }

    @Override
    public String toString() {
        return "Riceve{" +
                "partitaIva='" + partitaIva + '\'' +
                ", idDelivery='" + idDelivery + '\'' +
                '}';
    }
}
