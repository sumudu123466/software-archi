package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.PlaceOrderDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.entity.PlaceOrder;
import java.sql.*;

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public boolean save(PlaceOrder placeOrder) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?,?,?,?)";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, placeOrder.getOrderId());
        pstm.setString(2, placeOrder.getCustomerId());
        pstm.setString(3, placeOrder.getDate());
        pstm.setDouble(4, placeOrder.getTotal());

        return pstm.executeUpdate() > 0;
    }
}