package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.EventOrderDTO;
import lk.ijse.pos.dto.EventPaymentDTO;
import lk.ijse.pos.entity.EventPayment;

import java.sql.SQLException;
import java.util.List;

public interface EventPaymentBO {
    public  String currentId() throws SQLException, ClassNotFoundException;

    public boolean add(EventPaymentDTO eventPaymentDTO) throws SQLException, ClassNotFoundException ;

    public  String CurrentId() throws SQLException, ClassNotFoundException ;

    List<String> getid();

    EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException;
}
