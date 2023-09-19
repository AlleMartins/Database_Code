package model;

import java.util.Objects;

public class Prodotto_Finito {
    private final String id;
    private final int availability;
    private final String description;
    private final String documentation;
    private final String idProcess;
    private final String idDelivery;

    public Prodotto_Finito(String id, int availability, String description, String documentation, String idProcess, String idDelivery) {
        this.id = id;
        this.availability = availability;
        this.description = description;
        this.documentation = documentation;
        this.idProcess = idProcess;
        this.idDelivery = idDelivery;
    }

    public String getId() {
        return id;
    }

    public int getAvailability() {
        return availability;
    }

    public String getDescription() {
        return description;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public String getIdDelivery() {
        return idDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prodotto_Finito that)) return false;
        return getAvailability() == that.getAvailability() && Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDocumentation(), that.getDocumentation()) && Objects.equals(getIdProcess(), that.getIdProcess()) && Objects.equals(getIdDelivery(), that.getIdDelivery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAvailability(), getDescription(), getDocumentation(), getIdProcess(), getIdDelivery());
    }

    @Override
    public String toString() {
        return "Prodotto_Finito{" +
                "id='" + id + '\'' +
                ", availability=" + availability +
                ", description='" + description + '\'' +
                ", documentation='" + documentation + '\'' +
                ", idProcess='" + idProcess + '\'' +
                ", idDelivery='" + idDelivery + '\'' +
                '}';
    }
}
