package lk.ijse.foodcity.dao;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.foodcity.entity.Order;

import java.sql.SQLException;

public interface OrderDAO {
    boolean save(Order entity) throws SQLException, ClassNotFoundException;

    boolean save(MysqlxCrud.Order entity) throws SQLException, ClassNotFoundException;
    String getNextOrderId() throws SQLException, ClassNotFoundException;
}