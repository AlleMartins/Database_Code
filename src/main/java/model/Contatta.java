package model;

public class Contatta {
    private final String companyName;
    private final String partitaIva;
    private final String id;

    public String getCompanyName() {
        return companyName;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public String getId() {
        return id;
    }

    public Contatta(String companyName, String partitaIva, String id) {
        this.companyName = companyName;
        this.partitaIva = partitaIva;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contatta{" +
                "companyName='" + companyName + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
