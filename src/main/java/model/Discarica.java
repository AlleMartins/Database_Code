package model;

import java.util.Date;
import java.util.Objects;

public class Discarica {
    private final String id;
    private final String name;
    private final String address;
    private final String city;
    private final String province;
    private final int cap;
    private final String nation;
    private final String waste;
    private final Date open;
    private final Date close;
    private final String manager;

    public Discarica(String id, String name, String address, String city, String province, int cap, String nation, String waste, Date open, Date close, String manager) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.province = province;
        this.cap = cap;
        this.nation = nation;
        this.waste = waste;
        this.open = open;
        this.close = close;
        this.manager = manager;
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

    public int getCap() {
        return cap;
    }

    public String getNation() {
        return nation;
    }

    public String getWaste() {
        return waste;
    }

    public Date getOpen() {
        return open;
    }

    public Date getClose() {
        return close;
    }

    public String getManager() {
        return manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discarica discarica)) return false;
        return getCap() == discarica.getCap() && Objects.equals(getId(), discarica.getId()) && Objects.equals(getName(), discarica.getName()) && Objects.equals(getAddress(), discarica.getAddress()) && Objects.equals(getCity(), discarica.getCity()) && Objects.equals(getProvince(), discarica.getProvince()) && Objects.equals(getNation(), discarica.getNation()) && Objects.equals(getWaste(), discarica.getWaste()) && Objects.equals(getOpen(), discarica.getOpen()) && Objects.equals(getClose(), discarica.getClose()) && Objects.equals(getManager(), discarica.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getCity(), getProvince(), getCap(), getNation(), getWaste(), getOpen(), getClose(), getManager());
    }

    @Override
    public String toString() {
        return "Discarica{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", cap=" + cap +
                ", nation='" + nation + '\'' +
                ", waste='" + waste + '\'' +
                ", open=" + open +
                ", close=" + close +
                ", manager='" + manager + '\'' +
                '}';
    }
}
