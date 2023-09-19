package model;

import java.util.Objects;

public class Fornitori_Associati {
    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String province;
    private final String nation;
    private final int cap;
    private final String forniture;
    private final int telephone;
    private final String email;
    private final int timeForniture;

    public Fornitori_Associati(String id, String name, String address, String city, String province, int cap, String nation, String forniture, int telephone, String email, int timeForniture) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.nation = nation;
        this.cap = cap;
        this.forniture = forniture;
        this.telephone = telephone;
        this.email = email;
        this.timeForniture = timeForniture;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getNation() {
        return nation;
    }

    public int getCap() {
        return cap;
    }

    public String getForniture() {
        return forniture;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public int getTimeForniture() {
        return timeForniture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fornitori_Associati that)) return false;
        return getCap() == that.getCap() && getTelephone() == that.getTelephone() && getTimeForniture() == that.getTimeForniture() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getProvince(), that.getProvince()) && Objects.equals(getNation(), that.getNation()) && Objects.equals(getForniture(), that.getForniture()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getCity(), getProvince(), getNation(), getCap(), getForniture(), getTelephone(), getEmail(), getTimeForniture());
    }

    @Override
    public String toString() {
        return "Fornitori_Associati{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", nation='" + nation + '\'' +
                ", cap=" + cap +
                ", forniture='" + forniture + '\'' +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                ", timeForniture=" + timeForniture +
                '}';
    }
}
