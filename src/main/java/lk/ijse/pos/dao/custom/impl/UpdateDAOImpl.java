/*
package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.Update;
*/
/*import lk.ijse.FlowerShop.model.Update;*//*


import java.sql.Connection;
import java.sql.SQLException;

public class UpdateDAOImpl {

    public static boolean update(Update update1) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{  boolean isSupplierSaved = SupplierDAOImpl.save(update1.getSupplierList());
            if (isSupplierSaved) {
                System.out.println("wwwwwwwww");
                boolean isStockUpdate = StockDAOImpl.UpdateQTY(update1.getSupplierList());

                System.out.println("eeee");

                if (isStockUpdate) {
                        connection.commit();
                        return true;

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
