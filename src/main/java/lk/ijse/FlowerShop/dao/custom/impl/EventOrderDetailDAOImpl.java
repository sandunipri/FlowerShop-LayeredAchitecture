package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.EventOrderDetail;
/*
import lk.ijse.FlowerShop.model.EventOrderDetail;
*/

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EventOrderDetailDAOImpl {
/*    public static boolean save(List<EventOrderDetail> eventOrderDetailList) throws SQLException {

        for (EventOrderDetail eventOrderDetail: eventOrderDetailList) {
            if(!save(eventOrderDetail)) {
                return false;
            }
        }
        return true;
    }*/
    private static boolean save(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException {

      /*  String sql = "INSERT INTO E_OrderDetail VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, eventOrderDetail.getEOID());
        pstm.setString(2, eventOrderDetail.getStockID());
        pstm.setInt(3, eventOrderDetail.getIssuedFlowers());
        pstm.setDouble(4, eventOrderDetail.getPrice());


        return pstm.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO E_OrderDetail VALUES(?, ?, ?, ?)", eventOrderDetail.getEOID(), eventOrderDetail.getStockID(), eventOrderDetail.getIssuedFlowers(), eventOrderDetail.getPrice());
    }
}
