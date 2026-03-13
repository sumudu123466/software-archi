package lk.ijse.foodcity.dto;

public class CartDetailDto {
    private String itemCode;
    private int qty;
    private double unitPrice;

    public CartDetailDto() {}

    public CartDetailDto(String itemCode, int qty, double unitPrice) {
        this.itemCode = itemCode;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
}