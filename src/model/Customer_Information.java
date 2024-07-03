package model;

public class Customer_Information {    private int customerID;
    private String customerIDCard;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private int userID;

    public Customer_Information() {}

    public Customer_Information(int customerID, String customerIDCard, String customerName, String customerPhone, String customerAddress, int userID) {
        this.customerID = customerID;
        this.customerIDCard = customerIDCard;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.userID = userID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerIDCard() {
        return customerIDCard;
    }

    public void setCustomerIDCard(String customerIDCard) {
        this.customerIDCard = customerIDCard;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Customer_Information{" +
                "customerID=" + customerID +
                ", customerIDCard='" + customerIDCard + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", userID=" + userID +
                '}';
    }
}
