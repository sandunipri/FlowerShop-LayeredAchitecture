package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.UserBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.dao.custom.impl.UserDAOImpl;
import lk.ijse.pos.dto.UserDTO;
import lk.ijse.pos.entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
       return userDAO.getId();
    }

    @Override
    public UserDTO searchByID(String id) throws SQLException, ClassNotFoundException {
        User user =new User();
        UserDTO userDTO = new UserDTO(
                user.getUid(),
                user.getUName(),
                user.getUPasswrod()
        );
        return userDTO;
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
       User user = new User(
               dto.getUid(),
               dto.getUName(),
               dto.getUPasswrod()
       );
       return userDAO.update(user);
    }
}
