package model;

public class Appear {
    private int orderID;
    private int agriculturalID;

    public Appear() {}

    public Appear(int orderID, int agriculturalID) {
        this.orderID = orderID;
        this.agriculturalID = agriculturalID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAgriculturalID() {
        return agriculturalID;
    }

    public void setAgriculturalID(int agriculturalID) {
        this.agriculturalID = agriculturalID;
    }

    @Override
    public String toString() {
        return "Appear{" +
                "orderID=" + orderID +
                ", agriculturalID=" + agriculturalID +
                '}';
    }
}
