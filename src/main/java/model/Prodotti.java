package model;

import java.util.Objects;

public class Prodotti {
    private final String idProduct;
    private final String productName;
    private final String description;
    private final String productCategory;
    private final int price;
    private final int availability;
    private final String documentation;
    private final String license;
    private final String idForniture;

    public Prodotti(String idProduct, String productName, String description, String productCategory, int price, int availability, String documentation, String license, String idForniture) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.description = description;
        this.productCategory = productCategory;
        this.price = price;
        this.availability = availability;
        this.documentation = documentation;
        this.license = license;
        this.idForniture = idForniture;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailability() {
        return availability;
    }

    public String getDocumentation() {
        return documentation;
    }

    public String getLicense() {
        return license;
    }

    public String getIdForniture() {
        return idForniture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prodotti prodotti)) return false;
        return getPrice() == prodotti.getPrice() && getAvailability() == prodotti.getAvailability() && Objects.equals(getIdProduct(), prodotti.getIdProduct()) && Objects.equals(getProductName(), prodotti.getProductName()) && Objects.equals(getDescription(), prodotti.getDescription()) && Objects.equals(getProductCategory(), prodotti.getProductCategory()) && Objects.equals(getDocumentation(), prodotti.getDocumentation()) && Objects.equals(getLicense(), prodotti.getLicense()) && Objects.equals(getIdForniture(), prodotti.getIdForniture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdProduct(), getProductName(), getDescription(), getProductCategory(), getPrice(), getAvailability(), getDocumentation(), getLicense(), getIdForniture());
    }

    @Override
    public String toString() {
        return "Prodotti{" +
                "idProduct='" + idProduct + '\'' +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", documentation='" + documentation + '\'' +
                ", license='" + license + '\'' +
                ", idForniture='" + idForniture + '\'' +
                '}';
    }
}
