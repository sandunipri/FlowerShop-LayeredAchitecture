package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.EventOrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface EventOrderBO {
    public  String currentId() throws SQLException, ClassNotFoundException ;

    public  boolean save(EventOrderDTO eventOrder) throws SQLException, ClassNotFoundException ;


    public List<String> getid() ;

    public EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException ;
}
