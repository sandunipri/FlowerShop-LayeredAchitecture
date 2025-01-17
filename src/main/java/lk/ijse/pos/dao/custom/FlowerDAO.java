package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dao.SQLUtill;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.Flower;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FlowerDAO extends CrudDAO<Flower> {
   // public  boolean add(Flower flower) throws SQLException, ClassNotFoundException ;

    List<String> getFcode() throws SQLException, ClassNotFoundException;

    public  Flower searchByCode(String Fcode) throws SQLException, ClassNotFoundException;

    public  Flower searchByFlowerName(String name) throws SQLException, ClassNotFoundException ;

    public  List<String> getFlowerName() throws SQLException, ClassNotFoundException ;

    public  String currentCode() throws SQLException, ClassNotFoundException;

   /* public  boolean add(Flower flower) throws SQLException, ClassNotFoundException ;*/

   /* public  boolean delete(String fcode) throws SQLException, ClassNotFoundException ;*/
}
