package model;

import java.util.Date;
import java.util.Objects;

public class Trasporto_Discarica {
    private final String id;
    private final Date shipmentDate;
    private final Date arrivalDate;
    private final String trasport;
    private final String employee;
    private final int amount;
    private final int weight;
    private final String documents;

    public Trasporto_Discarica(String id, Date shipmentDate, Date arrivalDate, String trasport, String employee, int amount, int weight, String documents) {
        this.id = id;
        this.shipmentDate = shipmentDate;
        this.arrivalDate = arrivalDate;
        this.trasport = trasport;
        this.employee = employee;
        this.amount = amount;
        this.weight = weight;
        this.documents = documents;
    }

    public String getId() {
        return id;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getTrasport() {
        return trasport;
    }

    public String getEmployee() {
        return employee;
    }

    public int getAmount() {
        return amount;
    }

    public int getWeight() {
        return weight;
    }

    public String getDocuments() {
        return documents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trasporto_Discarica that)) return false;
        return getAmount() == that.getAmount() && getWeight() == that.getWeight() && Objects.equals(getId(), that.getId()) && Objects.equals(getShipmentDate(), that.getShipmentDate()) && Objects.equals(getArrivalDate(), that.getArrivalDate()) && Objects.equals(getTrasport(), that.getTrasport()) && Objects.equals(getEmployee(), that.getEmployee()) && Objects.equals(getDocuments(), that.getDocuments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShipmentDate(), getArrivalDate(), getTrasport(), getEmployee(), getAmount(), getWeight(), getDocuments());
    }

    @Override
    public String toString() {
        return "Trasporto_Discarica{" +
                "id='" + id + '\'' +
                ", shipmentDate=" + shipmentDate +
                ", arrivalDate=" + arrivalDate +
                ", trasport='" + trasport + '\'' +
                ", employee='" + employee + '\'' +
                ", amount=" + amount +
                ", weight=" + weight +
                ", documents='" + documents + '\'' +
                '}';
    }
}
