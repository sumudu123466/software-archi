package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.OrderDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.entity.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM orders"; // ඔබේ table නම නිවැරදි දැයි බලන්න
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        List<Order> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Order(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4)
            ));
        }
        return list;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {

        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String lastId = rs.getString("orderId");
            int number = Integer.parseInt(lastId);
            return String.valueOf(++number);
        }
        return "1";
    }

}