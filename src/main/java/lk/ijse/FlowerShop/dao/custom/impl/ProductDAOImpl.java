package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.Product;
/*
import lk.ijse.FlowerShop.model.Product;
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl {
    public static List<String> getProductName() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT P_name FROM Product";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        List<String> nameList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT P_name FROM Product");

        while (resultSet.next()) {
            nameList.add(resultSet.getString(1));
        }
        return nameList;
    }

    public static Product searchByName(String name) throws SQLException, ClassNotFoundException {

        /*String sql = "SELECT * FROM Product WHERE P_name = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,name);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Product WHERE P_name = ?", name);
        if (resultSet.next()) {
            return new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            );
        }
        return null;
    }

   /* public static Product searchByName(String name) throws SQLException {
        String sql = "SELECT * FROM Product WHERE P_name = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,name);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return new Product(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            );
        }
        return null;
    }*/

    public static String currentId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT P_code FROM Product ORDER BY CAST(SUBSTRING(P_code, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT P_code FROM Product ORDER BY CAST(SUBSTRING(P_code, 2) AS UNSIGNED) DESC LIMIT 1");

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean add(Product product) throws SQLException, ClassNotFoundException {
      /*  String sql = "INSERT INTO Product VALUES (?, ?, ?) ";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, product.getPCode());
        preparedStatement.setString(2, product.getPName());
        preparedStatement.setDouble(3, product.getPrice());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO Product VALUES (?,?,?)", product.getPCode(), product.getPName(), product.getPrice());
    }

    public static boolean delete(String pcode) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM Product WHERE P_code = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, pcode);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }    }*/
        return SQLUtill.execute("DELETE FROM Product WHERE P_code = ?", pcode);
    }
}




  /*  public static boolean UpdateQTY(List<ProductOrder> productOrderList) throws SQLException {
        for (ProductOrder product : productOrderList) {
            if(!updateQTY(product)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQTY(ProductOrder product) throws SQLException {
        String sql = "UPDATE Product SET QtyOnHand = QtyOnHand - ? WHERE P_code = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1,product.getPCode());
        preparedStatement.setString(2,product.);
        return preparedStatement.executeUpdate() > 0;
    }*/

