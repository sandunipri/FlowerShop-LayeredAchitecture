package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.EventDTO;

import java.sql.SQLException;
import java.util.List;

public interface EventBO {
    public List<String> getNames() throws SQLException, ClassNotFoundException;

    public EventDTO searchByName(String name) throws SQLException, ClassNotFoundException;

    public String currentId() throws SQLException, ClassNotFoundException;

    public boolean add(EventDTO dto) throws SQLException, ClassNotFoundException;

    public boolean delete(String eCode) throws SQLException, ClassNotFoundException ;
}
