package lk.ijse.FlowerShop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/FlowerShop",
                "root",
                "Ijse@1234"
        );
        System.out.println("connection: " + connection);
    }
    public static DbConnection getInstance() throws SQLException {
       // return (dbConnection == null) ? dbConnection = new DbConnection() : dbConnection;
        if(dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {

        return connection;
    }
}
