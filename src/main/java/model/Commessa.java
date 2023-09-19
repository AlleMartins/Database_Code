package model;

import java.util.Date;
import java.util.Objects;

public class Commessa {
    private final String id;
    private final String description;
    private final Date start;
    private final Date end;
    private final String state;
    private final String documents;
    private final String project;
    private final String companyName;
    private final String partitaIva;
    private final String ricPartitaIva;


    public Commessa(String id, String description, Date start, Date end, String state, String documents, String project, String companyName, String partitaIva, String ricPartitaIva) {
        this.id = id;
        this.description = description;
        this.start = start;
        this.end = end;
        this.state = state;
        this.documents = documents;
        this.project = project;
        this.companyName = companyName;
        this.partitaIva = partitaIva;
        this.ricPartitaIva = ricPartitaIva;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getState() {
        return state;
    }

    public String getDocuments() {
        return documents;
    }

    public String getProject() {
        return project;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public String getRicPartitaIva() {
        return ricPartitaIva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commessa commessa)) return false;
        return Objects.equals(getId(), commessa.getId()) && Objects.equals(getDescription(), commessa.getDescription()) && Objects.equals(getStart(), commessa.getStart()) && Objects.equals(getEnd(), commessa.getEnd()) && Objects.equals(getState(), commessa.getState()) && Objects.equals(getDocuments(), commessa.getDocuments()) && Objects.equals(getProject(), commessa.getProject()) && Objects.equals(getCompanyName(), commessa.getCompanyName()) && Objects.equals(getPartitaIva(), commessa.getPartitaIva()) && Objects.equals(getRicPartitaIva(), commessa.getRicPartitaIva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getStart(), getEnd(), getState(), getDocuments(), getProject(), getCompanyName(), getPartitaIva(), getRicPartitaIva());
    }

    @Override
    public String toString() {
        return "Commessa{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", state='" + state + '\'' +
                ", documents='" + documents + '\'' +
                ", project='" + project + '\'' +
                ", companyName='" + companyName + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", ricPartitaIva='" + ricPartitaIva + '\'' +
                '}';
    }
}
