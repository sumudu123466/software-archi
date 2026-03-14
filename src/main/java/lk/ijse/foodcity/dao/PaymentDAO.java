package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.entity.Payment;
import java.sql.SQLException;
import java.util.List;

public interface PaymentDAO extends SuperDAO{
    boolean save(Payment payment) throws SQLException;
    List<Payment> getAll() throws SQLException;
}