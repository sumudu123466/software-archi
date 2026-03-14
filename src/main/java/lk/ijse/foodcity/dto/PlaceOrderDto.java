package lk.ijse.foodcity.dto;

import java.util.List;

public class PlaceOrderDto {
    private String orderId;
    private String customerId;
    private String orderDate; // දත්ත ගබඩාවට යවන්න ලේසි නිසා String ලෙස තබාගන්න
    private double total;
    private List<CartDetailDto> cartDetails;

    public PlaceOrderDto() {}


    public PlaceOrderDto(String orderId, String customerId, String orderDate, double total, List<CartDetailDto> cartDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
        this.cartDetails = cartDetails;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<CartDetailDto> getCartDetails() { return cartDetails; }
    public void setCartDetails(List<CartDetailDto> cartDetails) { this.cartDetails = cartDetails; }


    public String getDate() { return orderDate; }

}