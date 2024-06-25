package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.entity.ProductPayment;
/*
import lk.ijse.FlowerShop.model.ProductPayment;
*/

import java.sql.*;

public class ProductPaymentDAOImpl {
    public static String currentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT PaymentId FROM ProductPayment ORDER BY CAST(SUBSTRING(PaymentId, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT PaymentId FROM ProductPayment ORDER BY CAST(SUBSTRING(PaymentId, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean add(ProductPayment productPayment) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO ProductPayment VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, productPayment.getPaymentid());
        preparedStatement.setString(2, productPayment.getCID());
        preparedStatement.setString(3, productPayment.getPOID());
        preparedStatement.setDate(4, (Date) productPayment.getDate());
        preparedStatement.setDouble(5,productPayment.getPaidPayment());
        preparedStatement.setDouble(6, productPayment.getAmount());
        preparedStatement.setDouble(7, productPayment.getBalance());


        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO ProductPayment VALUES (?, ?, ?, ?, ?, ?,?)",
                productPayment.getPaymentid(),
                productPayment.getCID(),
                productPayment.getPOID(),
                productPayment.getDate(),
                productPayment.getPaidPayment(),
                productPayment.getAmount(),
                productPayment.getBalance());
    }
}
