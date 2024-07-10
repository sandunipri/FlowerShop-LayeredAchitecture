package lk.ijse.pos.dao.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Event;

import java.sql.SQLException;
import java.util.List;

public interface EventDAO extends CrudDAO<Event> {
    public List<String> getNames() throws SQLException, ClassNotFoundException;

    public Event searchByName(String name) throws SQLException, ClassNotFoundException;

    public String currentId() throws SQLException, ClassNotFoundException;

  /*  public boolean add(Event event1) throws SQLException, ClassNotFoundException;*/
  /*  public boolean delete(String eCode) throws SQLException, ClassNotFoundException ;*/
}
