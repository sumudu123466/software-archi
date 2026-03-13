package lk.ijse.foodcity.dto;

public class OrderDto {
    private String orderId; // int වෙනුවට String පාවිච්චි කරන්න
    private String customerId;
    private String orderDate;
    private Double totalAmount;

    public OrderDto() {}

    public OrderDto(String orderId, String customerId, String orderDate, Double totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getOrderDate() { return orderDate; }
    public void setOrderDate(String orderDate) { this.orderDate = orderDate; }
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public static class OrderDetailDto {
        private String itemCode;
        private int qty;
        private double unitPrice;
        private double subTotal;

        public OrderDetailDto() {}

        public OrderDetailDto(String itemCode, int qty, double unitPrice, double subTotal) {
            this.itemCode = itemCode;
            this.qty = qty;
            this.unitPrice = unitPrice;
            this.subTotal = subTotal;
        }


        public String getItemCode() { return itemCode; }
        public void setItemCode(String itemCode) { this.itemCode = itemCode; }

        public int getQty() { return qty; }
        public void setQty(int qty) { this.qty = qty; }

        public double getUnitPrice() { return unitPrice; }
        public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

        public double getSubTotal() { return subTotal; }
        public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
    }
}