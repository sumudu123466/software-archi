package lk.ijse.foodcity.model;

import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.CartTM;
import lk.ijse.foodcity.dto.OrderDto;
import lk.ijse.foodcity.dto.OrderDetailDto;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {


    public static String getNextOrderId() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT id FROM `order` ORDER BY id DESC LIMIT 1");
        if (rst.next()) {
            int lastId = rst.getInt(1);
            return String.valueOf(lastId + 1);
        }
        return "1";
    }

    public static ArrayList<OrderDto> getAllOrders() {
        ArrayList<OrderDto> list = new ArrayList<>();
        try {
            ResultSet rst = CrudUtil.execute(
                    "SELECT o.id, o.customer_id, o.time, b.total FROM `order` o JOIN bill b ON o.bill_id = b.id"
            );
            while (rst.next()) {
                list.add(new OrderDto(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public static ArrayList<OrderDetailDto> getOrderDetails(String orderId) {
        ArrayList<OrderDetailDto> detailsList = new ArrayList<>();
        try {
            ResultSet rst = CrudUtil.execute(
                    "SELECT i.code, od.qty, od.price, (od.qty * od.price) AS subTotal " +
                            "FROM order_details od JOIN item i ON od.item_id = i.id " +
                            "WHERE od.order_id = ?", orderId
            );
            while (rst.next()) {
                detailsList.add(new OrderDetailDto(rst.getString(1), rst.getInt(2), rst.getDouble(3), rst.getDouble(4)));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return detailsList;
    }


    public static boolean placeOrder(int orderId, String customerNic, double netTotal, ArrayList<CartTM> items) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // Transaction Start


            boolean isBillSaved = CrudUtil.execute("INSERT INTO bill (time, total) VALUES (NOW(), ?)", netTotal);
            if (!isBillSaved) { connection.rollback(); return false; }

            ResultSet rstBill = CrudUtil.execute("SELECT id FROM bill ORDER BY id DESC LIMIT 1");
            int billId = rstBill.next() ? rstBill.getInt(1) : 0;


            ResultSet rstCust = CrudUtil.execute("SELECT cus_id FROM customer WHERE id=?", customerNic);
            int customerPk = rstCust.next() ? rstCust.getInt(1) : 0;


            boolean isOrderSaved = CrudUtil.execute(
                    "INSERT INTO `order` (id, status, time, customer_id, bill_id) VALUES (?, 1, NOW(), ?, ?)",
                    orderId, customerPk, billId
            );

            if (isOrderSaved) {
                for (CartTM tm : items) {

                    ResultSet rstItem = CrudUtil.execute("SELECT id FROM item WHERE code=?", tm.getCode());
                    int itemPk = rstItem.next() ? rstItem.getInt(1) : 0;


                    boolean isDetailsSaved = CrudUtil.execute(
                            "INSERT INTO order_details (qty, price, order_id, item_id) VALUES (?, ?, ?, ?)",
                            tm.getQty(), tm.getUnitPrice(), orderId, itemPk
                    );

                    if (!isDetailsSaved) { connection.rollback(); return false; }


                    boolean isQtyUpdated = CrudUtil.execute("UPDATE item SET qty = qty - ? WHERE code = ?", tm.getQty(), tm.getCode());
                    if (!isQtyUpdated) { connection.rollback(); return false; }
                }
                connection.commit();
                return true;
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}