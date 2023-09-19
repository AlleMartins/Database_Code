package model;

import java.util.Date;
import java.util.Objects;

public class Uso_Macchinario {
    private final String idMachine;
    private final String idEmployee;
    private final Date data;
    private final String time;

    public Uso_Macchinario(String idMachine, String idEmployee, Date data, String time) {
        this.idMachine = idMachine;
        this.idEmployee = idEmployee;
        this.data = data;
        this.time = time;
    }

    public String getIdMachine() {
        return idMachine;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public Date getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uso_Macchinario that)) return false;
        return Objects.equals(getIdMachine(), that.getIdMachine()) && Objects.equals(getIdEmployee(), that.getIdEmployee()) && Objects.equals(getData(), that.getData()) && Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdMachine(), getIdEmployee(), getData(), getTime());
    }

    @Override
    public String toString() {
        return "Uso_Macchinario{" +
                "idMachine='" + idMachine + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", data=" + data +
                ", time='" + time + '\'' +
                '}';
    }
}
