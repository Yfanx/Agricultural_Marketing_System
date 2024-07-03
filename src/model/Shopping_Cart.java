package model;

public class Shopping_Cart {
    private int cartID;
    private int agriculturalID;
    private String agriculturalName;
    private double price;
    private String supplierName;
    private int quantity;

    public Shopping_Cart(int cartID, int agriculturalID, String agriculturalName, double price, String supplierName) {
        this.cartID = cartID;
        this.agriculturalID = agriculturalID;
        this.agriculturalName = agriculturalName;
        this.price = price;
        this.supplierName = supplierName;
    }

    // Getters and setters
    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
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

}
