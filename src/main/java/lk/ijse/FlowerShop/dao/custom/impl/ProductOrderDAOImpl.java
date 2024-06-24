package lk.ijse.FlowerShop.dao.custom.impl;

import lk.ijse.FlowerShop.dao.SQLUtill;
import lk.ijse.FlowerShop.db.DbConnection;
import lk.ijse.FlowerShop.entity.ProductOrder;
/*
import lk.ijse.FlowerShop.model.*;
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductOrderDAOImpl {
    public static String currentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT PO_id FROM P_order ORDER BY CAST(SUBSTRING(PO_id, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT PO_id FROM P_order ORDER BY CAST(SUBSTRING(PO_id, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean save(ProductOrder productOrder) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO P_order VALUES(?, ?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, productOrder.getPOID());
        preparedStatement.setString(2, productOrder.getCID());


        return preparedStatement.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO P_order VALUES(?, ?)",productOrder.getPOID(),productOrder.getCID());
    }

    public static List<String> getid() {

       // String sql = "SELECT PO_id FROM P_order ORDER BY PO_id desc LIMIT 1";
        List<String> list = new ArrayList<>();
        try {
           /* Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);*/
            ResultSet resultSet = SQLUtill.execute("SELECT PO_id FROM P_order ORDER BY PO_id desc LIMIT 1");
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ProductOrder searchByPOID(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM P_order WHERE PO_id= ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM P_order WHERE PO_id= ?",id);
        if(resultSet.next()) {
            return new ProductOrder(
                    resultSet.getString(1),
                    resultSet.getString(2)


            );
        }
        return null;
    }
}
