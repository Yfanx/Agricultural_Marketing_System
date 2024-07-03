package model;

public class Agricultural_Information {
    private int agriculturalID;
    private String agriculturalName;
    private String agriculturalType;
    private String agriculturalIntroduction;
    private double price;

    private String supplierName;

    public Agricultural_Information() {}

    public Agricultural_Information(int agriculturalID, String agriculturalName, String agriculturalType, String agriculturalIntroduction, double price,String supplierName) {
        this.agriculturalID = agriculturalID;
        this.agriculturalName = agriculturalName;
        this.agriculturalType = agriculturalType;
        this.agriculturalIntroduction = agriculturalIntroduction;
        this.price = price;
        this.supplierName = supplierName;
    }

    public int getAgriculturalID() {
        return agriculturalID;
    }

    public void setAgriculturalID(int agriculturalID) {
        this.agriculturalID = agriculturalID;
    }

    public String getAgriculturalName() {
        return agriculturalName;
    }

    public void setAgriculturalName(String agriculturalName) {
        this.agriculturalName = agriculturalName;
    }

    public String getAgriculturalType() {
        return agriculturalType;
    }

    public void setAgriculturalType(String agriculturalType) {
        this.agriculturalType = agriculturalType;
    }

    public String getAgriculturalIntroduction() {
        return agriculturalIntroduction;
    }

    public void setAgriculturalIntroduction(String agriculturalIntroduction) {
        this.agriculturalIntroduction = agriculturalIntroduction;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Agricultural_Information{" +
                "agriculturalID=" + agriculturalID +
                ", agriculturalName='" + agriculturalName + '\'' +
                ", agriculturalType='" + agriculturalType + '\'' +
                ", agriculturalIntroduction='" + agriculturalIntroduction + '\'' +
                ", price=" + price +'\'' +
                ", supplierName=" + supplierName +
                '}';
    }
}

