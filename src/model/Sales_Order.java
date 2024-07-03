package model;

import java.sql.Timestamp;

public class Sales_Order {
    private int orderID;
    private String agriculturalName;
    private Timestamp orderCreateTime;
    private double totalPrice;
    private int userID;

    public Sales_Order() {}

    public Sales_Order(int orderID, String agriculturalName, Timestamp orderCreateTime, double totalPrice, int userID) {
        this.orderID = orderID;
        this.agriculturalName = agriculturalName;
        this.orderCreateTime = orderCreateTime;
        this.totalPrice = totalPrice;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getAgriculturalName() {
        return agriculturalName;
    }

    public void setAgriculturalName(String agriculturalName) {
        this.agriculturalName = agriculturalName;
    }

    public Timestamp getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Timestamp orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Sales_Order{" +
                "orderID=" + orderID +
                ", agriculturalName='" + agriculturalName + '\'' +
                ", orderCreateTime=" + orderCreateTime +
                ", totalPrice=" + totalPrice +
                ", customerID=" + userID +
                '}';
    }
}
