package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.EventOrderDetailDAO;
import lk.ijse.pos.entity.EventOrderDetail;
/*
import lk.ijse.FlowerShop.model.EventOrderDetail;
*/

import java.sql.SQLException;
import java.util.List;

public class EventOrderDetailDAOImpl implements EventOrderDetailDAO {
/*    public static boolean save(List<EventOrderDetail> eventOrderDetailList) throws SQLException {

        for (EventOrderDetail eventOrderDetail: eventOrderDetailList) {
            if(!save(eventOrderDetail)) {
                return false;
            }
        }
        return true;
    }*/
    @Override
    public boolean save(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException {

      /*  String sql = "INSERT INTO E_OrderDetail VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, eventOrderDetail.getEOID());
        pstm.setString(2, eventOrderDetail.getStockID());
        pstm.setInt(3, eventOrderDetail.getIssuedFlowers());
        pstm.setDouble(4, eventOrderDetail.getPrice());


        return pstm.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO E_OrderDetail VALUES(?, ?, ?, ?)", eventOrderDetail.getEOID(), eventOrderDetail.getStockID(), eventOrderDetail.getIssuedFlowers(), eventOrderDetail.getPrice());
    }

    @Override
    public boolean save(List<EventOrderDetail> eventOrderDetailList) throws SQLException, ClassNotFoundException {
        for (EventOrderDetail eventOrderDetail: eventOrderDetailList) {
            if(!save(eventOrderDetail)) {
                return false;
            }
        }
        return true;
    }
}
