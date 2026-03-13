package lk.ijse.foodcity.entity;

public class PlaceOrder {
    private String orderId;
    private String customerId;
    private String date;
    private double total;

    public PlaceOrder() {}

    public PlaceOrder(String orderId, String customerId, String date, double total) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.total = total;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}