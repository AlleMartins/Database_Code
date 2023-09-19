package model;

import java.util.Objects;

public class Azienda {
    private final String nameCompany;
    private final String address;
    private final String city;
    private final String province;
    private final int cap;
    private final String nation;
    private final String jobSector;
    private final  int telephone;
    private final  String email;
    private final String partitaIva;
    private final String account;

    public Azienda(String nameCompany, String address, String city, String province, int cap, String nation, String jobSector, int telephone, String email, String partitaIva, String account) {
        this.nameCompany = nameCompany;
        this.address = address;
        this.city = city;
        this.province = province;
        this.cap = cap;
        this.nation = nation;
        this.jobSector = jobSector;
        this.telephone = telephone;
        this.email = email;
        this.partitaIva = partitaIva;
        this.account = account;
    }

    public String getNameCompany() {
        return nameCompany;
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

    public String getJobSector() {
        return jobSector;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public String getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "nomeAzienda='" + nameCompany + '\'' +
                ", indirizzo='" + address + '\'' +
                ", citta='" + city + '\'' +
                ", provincia='" + province + '\'' +
                ", cap=" + cap +
                ", paese='" + nation + '\'' +
                ", settore='" + jobSector + '\'' +
                ", numeroTelefono=" + telephone +
                ", email='" + email + '\'' +
                ", partitaIva='" + partitaIva + '\'' +
                ", contoAzienda='" + account + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Azienda azienda)) return false;
        return getCap() == azienda.getCap() && getTelephone() == azienda.getTelephone() && Objects.equals(getNameCompany(), azienda.getNameCompany()) && Objects.equals(getAddress(), azienda.getAddress()) && Objects.equals(getCity(), azienda.getCity()) && Objects.equals(getProvince(), azienda.getProvince()) && Objects.equals(getNation(), azienda.getNation()) && Objects.equals(getJobSector(), azienda.getJobSector()) && Objects.equals(getEmail(), azienda.getEmail()) && Objects.equals(getPartitaIva(), azienda.getPartitaIva()) && Objects.equals(getAccount(), azienda.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameCompany(), getAddress(), getCity(), getProvince(), getCap(), getNation(), getJobSector(), getTelephone(), getEmail(), getPartitaIva(), getAccount());
    }
}
