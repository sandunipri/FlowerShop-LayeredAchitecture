/*
package lk.ijse.pos.dao.custom.impl;

import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.model.productPlace;

import java.sql.Connection;
import java.sql.SQLException;

public class productPlaceDAOImpl {
    public static boolean update(productPlace productPlace) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try{  boolean isProductOrderSaved = ProductOrderR.save(productPlace.getProductOrder());
            if (isProductOrderSaved) {
                System.out.println("r");
                boolean isOrderDetailSaved = ProductOrderDetailR.save(productPlace.getProductOrderDetailList());
                System.out.println("eeee");
                if (isOrderDetailSaved) {
                    boolean isStockUpdate = StockR.Updateqty(productPlace.getProductOrderDetailList());
                    System.out.println("ggggg");
                    if (isStockUpdate) {
                        connection.commit();
                        return true;

                    }
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;

        }finally {
            connection.setAutoCommit(true);
        }
    }


    }


*/
