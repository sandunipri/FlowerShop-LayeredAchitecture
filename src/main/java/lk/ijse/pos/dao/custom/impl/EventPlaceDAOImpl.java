/*
package lk.ijse.FlowerShop.dao.impl;

import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.EventPlace;
*/
/*
import lk.ijse.FlowerShop.model.EventPlace;
*//*


import java.sql.Connection;

import java.sql.SQLException;
public class EventPlaceDAOImpl {
    public static boolean update(EventPlace eventPlace) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            try{  boolean isEventSaverd = EventOrderDAOImpl.save(eventPlace.getEventOrder());
                if (isEventSaverd) {
                    System.out.println("r");
                    boolean isOrderDetailSaved = EventOrderDetailDAOImpl.save(eventPlace.getEventOrderDetailList());
                    System.out.println("eeee");
                    if (isOrderDetailSaved) {
                        boolean isStockUpdate = StockDAOImpl.UPDATEQTY(eventPlace.getEventOrderDetailList());
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
