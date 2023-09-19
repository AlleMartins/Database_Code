package model;

import java.util.Objects;

public class Destinazione {
    private final String idDump;
    private final String idTransport;

    public Destinazione(String idDump, String idTransport) {
        this.idDump = idDump;
        this.idTransport = idTransport;
    }

    public String getIdDump() {
        return idDump;
    }

    public String getIdTransport() {
        return idTransport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Destinazione that)) return false;
        return Objects.equals(idDump, that.idDump) && Objects.equals(idTransport, that.idTransport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDump, idTransport);
    }

    @Override
    public String toString() {
        return "Destinazione{" +
                "idDump='" + idDump + '\'' +
                ", idTransport='" + idTransport + '\'' +
                '}';
    }
}
