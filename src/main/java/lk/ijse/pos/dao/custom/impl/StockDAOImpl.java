package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.StockDAO;
import lk.ijse.pos.entity.EventOrderDetail;
import lk.ijse.pos.entity.ProductOrderDetail;
import lk.ijse.pos.entity.Stock;
import lk.ijse.pos.entity.Supplier;
/*import lk.ijse.FlowerShop.model.*;*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public boolean add(Stock stock) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES (?, ?, ?, )";

        Connection connection = DbConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, stock.getStockId());
            preparedStatement.setString(2, stock.getFCode());
            preparedStatement.setInt(3, stock.getQtyOnHand());


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }*/
        return SQLUtill.execute("INSERT INTO Stock (Stock_id, F_code, QtyOnHand) VALUES (?, ?, ?)",
                stock.getStockId(),
                stock.getFCode(),
                stock.getQtyOnHand());

    }

    @Override
    public List<String> getCodes() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT Stock_id FROM Stock";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
*/
        List<String> codeList = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT Stock_id FROM Stock");

        while(resultSet.next()) {
            codeList.add(resultSet.getString(1));
        }
        return codeList;
    }

    @Override
    public Stock searchById(String stockID) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM Stock WHERE Stock_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,stockID);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM Stock WHERE Stock_id = ?",stockID);
        if(resultSet.next()) {
            return new Stock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)

            );
        }
        return null;
    }
    @Override
    public boolean UpdateQTY(List<Supplier> supplierList) throws SQLException, ClassNotFoundException {
        for (Supplier supplier : supplierList) {
            if(!UpdateQTY(supplier)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean UpdateQTY(Supplier supplier) throws SQLException, ClassNotFoundException {
/*@Override

    @Override
        String sql = "UPDATE Stock SET QtyOnHand = QtyOnHand + ? WHERE Stock_id = ?";
    @Override
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);


        preparedStatement.setInt(1,supplier.getQTY());
        preparedStatement.setString(2,supplier.getStockID());
        return preparedStatement.executeUpdate() > 0;*/

        return SQLUtill.execute("UPDATE Stock SET QtyOnHand = QtyOnHand + ? WHERE Stock_id = ?",supplier.getQTY(),supplier.getStockID());
    }

    @Override
    public boolean Updateqty(List<ProductOrderDetail> productOrderDetailList) throws SQLException, ClassNotFoundException {

        for (ProductOrderDetail productOrderDetail : productOrderDetailList) {
            if(!Updateqty(productOrderDetail)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean Updateqty(ProductOrderDetail productOrderDetail) throws SQLException, ClassNotFoundException {

       /* String sql = "UPDATE Stock SET QtyOnHand = QtyOnHand - ? WHERE Stock_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);


        preparedStatement.setInt(1,productOrderDetail.getIssuedFlowers());
        preparedStatement.setString(2,productOrderDetail.getStockID());
        return preparedStatement.executeUpdate() > 0;*/

        return SQLUtill.execute("UPDATE Stock SET QtyOnHand = QtyOnHand - ? WHERE Stock_id = ?",productOrderDetail.getIssuedFlowers(),productOrderDetail.getStockID());
    }

    @Override
    public boolean UPDATEQTY(List<EventOrderDetail> eventOrderDetailList) throws SQLException, ClassNotFoundException {
        for (EventOrderDetail eventOrderDetail : eventOrderDetailList) {
            if(!UPDATEQTY(eventOrderDetail)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean UPDATEQTY(EventOrderDetail eventOrderDetail) throws SQLException, ClassNotFoundException {

      /*  String sql = "UPDATE Stock SET QtyOnHand = QtyOnHand - ? WHERE Stock_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);


        preparedStatement.setInt(1,eventOrderDetail.getIssuedFlowers());
        preparedStatement.setString(2,eventOrderDetail.getStockID());
        return preparedStatement.executeUpdate() > 0;*/

        return SQLUtill.execute("UPDATE Stock SET QtyOnHand = QtyOnHand - ? WHERE Stock_id = ?",eventOrderDetail.getIssuedFlowers(),eventOrderDetail.getStockID());
    }

    @Override
    public boolean ADD(Stock stock) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO Stock VALUES (?, ?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, stock.getStockId());
        preparedStatement.setString(2, stock.getFCode());
        preparedStatement.setInt(3, stock.getQtyOnHand());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("INSERT INTO Stock VALUES (?, ?,?)",
                stock.getStockId(),
                stock.getFCode(),
                stock.getQtyOnHand()
        );
    }

    @Override
    public boolean delete(String stockid) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Stock WHERE Stock_id = ?";
        /*PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setObject(1, stockid);

        //return preparedStatement.executeUpdate() > 0;
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
        return SQLUtill.execute("DELETE FROM Stock WHERE Stock_id = ?",stockid);
    }

    @Override
    public boolean update(Stock entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Stock searchByID(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
}

