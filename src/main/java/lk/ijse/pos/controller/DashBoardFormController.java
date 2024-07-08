package lk.ijse.pos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.pos.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardFormController {

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblEmployeeCount;

    @FXML
    private Label lblEventOrdersCount;

    @FXML
    private Label lblProductOrdersCount;

    @FXML
    private Label lblSupplierCount;
    private int customerCount;
    private int employeeCount;
    private int supplierCount;
    private int productCount;
    private int eventCount;


    public void initialize() {
        try {
            customerCount = getCustomerCount();
            employeeCount = getEmployeeCount();
            supplierCount = getSupplierCount();
            productCount = getProductCount();
            eventCount = getEventCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCustomerCount(customerCount);
        setEmployeeCount(employeeCount);
        setSupplierCount(supplierCount);
        setProductCount(productCount);
        setEventCount(eventCount);
    }

    private void setEventCount(int eventCount) {

        lblEventOrdersCount.setText(String.valueOf(eventCount));
    }

    private void setProductCount(int productCount) {

        lblProductOrdersCount.setText(String.valueOf(productCount));

    }

    private int getEventCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS event_count FROM Event";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int eventCount = 0;
        if(resultSet.next()) {
            eventCount = resultSet.getInt("event_count");
        }
        return eventCount;
    }

    private int getProductCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS product_count FROM Product";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int productCount = 0;
        if(resultSet.next()) {
            productCount = resultSet.getInt("product_count");
        }
        return productCount;
    }

    private void setSupplierCount(int supplierCount) {

        lblSupplierCount.setText(String.valueOf(supplierCount));
    }

    private int getSupplierCount() throws SQLException {

        String sql = "SELECT COUNT(*) AS supplier_count FROM Supplier";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int supplierCount = 0;
        if(resultSet.next()) {
            supplierCount = resultSet.getInt("supplier_count");
        }
        return supplierCount;
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));

    }

    private int getEmployeeCount() throws SQLException {

        String sql = "SELECT COUNT(*) AS employee_count FROM Employee";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int employeeCount = 0;
        if(resultSet.next()) {
            employeeCount = resultSet.getInt("employee_count");
        }
        return employeeCount;

    }

    private void setCustomerCount(int customerCount) {

        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM Customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int customerCount = 0;
        if(resultSet.next()) {
            customerCount = resultSet.getInt("customer_count");
        }
        return customerCount;
    }

}
