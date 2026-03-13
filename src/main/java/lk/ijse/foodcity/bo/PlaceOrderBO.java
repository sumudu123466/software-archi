package lk.ijse.foodcity.bo;

import lk.ijse.foodcity.dto.PlaceOrderDto;
import java.sql.SQLException;

public interface PlaceOrderBO {
    boolean placeOrder(PlaceOrderDto dto) throws SQLException;
}