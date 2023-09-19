package model;

import java.util.Objects;

public class Trasporto {
    private final String idWaste;
    private final String idTrasport;

    public Trasporto(String idWaste, String idTrasport) {
        this.idWaste = idWaste;
        this.idTrasport = idTrasport;
    }

    public String getIdWaste() {
        return idWaste;
    }

    public String getIdTrasport() {
        return idTrasport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trasporto trasporto)) return false;
        return Objects.equals(getIdWaste(), trasporto.getIdWaste()) && Objects.equals(getIdTrasport(), trasporto.getIdTrasport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdWaste(), getIdTrasport());
    }

    @Override
    public String toString() {
        return "Trasporto{" +
                "idWaste='" + idWaste + '\'' +
                ", idTrasport='" + idTrasport + '\'' +
                '}';
    }
}
