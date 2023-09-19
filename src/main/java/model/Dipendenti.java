package model;

import java.util.Date;
import java.util.Objects;

public class Dipendenti {
    private final String id;
    private final String name;
    private final Date birthday;
    private final String address;
    private final String city;
    private final String province;
    private final int cap;
    private final String nation;
    private final int telephone;
    private final String email;
    private final String role;
    private final String department;
    private final Date assumptionDate;
    private final int salary;
    private final String certification;

    public Dipendenti(String id, String name, Date birthday, String address, String city, String province, int cap, String nation, int telephone, String email, String role, String department, Date assumptionDate, int salary, String certification) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.city = city;
        this.province = province;
        this.cap = cap;
        this.nation = nation;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.department = department;
        this.assumptionDate = assumptionDate;
        this.salary = salary;
        this.certification = certification;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getDepartment() {
        return department;
    }

    public Date getAssumptionDate() {
        return assumptionDate;
    }

    public int getSalary() {
        return salary;
    }

    public String getCertification() {
        return certification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dipendenti that)) return false;
        return getCap() == that.getCap() && getTelephone() == that.getTelephone() && getSalary() == that.getSalary() && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getBirthday(), that.getBirthday()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCity(), that.getCity()) && Objects.equals(getProvince(), that.getProvince()) && Objects.equals(getNation(), that.getNation()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getRole(), that.getRole()) && Objects.equals(getDepartment(), that.getDepartment()) && Objects.equals(getAssumptionDate(), that.getAssumptionDate()) && Objects.equals(getCertification(), that.getCertification());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthday(), getAddress(), getCity(), getProvince(), getCap(), getNation(), getTelephone(), getEmail(), getRole(), getDepartment(), getAssumptionDate(), getSalary(), getCertification());
    }

    @Override
    public String toString() {
        return "Dipendenti{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", cap=" + cap +
                ", nation='" + nation + '\'' +
                ", telephone=" + telephone +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", assumptionDate=" + assumptionDate +
                ", salary=" + salary +
                ", certification='" + certification + '\'' +
                '}';
    }
}
