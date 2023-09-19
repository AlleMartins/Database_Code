package model;

import java.util.Date;
import java.util.Objects;

public class Consegna_Cliente {
    private final String id;
    private final Date shipmentDate;
    private final Date arrivalDate;
    private final String address;
    private final String city;
    private final int cap;
    private final String employee;
    private final int weight;
    private final String documents;
    private final String transport;

    public Consegna_Cliente(String id, Date shipmentDate, Date arrivalDate, String address, String city, int cap, String employee, int weight, String documents, String transport) {
        this.id = id;
        this.shipmentDate = shipmentDate;
        this.arrivalDate = arrivalDate;
        this.address = address;
        this.city = city;
        this.cap = cap;
        this.employee = employee;
        this.weight = weight;
        this.documents = documents;
        this.transport = transport;
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

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getCap() {
        return cap;
    }

    public String getEmployee() {
        return employee;
    }

    public int getWeight() {
        return weight;
    }

    public String getDocuments() {
        return documents;
    }

    public String getTransport() {
        return transport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consegna_Cliente that)) return false;
        return getCap() == that.getCap() && getWeight() == that.getWeight() && Objects.equals(getId(), that.getId()) && Objects.equals(getShipmentDate(), that.getShipmentDate()) && Objects.equals(getArrivalDate(), that.getArrivalDate()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getEmployee(), that.getEmployee()) && Objects.equals(getDocuments(), that.getDocuments()) && Objects.equals(getTransport(), that.getTransport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getShipmentDate(), getArrivalDate(), getAddress(), getCity(), getCap(), getEmployee(), getWeight(), getDocuments(), getTransport());
    }

    @Override
    public String toString() {
        return "Consegna_Cliente{" +
                "id='" + id + '\'' +
                ", shipmentDate=" + shipmentDate +
                ", arrivalDate=" + arrivalDate +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", cap=" + cap +
                ", employee='" + employee + '\'' +
                ", weight=" + weight +
                ", documents='" + documents + '\'' +
                ", transport='" + transport + '\'' +
                '}';
    }
}
