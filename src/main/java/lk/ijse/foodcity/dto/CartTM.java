package lk.ijse.foodcity.dto;

public class CartTM {
    private String code;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;

    public CartTM(String code, String description, int qty, double unitPrice, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.total = total;
    }
    // Getters and Setters
    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description; }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}