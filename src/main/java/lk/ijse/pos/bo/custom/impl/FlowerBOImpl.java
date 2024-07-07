package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.FlowerBO;
import lk.ijse.pos.dao.custom.FlowerDAO;
import lk.ijse.pos.dao.custom.impl.FlowerDAOImpl;
import lk.ijse.pos.dto.FlowerDTO;
import lk.ijse.pos.entity.Flower;

import java.sql.SQLException;
import java.util.List;

public class FlowerBOImpl implements FlowerBO {
    FlowerDAO flowerDAO = new FlowerDAOImpl();
    @Override
    public List<String> getFcode() throws SQLException, ClassNotFoundException {
        return flowerDAO.getFcode();
    }

    @Override
    public FlowerDTO searchByCode(String Fcode) throws SQLException, ClassNotFoundException {
        Flower flower = new Flower();
        FlowerDTO flowerDTO = new FlowerDTO(
                flower.getFCode(),
                flower.getFName()
        );
        return flowerDTO;
    }

    @Override
    public FlowerDTO searchByName(String name) throws SQLException, ClassNotFoundException {
        Flower flower = new Flower();
        FlowerDTO flowerDTO = new FlowerDTO(
                flower.getFCode(),
                flower.getFName()
        );
        return flowerDTO;
    }

    @Override
    public List<String> getFlowerName() throws SQLException, ClassNotFoundException {
        return flowerDAO.getFlowerName();
    }

    @Override
    public String currentCode() throws SQLException, ClassNotFoundException {
        return flowerDAO.currentCode();
    }

    @Override
    public boolean add(FlowerDTO dto) throws SQLException, ClassNotFoundException {
      Flower flower = new Flower(
              dto.getFCode(),
              dto.getFName()
      );
      return flowerDAO.add(flower);
    }

    @Override
    public boolean delete(String fcode) throws SQLException, ClassNotFoundException {
        return flowerDAO.delete(fcode);
    }
}
