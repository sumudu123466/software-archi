package lk.ijse.foodcity.entity;

public class Payment {
    private int payId;
    private int orderId;
    private double amount;
    private String date;

    public Payment() {}
    public Payment(int payId, int orderId, double amount, String date) {
        this.payId = payId;
        this.orderId = orderId;
        this.amount = amount;
        this.date = date;
    }
    // Getters and Setters...
    public int getPayId() { return payId; }
    public int getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
}