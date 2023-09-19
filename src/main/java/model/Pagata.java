package model;

import java.util.Objects;

public class Pagata {
    private final String partitaIva;
    private final int invoiceNumber;

    public Pagata(String partitaIva, int invoiceNumber) {
        this.partitaIva = partitaIva;
        this.invoiceNumber = invoiceNumber;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagata pagata)) return false;
        return getInvoiceNumber() == pagata.getInvoiceNumber() && Objects.equals(getPartitaIva(), pagata.getPartitaIva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPartitaIva(), getInvoiceNumber());
    }

    @Override
    public String toString() {
        return "Pagata{" +
                "partitaIva='" + partitaIva + '\'' +
                ", invoiceNumber=" + invoiceNumber +
                '}';
    }
}
