package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.EventPaymentDAO;
import lk.ijse.pos.entity.EventPayment;
/*import lk.ijse.FlowerShop.entity.EventPlace;
import lk.ijse.FlowerShop.model.EventPayment;*/

import java.sql.*;

public class EventPaymentDAOImpl implements EventPaymentDAO {
    @Override
    public  String currentId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT PaymentId FROM EventPayment ORDER BY PaymentId desc LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute( "SELECT PaymentId FROM EventPayment ORDER BY PaymentId desc LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean add(EventPayment eventPayment) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO EventPayment VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, eventPayment.getPaymentid());
        preparedStatement.setString(2, eventPayment.getCID());
        preparedStatement.setString(3, eventPayment.getEOID());
        preparedStatement.setDate(4, (Date) eventPayment.getDate());
        preparedStatement.setDouble(5,eventPayment.getPaidPayment());
        preparedStatement.setDouble(6, eventPayment.getAmount());
        preparedStatement.setDouble(7, eventPayment.getBalance());


        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO EventPayment VALUES (?, ?, ?, ?, ?, ?,?)",
                eventPayment.getPaymentid(),
                eventPayment.getCID(),
                eventPayment.getEOID(),
                eventPayment.getDate(),
                eventPayment.getPaidPayment(),
                eventPayment.getAmount(),
                eventPayment.getBalance());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(EventPayment entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public EventPayment searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String CurrentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT PaymentId FROM EventPayment ORDER BY CAST(SUBSTRING(PaymentId, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT PaymentId FROM EventPayment ORDER BY CAST(SUBSTRING(PaymentId, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
