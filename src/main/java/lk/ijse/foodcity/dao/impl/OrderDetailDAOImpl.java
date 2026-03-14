package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.OrderDetailDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.entity.OrderDetail;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetail> searchByOrderId(String orderId) throws SQLException, ClassNotFoundException {

        String sql = "SELECT * FROM order_details WHERE order_id = ?";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setString(1, orderId);
        ResultSet rs = pstm.executeQuery();

        List<OrderDetail> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new OrderDetail(
                    rs.getString("order_id"),
                    rs.getString("item_id"),
                    rs.getInt("qty"),
                    rs.getDouble("price")
            ));
        }
        return list;
    }
}