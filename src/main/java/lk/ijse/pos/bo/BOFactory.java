package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){

        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
       CUSTOMER,EMPLOYEE,EVENT,EVENTPAYMENT,EVENTPLACEORDER,FLOWER,PRODUCT,
        PRODUCTPAYMENT,PRODUCTPLACEORDER,STOCK,SUPPLIER,UPDATE,USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case USER:
                return new UserBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case EVENT:
                return new EventBOImpl();
            case EVENTPAYMENT:
                return new EventPaymentBOImpl();
            case EVENTPLACEORDER:
                return new EventPlaceOrderBOImpl();
            case FLOWER:
                return new FlowerBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case PRODUCTPAYMENT:
                return new ProductPaymentBOImpl();
            case PRODUCTPLACEORDER:
                return new ProductPlaceOrderBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case UPDATE:
                return new UpdateBOImpl();

            default:
                return null;
        }
    }
}
