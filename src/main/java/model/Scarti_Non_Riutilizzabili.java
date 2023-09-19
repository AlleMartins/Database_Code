package model;

import java.util.Date;
import java.util.Objects;

public class Scarti_Non_Riutilizzabili {
    private final String id;
    private final String description;
    private final Date detectionDate;
    private final int amount;
    private final String documentation;
    private final String reject;

    public Scarti_Non_Riutilizzabili(String id, String description, Date detectionDate, int amount, String documentation, String reject) {
        this.id = id;
        this.description = description;
        this.detectionDate = detectionDate;
        this.amount = amount;
        this.documentation = documentation;
        this.reject = reject;
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

    public String getReject() {
        return reject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scarti_Non_Riutilizzabili that)) return false;
        return getAmount() == that.getAmount() && Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getDetectionDate(), that.getDetectionDate()) && Objects.equals(getDocumentation(), that.getDocumentation()) && Objects.equals(getReject(), that.getReject());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getDetectionDate(), getAmount(), getDocumentation(), getReject());
    }

    @Override
    public String toString() {
        return "Scarti_Non_Riutilizzabili{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", detectionDate=" + detectionDate +
                ", amount=" + amount +
                ", documentation='" + documentation + '\'' +
                ", reject='" + reject + '\'' +
                '}';
    }
}
