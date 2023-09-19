package model;

import java.util.Date;
import java.util.Objects;

public class Processo_Produttivo {
    private final String idProcess;
    private final String nameProcess;
    private final Date start;
    private final Date end;
    private final String companyName;
    private final String partitaIva;

    public Processo_Produttivo(String idProcess, String nameProcess, Date start, Date end, String companyName, String partitaIva) {
        this.idProcess = idProcess;
        this.nameProcess = nameProcess;
        this.start = start;
        this.end = end;
        this.companyName = companyName;
        this.partitaIva = partitaIva;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public String getNameProcess() {
        return nameProcess;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processo_Produttivo that)) return false;
        return Objects.equals(getIdProcess(), that.getIdProcess()) && Objects.equals(getNameProcess(), that.getNameProcess()) && Objects.equals(getStart(), that.getStart()) && Objects.equals(getEnd(), that.getEnd()) && Objects.equals(getCompanyName(), that.getCompanyName()) && Objects.equals(getPartitaIva(), that.getPartitaIva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProcess(), getNameProcess(), getStart(), getEnd(), getCompanyName(), getPartitaIva());
    }

    @Override
    public String toString() {
        return "Processo_Produttivo{" +
                "idProcess='" + idProcess + '\'' +
                ", nameProcess='" + nameProcess + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", companyName='" + companyName + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                '}';
    }
}
