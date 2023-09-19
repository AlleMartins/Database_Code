package model;

import java.util.Date;
import java.util.Objects;

public class Ricicla {
    private final Date data;
    private final String idMachine;
    private final String idEmployee;
    private final String time;
    private final String idWaste;

    @Override
    public String toString() {
        return "Ricicla{" +
                "data=" + data +
                ", idMachine='" + idMachine + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", time='" + time + '\'' +
                ", idWaste='" + idWaste + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ricicla ricicla)) return false;
        return Objects.equals(getData(), ricicla.getData()) && Objects.equals(getIdMachine(), ricicla.getIdMachine()) && Objects.equals(getIdEmployee(), ricicla.getIdEmployee()) && Objects.equals(getTime(), ricicla.getTime()) && Objects.equals(getIdWaste(), ricicla.getIdWaste());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData(), getIdMachine(), getIdEmployee(), getTime(), getIdWaste());
    }

    public Date getData() {
        return data;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public String getTime() {
        return time;
    }

    public String getIdWaste() {
        return idWaste;
    }

    public Ricicla(Date data, String idMachine, String idEmployee, String time, String idWaste) {
        this.data = data;
        this.idMachine = idMachine;
        this.idEmployee = idEmployee;
        this.time = time;
        this.idWaste = idWaste;
    }
}
