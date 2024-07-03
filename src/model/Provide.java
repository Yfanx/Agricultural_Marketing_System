package model;

public class Provide {
    private int agriculturalID;
    private int supplierID;

    public Provide() {}

    public Provide(int agriculturalID, int supplierID) {
        this.agriculturalID = agriculturalID;
        this.supplierID = supplierID;
    }

    public int getAgriculturalID() {
        return agriculturalID;
    }

    public void setAgriculturalID(int agriculturalID) {
        this.agriculturalID = agriculturalID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Provide{" +
                "agriculturalID=" + agriculturalID +
                ", supplierID=" + supplierID +
                '}';
    }
}
