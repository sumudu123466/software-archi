package lk.ijse.foodcity.model;

import lk.ijse.foodcity.db.DBConnection;
import lk.ijse.foodcity.dto.OrderDetailDto;
import java.sql.*;
import java.util.List;

public class PlaceOrderModel {
    public static boolean placeOrder(int orderId, int customerId, double total, String payMethod, List<OrderDetailDto> details) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // Transaction Start

            // 1. Bill Table
            PreparedStatement pBill = connection.prepareStatement("INSERT INTO bill (time, total) VALUES (NOW(), ?)", Statement.RETURN_GENERATED_KEYS);
            pBill.setDouble(1, total);
            if (pBill.executeUpdate() > 0) {
                ResultSet rs = pBill.getGeneratedKeys();
                int billId = rs.next() ? rs.getInt(1) : 0;

                // 2. Order Table
                PreparedStatement pOrder = connection.prepareStatement("INSERT INTO `order` (id, status, time, customer_id, bill_id) VALUES (?, 1, NOW(), ?, ?)");
                pOrder.setInt(1, orderId);
                pOrder.setInt(2, customerId);
                pOrder.setInt(3, billId);

                if (pOrder.executeUpdate() > 0) {
                    // 3. Details & Item Stock Update
                    for (OrderDetailDto det : details) {
                        PreparedStatement pDet = connection.prepareStatement("INSERT INTO order_details (qty, price, order_id, item_id) VALUES (?, ?, ?, ?)");
                        pDet.setInt(1, det.getQty());
                        pDet.setDouble(2, det.getUnitPrice());
                        pDet.setInt(3, orderId);
                        pDet.setInt(4, det.getItemId());

                        PreparedStatement pItem = connection.prepareStatement("UPDATE item SET qty = qty - ? WHERE id = ?");
                        pItem.setInt(1, det.getQty());
                        pItem.setInt(2, det.getItemId());

                        if (pDet.executeUpdate() == 0 || pItem.executeUpdate() == 0) {
                            connection.rollback(); return false;
                        }
                    }

                    // 4. Payment Table
                    PreparedStatement pPay = connection.prepareStatement("INSERT INTO payment (method, amount, date, order_id) VALUES (?, ?, NOW(), ?)");
                    pPay.setString(1, payMethod);
                    pPay.setDouble(2, total);
                    pPay.setInt(3, orderId);

                    if (pPay.executeUpdate() > 0) {
                        connection.commit(); return true;
                    }
                }
            }
            connection.rollback(); return false;
        } catch (SQLException e) {
            connection.rollback(); throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}