package lk.ijse.pos.bo.custom;

import lk.ijse.pos.db.DbConnection;
import lk.ijse.pos.dto.UpdateDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface UpdateBO {
    public  boolean update(UpdateDTO update1) throws SQLException;
}
