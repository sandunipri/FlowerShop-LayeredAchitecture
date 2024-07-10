package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.EventDAO;
import lk.ijse.pos.entity.Event;
/*
import lk.ijse.FlowerShop.model.Event;
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
    @Override
    public  List<String> getNames() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT E_name FROM Event";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
*/
        List<String> namelist = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT E_name FROM Event");

        while(resultSet.next()) {
            namelist.add(resultSet.getString(1));
        }
        return namelist;
    }

    @Override
    public  Event searchByName(String name) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Event WHERE E_name = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,name);

      */  ResultSet resultSet = SQLUtill.execute("SELECT * FROM Event WHERE E_name = ?",name);
        if(resultSet.next()) {
            return new Event(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3),
                    resultSet.getInt(4)

            );
        }
        return null;
    }

    @Override
    public  String currentId() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT E_code FROM Event ORDER BY CAST(SUBSTRING(E_code, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet =SQLUtill.execute("SELECT E_code FROM Event ORDER BY CAST(SUBSTRING(E_code, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public  boolean add(Event event1) throws SQLException, ClassNotFoundException {
     /*   String sql = "INSERT INTO Event VALUES (?, ?, ?, ?) ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, event1.getEID());
        preparedStatement.setString(2, event1.getEName());
        preparedStatement.setDate(3, (Date) event1.getEDate());
        preparedStatement.setDouble(4, event1.getPrice());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute( "INSERT INTO Event VALUES (?, ?, ?, ?) ",event1.getEID(),event1.getEName(),event1.getEDate(),event1.getPrice());
    }

    @Override
    public  boolean delete(String eCode) throws SQLException, ClassNotFoundException {

       /* String sql = "DELETE FROM Event WHERE E_code = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, eCode);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }
    }*/
        return SQLUtill.execute("DELETE FROM Event WHERE E_code = ?", eCode);
    }

    @Override
    public boolean update(Event entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Event searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

