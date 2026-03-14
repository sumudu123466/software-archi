package lk.ijse.foodcity.bo.custom;

import lk.ijse.foodcity.dto.OrderDto;
import lk.ijse.foodcity.dto.OrderDetailDto; // මෙය අනිවාර්යයෙන් import කරන්න
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException;
    List<OrderDetailDto> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException;
}