/*
package lk.ijse.FlowerShop.dao.impl;

import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.productPlace;
*/
/*
import lk.ijse.FlowerShop.model.productPlace;
*//*


import java.sql.Connection;
import java.sql.SQLException;

public class productPlaceDAOImpl {
    public static boolean update(productPlace productPlace) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try{  boolean isProductOrderSaved = ProductOrderDAOImpl.save(productPlace.getProductOrder());
            if (isProductOrderSaved) {
                System.out.println("r");
                boolean isOrderDetailSaved = ProductOrderDetailDAOImpl.save(productPlace.getProductOrderDetailList());
                System.out.println("eeee");
                if (isOrderDetailSaved) {
                    boolean isStockUpdate = StockDAOImpl.Updateqty(productPlace.getProductOrderDetailList());
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

  */
/*  public static boolean update(productPlace productPlace) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = ProductOrderR.save(productPlace.getProductOrderList());
            if (isOrderSaved) {
                System.out.println("hhh");
                boolean isproductUpdate = ProductR.UpdateQTY(productPlace.getProductOrderList());
                System.out.println("bhhh");
                if (isproductUpdate) {
                    boolean isStockUpdate = StockR.updateqty(productPlace.getProductOrderList());
                    System.out.println("hyyoo");
                    if (isStockUpdate) {
                        connection.commit();
                    }   return true;

                    }
                }
                connection.rollback();
                return false;
            }catch(Exception e){
                connection.rollback();
                return false;

            }finally{
                connection.setAutoCommit(true);
            }

        }*//*

    }


*/
