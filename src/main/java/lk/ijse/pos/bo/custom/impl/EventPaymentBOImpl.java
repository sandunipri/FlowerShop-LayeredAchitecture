package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventPaymentBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.EventOrderDAO;
import lk.ijse.pos.dao.custom.EventPaymentDAO;
import lk.ijse.pos.dao.custom.impl.EventOrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.EventPaymentDAOImpl;
import lk.ijse.pos.dto.EmployeeDTO;
import lk.ijse.pos.dto.EventOrderDTO;
import lk.ijse.pos.dto.EventPaymentDTO;
import lk.ijse.pos.entity.EventOrder;
import lk.ijse.pos.entity.EventPayment;

import java.sql.SQLException;
import java.util.List;

public class EventPaymentBOImpl implements EventPaymentBO {
        EventPaymentDAO eventPaymentDAO = (EventPaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENTPAYMENT);
        EventOrderDAO eventOrderDAO = (EventOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENTORDER);


    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return eventPaymentDAO.currentId();
    }

    @Override
    public boolean add(EventPaymentDTO eventPaymentDTO) throws SQLException, ClassNotFoundException {
        EventPayment eventPayment = new EventPayment(
                eventPaymentDTO.getPaymentid(),
                eventPaymentDTO.getCID(),
                eventPaymentDTO.getEOID(),
                eventPaymentDTO.getDate(),
                eventPaymentDTO.getPaidPayment(),
                eventPaymentDTO.getAmount(),
                eventPaymentDTO.getBalance()
        );

        return eventPaymentDAO.add(eventPayment);
    }

    @Override
    public String CurrentId() throws SQLException, ClassNotFoundException {
        return eventPaymentDAO.CurrentId();
    }

    @Override
    public List<String> getid() {
        return eventOrderDAO.getid();
    }

    @Override
    public EventOrderDTO searchByPOID(String id) throws SQLException, ClassNotFoundException {
        EventOrder eventOrder = eventOrderDAO.searchByID(id);
        EventOrderDTO eventOrderDTO = new EventOrderDTO(
                eventOrder.getEOID(),
                eventOrder.getCID(),
                eventOrder.getEcode(),
                eventOrder.getAmount()
        );
        return eventOrderDTO;
    }
}
