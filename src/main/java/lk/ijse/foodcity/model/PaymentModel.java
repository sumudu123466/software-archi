package lk.ijse.foodcity.model;

import lk.ijse.foodcity.dto.PaymentDto;
import lk.ijse.foodcity.util.CrudUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentModel {

    public static boolean savePayment(PaymentDto dto) throws SQLException {
        // Table: payment (id, method, amount, date, order_id)
        return CrudUtil.execute(
                "INSERT INTO payment (method, amount, date, order_id) VALUES (?, ?, ?, ?)",
                "Cash",
                dto.getAmount(),
                dto.getDate(),
                dto.getOrderId()
        );
    }

    public static ArrayList<PaymentDto> getAllPayments() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment");
        ArrayList<PaymentDto> list = new ArrayList<>();

        while (rst.next()) {
            list.add(new PaymentDto(
                    rst.getInt("id"),
                    rst.getInt("order_id"),
                    rst.getDouble("amount"),
                    rst.getString("date")
            ));
        }
        return list;
    }
}