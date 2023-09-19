package model;

import java.util.Date;
import java.util.Objects;

public class Fattura {
    private final int invoiceNumber;
    private final Date emission;
    private final String description;
    private final int total;
    private final String paymentMethod;
    private final String statePayment;
    private final Date expirationDate;
    private final String addressInvoice;
    private final String companyName;
    private final String partitaIva;

    public Fattura(int invoiceNumber, Date emission, String description, int total, String paymentMethod, String statePayment, Date expirationDate, String addressInvoice, String companyName, String partitaIva) {
        this.invoiceNumber = invoiceNumber;
        this.emission = emission;
        this.description = description;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.statePayment = statePayment;
        this.expirationDate = expirationDate;
        this.addressInvoice = addressInvoice;
        this.companyName = companyName;
        this.partitaIva = partitaIva;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Date getEmission() {
        return emission;
    }

    public String getDescription() {
        return description;
    }

    public int getTotal() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatePayment() {
        return statePayment;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getAddressInvoice() {
        return addressInvoice;
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
        if (!(o instanceof Fattura fattura)) return false;
        return invoiceNumber == fattura.invoiceNumber && total == fattura.total && Objects.equals(emission, fattura.emission) && Objects.equals(description, fattura.description) && Objects.equals(paymentMethod, fattura.paymentMethod) && Objects.equals(statePayment, fattura.statePayment) && Objects.equals(expirationDate, fattura.expirationDate) && Objects.equals(addressInvoice, fattura.addressInvoice) && Objects.equals(companyName, fattura.companyName) && Objects.equals(partitaIva, fattura.partitaIva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNumber, emission, description, total, paymentMethod, statePayment, expirationDate, addressInvoice, companyName, partitaIva);
    }

    @Override
    public String toString() {
        return "Fattura{" +
                "invoiceNumber=" + invoiceNumber +
                ", emission=" + emission +
                ", description='" + description + '\'' +
                ", total=" + total +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", statePayment='" + statePayment + '\'' +
                ", expirationDate=" + expirationDate +
                ", addressInvoice='" + addressInvoice + '\'' +
                ", companyName='" + companyName + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                '}';
    }
}
