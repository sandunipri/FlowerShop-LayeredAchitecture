package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.FlowerDTO;
import lk.ijse.pos.entity.Flower;

import java.sql.SQLException;
import java.util.List;

public interface FlowerBO extends SuperBO {
    List<String> getFcode() throws SQLException, ClassNotFoundException;

    public FlowerDTO searchByCode(String Fcode) throws SQLException, ClassNotFoundException;

    public  FlowerDTO searchByName(String name) throws SQLException, ClassNotFoundException ;

    public  List<String> getFlowerName() throws SQLException, ClassNotFoundException ;

    public  String currentCode() throws SQLException, ClassNotFoundException;

    public  boolean add(FlowerDTO dto) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String fcode) throws SQLException, ClassNotFoundException ;
}

