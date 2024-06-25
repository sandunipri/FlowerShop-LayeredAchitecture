package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.entity.Supplier;
/*import lk.ijse.FlowerShop.model.Supplier;*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDAOImpl {

    public static String currentSId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT S_id FROM Supplier ORDER BY CAST(SUBSTRING(S_id, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT S_id FROM Supplier ORDER BY CAST(SUBSTRING(S_id, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static boolean save(Supplier supplierList) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO Supplier VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, supplierList.getSID());
        preparedStatement.setString(2, supplierList.getStockID());
        preparedStatement.setString(3, supplierList.getUID());
        preparedStatement.setString(4, supplierList.getSName());
        preparedStatement.setString(5, supplierList.getAddress());
        preparedStatement.setString(6, supplierList.getTelNo());
        preparedStatement.setString(7, supplierList.getFcode());
        preparedStatement.setInt(8, supplierList.getQTY());
        preparedStatement.setDouble(9, supplierList.getPrice());

        return preparedStatement.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO Supplier VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                supplierList.getSID(),
                supplierList.getStockID(),
                supplierList.getUID(),
                supplierList.getSName(),
                supplierList.getAddress(),
                supplierList.getTelNo(),
                supplierList.getFcode(),
                supplierList.getQTY(),
                supplierList.getPrice());

    }
   public static boolean save(List<Supplier> supplierList) throws SQLException, ClassNotFoundException {
        for (Supplier supplier : supplierList) {
            if(!save(supplier)) {
                return false;
            }
        }
        return true;
    }


}
