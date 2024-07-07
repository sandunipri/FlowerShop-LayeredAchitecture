package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventPaymentBO;
import lk.ijse.pos.dao.custom.EventPaymentDAO;
import lk.ijse.pos.dao.custom.impl.EventPaymentDAOImpl;
import lk.ijse.pos.dto.EventPaymentDTO;
import lk.ijse.pos.entity.EventPayment;

import java.sql.SQLException;

public class EventPaymentBOImpl implements EventPaymentBO {
        EventPaymentDAO eventPaymentDAO = new EventPaymentDAOImpl();
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
}
