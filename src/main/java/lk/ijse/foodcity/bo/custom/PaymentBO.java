package lk.ijse.foodcity.bo.custom;

import lk.ijse.foodcity.dto.PaymentDto;
import java.sql.SQLException;

public interface PaymentBO extends SuperBO{
    boolean addPayment(PaymentDto dto) throws SQLException;


}