package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
/*
import lk.ijse.FlowerShop.model.Customer;
*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  List<String> getCid() throws SQLException, ClassNotFoundException {
        List<String> IDList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT C_id FROM Customer");

        while(resultSet.next()) {
            IDList.add(resultSet.getString(1));
        }
        return IDList;
    }
    @Override
    public  Customer searchByID(String C_id) throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT * FROM Customer WHERE C_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,C_id);*/

        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Customer WHERE C_id = ?", C_id);
        if(resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }
        return null;
    }
    @Override
    public  boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, customer.getCid());
        preparedStatement.setString(2, customer.getUid());
        preparedStatement.setString(3, customer.getCname());
        preparedStatement.setString(4, customer.getAddress());
        preparedStatement.setString(5, customer.getTelNo());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO Customer VALUES (?, ?, ?, ?, ?)",customer.getCid(),customer.getUid(),customer.getCname(),customer.getAddress(),customer.getTelNo());
    }
    @Override
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {
       /* String sql = "UPDATE Customer SET U_id = ?, C_name = ?, Address = ?, TelNo = ? WHERE C_id = ?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, customer.getUid());
        preparedStatement.setString(2, customer.getCname());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getTelNo());
        preparedStatement.setString(5, customer.getCid());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("UPDATE Customer SET U_id = ?, C_name = ?, Address = ?, TelNo = ? WHERE C_id = ?",customer.getUid(),customer.getCname(),customer.getAddress(),customer.getTelNo(),customer.getCid());
    }
    @Override
    public boolean delete(String cid) throws SQLException, ClassNotFoundException {
       /* String sql = "DELETE FROM Customer WHERE C_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, cid);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/

        return SQLUtill.execute("DELETE FROM Customer WHERE C_id = ?", cid);
    }
    @Override
    public List<String> getTelNo() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT TelNo FROM Customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> TelNoList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute( "SELECT TelNo FROM Customer");

        while(resultSet.next()) {
            TelNoList.add(resultSet.getString(1));
        }
        return TelNoList;
    }
    @Override
    public  Customer searchByTelNo(String telNo) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM Customer WHERE TelNo = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,telNo);*/

        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Customer WHERE TelNo = ?", telNo);
        if(resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
        }
        return null;
    }
    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM Customer";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        */
        List<Customer> customerList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Customer");


        while (resultSet.next()) {
            String cid = resultSet.getString(1);
            String Uid = resultSet.getString(2);
            String name = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String telno = resultSet.getString(5);



            Customer customer = new Customer(cid,Uid,name,Address,telno);


            customerList.add(customer);
        }
        return customerList;
    }
    @Override
    public  String currentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT C_id FROM Customer ORDER BY CAST(SUBSTRING(C_id, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute( "SELECT C_id FROM Customer ORDER BY CAST(SUBSTRING(C_id, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
    }
