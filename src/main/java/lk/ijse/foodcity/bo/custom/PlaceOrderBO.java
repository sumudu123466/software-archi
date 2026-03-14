package lk.ijse.foodcity.bo.custom;

import lk.ijse.foodcity.dto.ItemDTO;

import lk.ijse.foodcity.dto.PlaceOrderDto;
import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException, ClassNotFoundException;
    String getNextOrderId() throws SQLException, ClassNotFoundException;
    List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    List<String> getItemCodes() throws SQLException, ClassNotFoundException;
    ItemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException;
}