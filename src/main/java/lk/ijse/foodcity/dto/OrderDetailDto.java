package lk.ijse.foodcity.dto;

public class OrderDetailDto {
    private String itemCode;
    private int qty;
    private double unitPrice;
    private double subTotal;

    // Default Constructor
    public OrderDetailDto() {}

    // Full Constructor
    public OrderDetailDto(String itemCode, int qty, double unitPrice, double subTotal) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getItemId() {
        return 0;
    }
}