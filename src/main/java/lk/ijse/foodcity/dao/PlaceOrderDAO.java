package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.PlaceOrder;
import java.sql.SQLException;

public interface PlaceOrderDAO extends SuperDAO{
    boolean save(PlaceOrder placeOrder) throws SQLException, ClassNotFoundException;
}