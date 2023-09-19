package model;

import java.util.Date;
import java.util.Objects;

public class Produce {
    private final Date data;
    private final String idMachine;
    private final String idEmployee;
    private final String time;
    private final String idWaste;

    public Produce(Date data, String idMachine, String idEmployee, String time, String idWaste) {
        this.data = data;
        this.idMachine = idMachine;
        this.idEmployee = idEmployee;
        this.time = time;
        this.idWaste = idWaste;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produce produce)) return false;
        return Objects.equals(getData(), produce.getData()) && Objects.equals(getIdMachine(), produce.getIdMachine()) && Objects.equals(getIdEmployee(), produce.getIdEmployee()) && Objects.equals(getTime(), produce.getTime()) && Objects.equals(getIdWaste(), produce.getIdWaste());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData(), getIdMachine(), getIdEmployee(), getTime(), getIdWaste());
    }

    @Override
    public String toString() {
        return "Produce{" +
                "data=" + data +
                ", idMachine='" + idMachine + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", time='" + time + '\'' +
                ", idWaste='" + idWaste + '\'' +
                '}';
    }
}
