package lk.ijse.foodcity.dto;

public class PaymentDto {
    private int payId;
    private int orderId;
    private double amount;
    private String date;

    public PaymentDto() {}

    public PaymentDto(int payId, int orderId, double amount, String date) {
        this.payId = payId;
        this.orderId = orderId;
        this.amount = amount;
        this.date = date;
    }

    public int getPayId() { return payId; }
    public void setPayId(int payId) { this.payId = payId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}