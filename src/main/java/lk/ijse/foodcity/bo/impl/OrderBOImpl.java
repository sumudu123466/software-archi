package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.OrderBO;
import lk.ijse.foodcity.dao.DAOFactory;
import lk.ijse.foodcity.dao.OrderDAO;
import lk.ijse.foodcity.dao.impl.OrderDAOImpl;
import lk.ijse.foodcity.dao.impl.OrderDetailDAOImpl;
import lk.ijse.foodcity.dto.OrderDetailDto;
import lk.ijse.foodcity.dto.OrderDto;
import lk.ijse.foodcity.entity.Order;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public List<OrderDto> getAllOrders() throws SQLException, ClassNotFoundException {
        List<Order> entities = orderDAO.getAll();
        List<OrderDto> dtos = new ArrayList<>();

        if (entities != null) {
            for (Order o : entities) {

                dtos.add(new OrderDto(o.getOrderId(), o.getCustomerId(), o.getDate(), o.getTotal()));
            }
        }
        return dtos;
    }

    @Override
    public List<OrderDetailDto> getOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        return new OrderDetailDAOImpl().searchByOrderId(orderId).stream().map(detail ->
                new OrderDetailDto(detail.getItemCode(), detail.getQty(), detail.getUnitPrice(), detail.getSubTotal())
        ).collect(Collectors.toList());
    }
}