package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.dao.custom.EmployeeDAO;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.dto.EmployeeDTO;
import lk.ijse.FlowerShop.entity.Employee;
/*
import lk.ijse.FlowerShop.model.Employee;
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public  List<String> getEid() throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT E_id FROM Employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/

        List<String> IDList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT E_id FROM Employee");

        while(resultSet.next()) {
            IDList.add(resultSet.getString(1));
        }
        return IDList;
    }
    public  EmployeeDTO searchByID(String E_id) throws SQLException, ClassNotFoundException {
      /*  String sql = "SELECT * FROM Employee WHERE E_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, E_id);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Employee WHERE E_id = ?", E_id);
        if(resultSet.next()) {
            return new EmployeeDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            );
        }
        return null;

    }

    public  boolean add(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
/*            String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

            preparedStatement.setString(1, employee.getEid());
            preparedStatement.setString(2, employee.getUid());
            preparedStatement.setString(3, employee.getEname());
            preparedStatement.setString(4, employee.getAddress());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getJobRole());
            preparedStatement.setString(7, employee.getTelNo());

            int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    return true;
                } else {
                    return false;
        }*/
        return SQLUtill.execute("INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?)",employee.getEid(),employee.getUid(),employee.getEname(),employee.getAddress(),employee.getEmail(),employee.getJobRole(),employee.getTelNo());
    }

    public  boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
      /*  String sql = "UPDATE Employee SET U_id = ?, E_name = ?, Address = ?,Email = ?,JobRole = ?, TelNo = ? WHERE E_id = ?";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, employee.getUid());
        preparedStatement.setString(2, employee.getEname());
        preparedStatement.setString(3, employee.getAddress());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, employee.getJobRole());
        preparedStatement.setString(6, employee.getTelNo());
        preparedStatement.setString(7, employee.getEid());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute( "UPDATE Employee SET U_id = ?, E_name = ?, Address = ?,Email = ?,JobRole = ?, TelNo = ? WHERE E_id = ?",employee.getUid(),employee.getEname(),employee.getAddress(),employee.getEmail(),employee.getJobRole(),employee.getTelNo(),employee.getEid());
    }

    public  boolean delete(String eid) throws SQLException, ClassNotFoundException {

       /* String sql = "DELETE FROM Employee WHERE E_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, eid);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute( "DELETE FROM Employee WHERE E_id = ?",eid);
    }

    public  List<Employee> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM Employee";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Employee");

        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String eid = resultSet.getString(1);
            String Uid = resultSet.getString(2);
            String name = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String Email = resultSet.getString(5);
            String jobrole= resultSet.getString(6);
            String telno = resultSet.getString(7);


         Employee employee = new Employee(eid,Uid,name,Address,Email,jobrole,telno);


            employeeList.add(employee);
        }
        return employeeList;

    }

    public  String currentId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT E_id FROM Employee ORDER BY CAST(SUBSTRING(E_id, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute( "SELECT E_id FROM Employee ORDER BY CAST(SUBSTRING(E_id, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
}
