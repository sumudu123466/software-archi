package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.PlaceOrderBO;
import lk.ijse.foodcity.dao.PlaceOrderDAO;
import lk.ijse.foodcity.dao.impl.PlaceOrderDAOImpl;
import lk.ijse.foodcity.dto.PlaceOrderDto;
import lk.ijse.foodcity.entity.PlaceOrder;
import java.sql.SQLException;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    private PlaceOrderDAO placeOrderDAO = new PlaceOrderDAOImpl();

    @Override
    public boolean placeOrder(PlaceOrderDto dto) throws SQLException {

        PlaceOrder placeOrder = new PlaceOrder(
                dto.getOrderId(),
                dto.getCustomerId(),
                dto.getDate().toString(),
                dto.getTotal()
        );


        return placeOrderDAO.save(placeOrder);
    }
}