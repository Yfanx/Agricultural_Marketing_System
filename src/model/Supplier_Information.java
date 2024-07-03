package model;

public class Supplier_Information {
    private int supplierID;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;

    public Supplier_Information() {}

    public Supplier_Information(int supplierID, String supplierName, String supplierAddress, String supplierPhone) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierAddress = supplierAddress;
        this.supplierPhone = supplierPhone;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    @Override
    public String toString() {
        return supplierName;
    }
}

