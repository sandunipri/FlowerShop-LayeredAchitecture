package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    public List<String> getId() throws SQLException, ClassNotFoundException ;

    public UserDTO searchByID(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(UserDTO dto) throws SQLException, ClassNotFoundException ;
}
