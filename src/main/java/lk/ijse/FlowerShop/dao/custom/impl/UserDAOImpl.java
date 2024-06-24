package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.User;
/*
import lk.ijse.FlowerShop.model.User;
*/

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl {

    public static List<String> getId() throws SQLException, ClassNotFoundException {
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


    public static User searchByID(String id) throws SQLException, ClassNotFoundException {
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

    public static boolean update(User user) throws SQLException, ClassNotFoundException {
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
