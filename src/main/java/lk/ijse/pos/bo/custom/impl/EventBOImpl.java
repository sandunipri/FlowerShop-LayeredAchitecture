package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.EventBO;
import lk.ijse.pos.dao.custom.EventDAO;
import lk.ijse.pos.dao.custom.impl.EventDAOImpl;
import lk.ijse.pos.dto.EventDTO;
import lk.ijse.pos.entity.Event;

import java.sql.SQLException;
import java.util.List;

public class EventBOImpl implements EventBO {

    EventDAO eventDAO = new EventDAOImpl();
    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
       return eventDAO.getNames();
    }

    @Override
    public EventDTO searchByName(String name) throws SQLException, ClassNotFoundException {
        Event event = new Event();
        EventDTO eventDTO = new EventDTO(
                event.getEID(),
                event.getEName(),
                event.getEDate(),
                event.getPrice()
        );
        return eventDTO;
    }

    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
      return eventDAO.currentId();
    }

    @Override
    public boolean add(EventDTO dto) throws SQLException, ClassNotFoundException {
       Event event = new Event(
         dto.getEID(),
         dto.getEName(),
         dto.getEDate(),
         dto.getPrice()

       );
       return eventDAO.add(event);
    }

    @Override
    public boolean delete(String eCode) throws SQLException, ClassNotFoundException {
        return eventDAO.delete(eCode);
    }
}
