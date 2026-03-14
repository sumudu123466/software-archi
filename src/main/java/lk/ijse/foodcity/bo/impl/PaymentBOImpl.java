package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.PaymentBO;
import lk.ijse.foodcity.dao.DAOFactory;
import lk.ijse.foodcity.dao.PaymentDAO;
import lk.ijse.foodcity.dao.impl.PaymentDAOImpl;
import lk.ijse.foodcity.dto.PaymentDto;
import lk.ijse.foodcity.entity.Payment;
import java.sql.SQLException;

public class PaymentBOImpl implements PaymentBO {
    private PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);

    @Override
    public boolean addPayment(PaymentDto dto) throws SQLException {

        Payment payment = new Payment(dto.getPayId(), dto.getOrderId(), dto.getAmount(), dto.getDate());
        return paymentDAO.save(payment);
    }
}