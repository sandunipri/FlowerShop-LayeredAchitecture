package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.EventOrder;

import java.sql.SQLException;
import java.util.List;

public interface EventOrderDAO extends CrudDAO<EventOrder> {

    public  String currentId() throws SQLException, ClassNotFoundException ;
   /* public  boolean add(EventOrder eventOrder) throws SQLException, ClassNotFoundException ;*/
    public List<String> getid() ;
  /*  EventOrder searchByID(String id) throws SQLException, ClassNotFoundException ;*/
}
