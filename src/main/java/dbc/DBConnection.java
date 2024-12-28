package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private  static DBConnection instance;
    private Connection dbc;

    private DBConnection(){
        String url = "jdbc:mysql://localhost:3306/thogakade";
        String username = "root";
        String password = "1234";

        try {
            dbc =  DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance(){
        if(instance==null){
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return dbc;
    }
}
