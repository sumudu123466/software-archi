package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.OrderDetailsBO;
import lk.ijse.foodcity.dao.DAOFactory;
import lk.ijse.foodcity.dao.OrderDetailDAO;
import lk.ijse.foodcity.dao.impl.OrderDetailDAOImpl;
import lk.ijse.foodcity.dto.OrderDetailDto;
import lk.ijse.foodcity.entity.OrderDetail;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private final OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    @Override
    public List<OrderDetailDto> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetail> entities = orderDetailDAO.searchByOrderId(orderId);
        List<OrderDetailDto> dtos = new ArrayList<>();

        if (entities != null) {
            for (OrderDetail od : entities) {

                dtos.add(new OrderDetailDto(od.getItemCode(), od.getQty(), od.getUnitPrice(), od.getSubTotal()));
            }
        }
        return dtos;
    }
}