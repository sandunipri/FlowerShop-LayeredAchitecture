package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.custom.EventOrderDAO;
import lk.ijse.pos.entity.EventOrder;
/*import lk.ijse.FlowerShop.model.EventOrder;*/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventOrderDAOImpl implements EventOrderDAO {

    @Override
    public  String currentId() throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT EO_id FROM E_order ORDER BY CAST(SUBSTRING(EO_id, 2) AS UNSIGNED) DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/
        ResultSet resultSet = SQLUtill.execute("SELECT EO_id FROM E_order ORDER BY CAST(SUBSTRING(EO_id, 2) AS UNSIGNED) DESC LIMIT 1");

        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }
    @Override
    public  boolean save(EventOrder eventOrder) throws SQLException, ClassNotFoundException {
       /* String sql = "INSERT INTO E_order VALUES(?, ?, ?,?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);

        preparedStatement.setString(1, eventOrder.getEOID());
        preparedStatement.setString(2, eventOrder.getCID());
        preparedStatement.setString(3, eventOrder.getEcode());
        preparedStatement.setDouble(4, eventOrder.getAmount());

        return preparedStatement.executeUpdate() > 0;*/
        return SQLUtill.execute("INSERT INTO E_order VALUES(?, ?, ?,?)",eventOrder.getEOID(),eventOrder.getCID(),eventOrder.getEcode(),eventOrder.getAmount());
    }
    @Override
    public  List<String> getid() {
        //String sql = "SELECT EO_id FROM E_order ORDER BY EO_id desc LIMIT 1";
        List<String> list = new ArrayList<>();
        try {
           /* Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);*/
            ResultSet resultSet = SQLUtill.execute("SELECT EO_id FROM E_order ORDER BY EO_id desc LIMIT 1");

            while (resultSet.next()) {
                list.add(resultSet.getString(1));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public  EventOrder searchByPOID(String id) throws SQLException, ClassNotFoundException {
       /* String sql = "SELECT * FROM E_order WHERE EO_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,id);
*/
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM E_order WHERE EO_id = ?", id);
        if(resultSet.next()) {
                return new EventOrder(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)



            );
        }
        return null;
    }
}
