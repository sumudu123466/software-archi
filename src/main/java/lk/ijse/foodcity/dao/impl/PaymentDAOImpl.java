package lk.ijse.foodcity.dao.impl;

import lk.ijse.foodcity.dao.PaymentDAO;
import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.entity.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment p) throws SQLException {
        String sql = "INSERT INTO payment VALUES(?,?,?,?)";
        PreparedStatement pstm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setInt(1, p.getPayId());
        pstm.setInt(2, p.getOrderId());
        pstm.setDouble(3, p.getAmount());
        pstm.setString(4, p.getDate());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        ResultSet rst = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM payment").executeQuery();
        List<Payment> list = new ArrayList<>();
        while (rst.next()) {
            list.add(new Payment(rst.getInt(1), rst.getInt(2), rst.getDouble(3), rst.getString(4)));
        }
        return list;
    }
}