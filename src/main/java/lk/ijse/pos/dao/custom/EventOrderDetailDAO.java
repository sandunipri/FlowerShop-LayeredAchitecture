package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.entity.EventOrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface EventOrderDetailDAO {
    /*    public static boolean save(List<EventOrderDetail> eventOrderDetailList) throws SQLException {

            for (EventOrderDetail eventOrderDetail: eventOrderDetailList) {
                if(!save(eventOrderDetail)) {
                    return false;
                }
            }
            return true;
        }*/
    boolean save(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException;

    public boolean save(List<EventOrderDetail> eventOrderDetailList) throws SQLException, ClassNotFoundException;
}
