package model;

import java.util.Objects;

public class Macchinari {
    private final String id;
    private final String nameMachine;
    private final String typeMachine;
    private final String description;
    private final String brand;
    private final String modelMachine;
    private final int capacity;

    public Macchinari(String id, String nameMachine, String typeMachine, String description, String brand, String modelMachine, int capacity) {
        this.id = id;
        this.nameMachine = nameMachine;
        this.typeMachine = typeMachine;
        this.description = description;
        this.brand = brand;
        this.modelMachine = modelMachine;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getNameMachine() {
        return nameMachine;
    }

    public String getTypeMachine() {
        return typeMachine;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelMachine() {
        return modelMachine;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Macchinari that)) return false;
        return getCapacity() == that.getCapacity() && Objects.equals(getId(), that.getId()) && Objects.equals(getNameMachine(), that.getNameMachine()) && Objects.equals(getTypeMachine(), that.getTypeMachine()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getBrand(), that.getBrand()) && Objects.equals(getModelMachine(), that.getModelMachine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNameMachine(), getTypeMachine(), getDescription(), getBrand(), getModelMachine(), getCapacity());
    }

    @Override
    public String toString() {
        return "Macchinari{" +
                "id='" + id + '\'' +
                ", nameMachine='" + nameMachine + '\'' +
                ", typeMachine='" + typeMachine + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", modelMachine='" + modelMachine + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
