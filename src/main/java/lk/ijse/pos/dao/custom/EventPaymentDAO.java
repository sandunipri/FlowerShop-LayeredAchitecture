package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dto.EventPaymentDTO;
import lk.ijse.pos.entity.EventPayment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EventPaymentDAO extends SuperDAO {

    public  String currentId() throws SQLException, ClassNotFoundException;

    public boolean add(EventPayment eventPayment) throws SQLException, ClassNotFoundException ;

    public  String CurrentId() throws SQLException, ClassNotFoundException ;
}
