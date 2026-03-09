package lk.ijse.foodcity.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PlaceOrderDto {
    private String orderId;
    private String customerId;
    private LocalDateTime date;
    private double total;
    private int qty;
    private double price;
    private List<CartDetailDto> cartDetails;

    public PlaceOrderDto() {}

    public PlaceOrderDto(String orderId, String customerId, LocalDateTime date, double total, List<CartDetailDto> cartDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.date = date;
        this.total = total;
        this.cartDetails = cartDetails;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public List<CartDetailDto> getCartDetails() { return cartDetails; }
    public void setCartDetails(List<CartDetailDto> cartDetails) { this.cartDetails = cartDetails; }
}