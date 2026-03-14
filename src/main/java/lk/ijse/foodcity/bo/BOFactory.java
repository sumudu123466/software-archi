package lk.ijse.foodcity.bo;

import lk.ijse.foodcity.bo.custom.SuperBO;
import lk.ijse.foodcity.bo.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL, PAYMENT,PlaceOrder
    }

    public SuperBO getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_DETAIL:
                return new OrderDetailsBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PlaceOrder:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}