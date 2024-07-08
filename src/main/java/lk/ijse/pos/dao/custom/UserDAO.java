package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends SuperDAO {
    public  List<String> getId() throws SQLException, ClassNotFoundException ;

    public  User searchByID(String id) throws SQLException, ClassNotFoundException ;

    public  boolean update(User user) throws SQLException, ClassNotFoundException ;
}
