package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,EVENT,EVENTORDER,EVENTORDERDETAIL,EVENTPAYMENT,FLOWER,PRODUCT,
        PRODUCTORDER,PRODUCTORDERDETAIL,PRODUCTPAYMENT,STOCK,USER,SUPPLIER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case EVENT:
                return new EventDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case FLOWER:
                return new FlowerDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PRODUCTORDER:
                return new ProductOrderDAOImpl();
            case PRODUCTORDERDETAIL:
                return new ProductOrderDetailDAOImpl();
            case PRODUCTPAYMENT:
                return new ProductPaymentDAOImpl();
            case EVENTORDER:
                return new EventOrderDAOImpl();
            case EVENTORDERDETAIL:
                return new EventOrderDetailDAOImpl();
            case EVENTPAYMENT:
                return new EventPaymentDAOImpl();
            default:
                return null;
        }
    }

}
