package lk.ijse.foodcity.dao.impl;

// වැරදි import එක ඉවත් කර, නිවැරදි entity එක import කරන්න
import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.foodcity.dao.OrderDAO;
import lk.ijse.foodcity.entity.Order; // මෙතන ඔයාගේ entity එක import වෙන්න ඕනේ
import lk.ijse.foodcity.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute(
                "INSERT INTO orders (order_id, customer_id, order_date, total_amount) VALUES (?,?,?,?)",
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getTotalAmount()
        );
    }

    @Override
    public boolean save(MysqlxCrud.Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }
}