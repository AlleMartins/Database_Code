package model;

import java.util.Date;
import java.util.Objects;

public class Scarti_Riutilizzabili {
    private final String id;
    private final String description;
    private final Date detectionDate;
    private final int amount;
    private final String documentation;

    public Scarti_Riutilizzabili(String id, String description, Date detectionDate, int amount, String documentation) {
        this.id = id;
        this.description = description;
        this.detectionDate = detectionDate;
        this.amount = amount;
        this.documentation = documentation;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public int getAmount() {
        return amount;
    }

    public String getDocumentation() {
        return documentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scarti_Riutilizzabili that)) return false;
        return getAmount() == that.getAmount() && Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDetectionDate(), that.getDetectionDate()) && Objects.equals(getDocumentation(), that.getDocumentation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getDetectionDate(), getAmount(), getDocumentation());
    }

    @Override
    public String toString() {
        return "Scarti_Riutilizzabili{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", detectionDate=" + detectionDate +
                ", amount=" + amount +
                ", documentation='" + documentation + '\'' +
                '}';
    }
}
