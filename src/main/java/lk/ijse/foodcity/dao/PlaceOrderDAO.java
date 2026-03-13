package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.PlaceOrder;
import java.sql.SQLException;

public interface PlaceOrderDAO {
    boolean save(PlaceOrder placeOrder) throws SQLException;
}