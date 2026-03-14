package lk.ijse.foodcity.bo.custom;

import lk.ijse.foodcity.dto.OrderDetailDto;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailsBO extends SuperBO {
    List<OrderDetailDto> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException;
}