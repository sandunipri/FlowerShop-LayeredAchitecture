package lk.ijse.pos.dao.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.EventOrder;

import java.sql.SQLException;
import java.util.List;

public interface EventOrderDAO extends SuperDAO {

    public  String currentId() throws SQLException, ClassNotFoundException ;
    public  boolean save(EventOrder eventOrder) throws SQLException, ClassNotFoundException ;
    public List<String> getid() ;
    EventOrder searchByPOID(String id) throws SQLException, ClassNotFoundException ;
}
