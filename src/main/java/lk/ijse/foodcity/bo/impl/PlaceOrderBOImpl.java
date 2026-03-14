package lk.ijse.foodcity.bo.impl;

import lk.ijse.foodcity.bo.custom.PlaceOrderBO;
import lk.ijse.foodcity.dao.*;
import lk.ijse.foodcity.dao.impl.*;
import lk.ijse.foodcity.dto.*;
import lk.ijse.foodcity.entity.*;

import java.sql.SQLException;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {


    private final PlaceOrderDAO placeOrderDAO = (PlaceOrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PlaceOrder);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean placeOrder(PlaceOrderDto dto) throws SQLException, ClassNotFoundException {

        PlaceOrder entity = new PlaceOrder(dto.getOrderId(), dto.getCustomerId(), dto.getOrderDate(), dto.getTotal());
        return placeOrderDAO.save(entity);
    }

    @Override
    public String getNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextOrderId();
    }

    @Override
    public List<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllIds();
    }

    @Override
    public List<String> getItemCodes() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllCodes();
    }


    @Override
    public ItemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException {
        return itemDAO.search(itemCode);
    }
}