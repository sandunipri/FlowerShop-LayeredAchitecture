package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dao.custom.impl.EventOrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.EventOrderDetailDAOImpl;
import lk.ijse.pos.dto.*;

import java.sql.SQLException;
import java.util.List;

public interface EventPlaceOrderBO {
    public  String currentId() throws SQLException, ClassNotFoundException ;
    public  boolean saveOrder(EventOrderDTO eventOrder) throws SQLException, ClassNotFoundException ;
    public List<String> getid() ;
    public EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException ;
    public   boolean saveOD(EventOrderDetailDTO eventOrderDetailDTO) throws SQLException, ClassNotFoundException ;
    boolean updateEvent(EventPlaceDTO eventPlace) throws SQLException;

    List<String> getCodes() throws SQLException, ClassNotFoundException;


    List<String> getFlowerName() throws SQLException, ClassNotFoundException;

    List<String> getNames() throws SQLException, ClassNotFoundException;

    List<String> getTelNo() throws SQLException, ClassNotFoundException;

    EventDTO searchByName(String name) throws SQLException, ClassNotFoundException;

    FlowerDTO searchByFlowerName(String name) throws SQLException, ClassNotFoundException;

    CustomerDTO searchByTelNo(String telNo) throws SQLException, ClassNotFoundException;
}
