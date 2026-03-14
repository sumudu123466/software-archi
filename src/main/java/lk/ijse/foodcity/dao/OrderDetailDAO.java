package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.OrderDetail;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends SuperDAO {
    List<OrderDetail> searchByOrderId(String orderId) throws SQLException, ClassNotFoundException;
}