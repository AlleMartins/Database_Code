package model;

import java.util.Date;
import java.util.Objects;

public class Cliente {
    private final String name;
    private final String surname;
    private final Date birthday;
    private final String address;
    private final String city;
    private final String province;
    private final int cap;
    private final String nation;
    private final String email;
    private final int telephone;
    private final String jobSector;
    private final String partitaIva;

    public Cliente(String name, String surname, Date birthday, String address, String city, String province, int cap, String nation, String email, int telephone, String jobSector, String partitaIva) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.province = province;
        this.cap = cap;
        this.nation = nation;
        this.email = email;
        this.telephone = telephone;
        this.jobSector = jobSector;
        this.partitaIva = partitaIva;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return birthday;
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

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getJobSector() {
        return jobSector;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return getCap() == cliente.getCap() && getTelephone() == cliente.getTelephone() && Objects.equals(getName(), cliente.getName()) && Objects.equals(getSurname(), cliente.getSurname()) && Objects.equals(getBirthday(), cliente.getBirthday()) && Objects.equals(getAddress(), cliente.getAddress()) && Objects.equals(getCity(), cliente.getCity()) && Objects.equals(getProvince(), cliente.getProvince()) && Objects.equals(getNation(), cliente.getNation()) && Objects.equals(getEmail(), cliente.getEmail()) && Objects.equals(getJobSector(), cliente.getJobSector()) && Objects.equals(getPartitaIva(), cliente.getPartitaIva());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getBirthday(), getAddress(), getCity(), getProvince(), getCap(), getNation(), getEmail(), getTelephone(), getJobSector(), getPartitaIva());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", cap=" + cap +
                ", nation='" + nation + '\'' +
                ", email='" + email + '\'' +
                ", telephone=" + telephone +
                ", jobSector='" + jobSector + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                '}';
    }
}
