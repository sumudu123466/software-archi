package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends SuperDAO {
    List<Order> getAll() throws SQLException, ClassNotFoundException;
    String generateNextOrderId() throws SQLException, ClassNotFoundException;
}