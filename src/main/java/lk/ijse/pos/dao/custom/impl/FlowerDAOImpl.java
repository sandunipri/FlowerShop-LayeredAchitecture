package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.FlowerDAO;
import lk.ijse.pos.entity.Flower;
/*import lk.ijse.FlowerShop.model.Flower;*/

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowerDAOImpl implements FlowerDAO {
    @Override
    public List<String> getFcode() throws SQLException, ClassNotFoundException {
        List<String> CodeList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT F_code FROM Flower");

        while(resultSet.next()) {
            CodeList.add(resultSet.getString(1));
        }
        return CodeList;
    }

    @Override
    public  Flower searchByCode(String Fcode) throws SQLException, ClassNotFoundException {
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

    @Override
    public  Flower searchByFlowerName(String name) throws SQLException, ClassNotFoundException {
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

    @Override
    public  List<String> getFlowerName() throws SQLException, ClassNotFoundException {
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

    @Override
    public  String currentCode() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT F_code FROM Flower ORDER BY CAST(SUBSTRING(F_code, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT F_code FROM Flower ORDER BY CAST(SUBSTRING(F_code, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public  boolean add(Flower flower) throws SQLException, ClassNotFoundException {

        return SQLUtill.execute("INSERT INTO Flower VALUES (?, ?)", flower.getFCode(), flower.getFName());
    }

    @Override
    public  boolean delete(String fcode) throws SQLException, ClassNotFoundException {
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

    @Override
    public boolean update(Flower entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Flower searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

