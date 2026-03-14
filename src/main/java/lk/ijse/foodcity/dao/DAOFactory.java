package lk.ijse.foodcity.dao;

import lk.ijse.foodcity.bo.BOFactory;
import lk.ijse.foodcity.bo.custom.SuperBO;
import lk.ijse.foodcity.bo.impl.*;
import lk.ijse.foodcity.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, PAYMENT,PlaceOrder
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case PlaceOrder:
                return new PlaceOrderDAOImpl();
            default:
                return null;
        }
    }
}