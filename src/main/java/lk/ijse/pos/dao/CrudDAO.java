package lk.ijse.pos.dao;

import lk.ijse.pos.entity.Customer;

import java.sql.SQLException;

public interface CrudDAO <T> extends SuperDAO{

    public  boolean add(T entity) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException , ClassNotFoundException ;

    public  boolean update(T entity) throws SQLException, ClassNotFoundException ;

    public T searchByID(String id) throws SQLException, ClassNotFoundException ;



  /*  boolean add(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    boolean delete(String id) throws SQLException;
    T search(String id) throws SQLException;
    List<T> getAll() throws SQLException;
*/
}
