package lk.ijse.foodcity.entity;

public class Order {
    private String orderId;
    private String customerId;
    private String date; // එක දිනයක් පමණක් තබා ගන්න
    private double total; // එක total එකක් පමණක් තබා ගන්න

    public Order() {}

    // Constructor එක නව විචල්‍යයන්ට ගැලපෙන ලෙස
    public Order(String orderId, String customerId, String date, double total) {
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