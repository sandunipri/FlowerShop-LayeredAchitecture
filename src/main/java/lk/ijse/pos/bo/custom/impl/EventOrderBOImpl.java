package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventOrderBO;
import lk.ijse.pos.dao.custom.EventOrderDAO;
import lk.ijse.pos.dao.custom.impl.EventOrderDAOImpl;
import lk.ijse.pos.dto.EventOrderDTO;
import lk.ijse.pos.entity.EventOrder;

import java.sql.SQLException;
import java.util.List;

public class EventOrderBOImpl implements EventOrderBO {
    EventOrderDAO eventOrderDAO = new EventOrderDAOImpl();
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return eventOrderDAO.currentId();
    }

    @Override
    public boolean save(EventOrderDTO dto) throws SQLException, ClassNotFoundException {
        EventOrder eventOrder = new EventOrder(
                dto.getEOID(),
                dto.getCID(),
                dto.getEcode(),
                dto.getAmount()
        );
        return eventOrderDAO.save(eventOrder);
    }

    @Override
    public List<String> getid() {
        return eventOrderDAO.getid();
    }

    @Override
    public EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException {
        EventOrder eventOrder = new EventOrder();
        EventOrderDTO eventOrderDTO = new EventOrderDTO(

                eventOrder.getEOID(),
                eventOrder.getCID(),
                eventOrder.getEcode(),
                eventOrder.getAmount()

        );
        return eventOrderDTO;
    }
}
