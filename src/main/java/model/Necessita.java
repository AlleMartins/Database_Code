package model;

import java.util.Objects;

public class Necessita {
    private final String idMachine;
    private final String idProcess;

    public Necessita(String idMachine, String idProcess) {
        this.idMachine = idMachine;
        this.idProcess = idProcess;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public String getIdProcess() {
        return idProcess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Necessita necessita)) return false;
        return Objects.equals(getIdMachine(), necessita.getIdMachine()) && Objects.equals(getIdProcess(), necessita.getIdProcess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMachine(), getIdProcess());
    }

    @Override
    public String toString() {
        return "Necessita{" +
                "idMachine='" + idMachine + '\'' +
                ", idProcess='" + idProcess + '\'' +
                '}';
    }
}
