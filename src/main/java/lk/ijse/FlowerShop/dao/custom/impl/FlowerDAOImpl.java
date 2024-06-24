package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.Flower;
/*import lk.ijse.FlowerShop.model.Flower;*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl {

    public static List<String> getFcode() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT F_code FROM Flower";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> CodeList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT F_code FROM Flower");

        while(resultSet.next()) {
            CodeList.add(resultSet.getString(1));
        }
        return CodeList;
    }

    public static Flower searchByCode(String Fcode) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Flower WHERE F_code = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,Fcode );
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Flower WHERE F_code = ?",Fcode );
        if(resultSet.next()) {
            return new Flower(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    public static Flower searchByName(String name) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Flower WHERE F_name = ?";

        PreparedStatement preparedStatement= DbConnection.getInstance().getConnection().prepareStatement(sql);
        preparedStatement.setObject(1,name );
*/
        ResultSet resultSet =SQLUtill.execute("SELECT * FROM Flower WHERE F_name = ?",name );
        if(resultSet.next()) {
            return new Flower(

                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    public static List<String> getFlowerName() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT F_name FROM Flower";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> nameList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT F_name FROM Flower");

        while(resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }

    public static String currentCode() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT F_code FROM Flower ORDER BY CAST(SUBSTRING(F_code, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT F_code FROM Flower ORDER BY CAST(SUBSTRING(F_code, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean add(Flower flower) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO Flower VALUES (?, ?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, flower.getFCode());
        preparedStatement.setString(2, flower.getFName());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO Flower VALUES (?, ?)", flower.getFCode(), flower.getFName());
    }

    public static boolean delete(String fcode) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM Flower WHERE F_code = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, fcode);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("DELETE FROM Flower WHERE F_code = ?", fcode);
    }
}

