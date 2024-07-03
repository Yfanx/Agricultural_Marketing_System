package model;

public class Purchase {
    private int agriculturalID;
    private int customerID;

    public Purchase() {}

    public Purchase(int agriculturalID, int customerID) {
        this.agriculturalID = agriculturalID;
        this.customerID = customerID;
    }

    public int getAgriculturalID() {
        return agriculturalID;
    }

    public void setAgriculturalID(int agriculturalID) {
        this.agriculturalID = agriculturalID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "agriculturalID=" + agriculturalID +
                ", customerID=" + customerID +
                '}';
    }
}
