package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.UserDAO;
import lk.ijse.pos.entity.User;
/*
import lk.ijse.FlowerShop.model.User;
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public  List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT U_id FROM User";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> IDlist = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT U_id FROM User");

        while(resultSet.next()) {
            IDlist.add(resultSet.getString(1));
        }
        return IDlist;
    }

    @Override
    public  User searchByID(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM User WHERE U_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, id);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM User WHERE U_id = ?", id);
        if(resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)


            );
        }
        return null;
    }

    @Override
    public  boolean update(User user) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE User SET U_name = ?, U_password = ? WHERE U_id = ?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, user.getUName());
        preparedStatement.setString(2, user.getUPasswrod());
        preparedStatement.setString(3, user.getUid());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("UPDATE User SET U_name = ?, U_password = ? WHERE U_id = ?", user.getUName(), user.getUPasswrod(), user.getUid());
    }
}
