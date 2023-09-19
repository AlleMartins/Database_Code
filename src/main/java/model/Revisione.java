package model;

import java.util.Date;
import java.util.Objects;

public class Revisione {

    private final String id;
    private final Date start;
    private final Date end;
    private final String responsible;
    private final String result;
    private final String correctiveActions;
    private final int cost;
    private final String state;
    private final String notes;
    private final String idMachine;

    public Revisione(String id, Date start, Date end, String responsible, String result, String correctiveActions, int cost, String state, String notes, String idMachine) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.responsible = responsible;
        this.result = result;
        this.correctiveActions = correctiveActions;
        this.cost = cost;
        this.state = state;
        this.notes = notes;
        this.idMachine = idMachine;
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getResponsible() {
        return responsible;
    }

    public String getResult() {
        return result;
    }

    public String getCorrectiveActions() {
        return correctiveActions;
    }

    public int getCost() {
        return cost;
    }

    public String getState() {
        return state;
    }

    public String getNotes() {
        return notes;
    }

    public String getIdMachine() {
        return idMachine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Revisione revisione)) return false;
        return getCost() == revisione.getCost() && Objects.equals(getId(), revisione.getId()) && Objects.equals(getStart(), revisione.getStart()) && Objects.equals(getEnd(), revisione.getEnd()) && Objects.equals(getResponsible(), revisione.getResponsible()) && Objects.equals(getResult(), revisione.getResult()) && Objects.equals(getCorrectiveActions(), revisione.getCorrectiveActions()) && Objects.equals(getState(), revisione.getState()) && Objects.equals(getNotes(), revisione.getNotes()) && Objects.equals(getIdMachine(), revisione.getIdMachine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStart(), getEnd(), getResponsible(), getResult(), getCorrectiveActions(), getCost(), getState(), getNotes(), getIdMachine());
    }

    @Override
    public String toString() {
        return "Revisione{" +
                "id='" + id + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", responsible='" + responsible + '\'' +
                ", result='" + result + '\'' +
                ", correctiveActions='" + correctiveActions + '\'' +
                ", cost=" + cost +
                ", state='" + state + '\'' +
                ", notes='" + notes + '\'' +
                ", idMachine='" + idMachine + '\'' +
                '}';
    }
}
