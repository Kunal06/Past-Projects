/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtimemanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DBconnect {

    private Connection DBConnection;
    String url = "jdbc:mysql://127.0.0.1:3306/pv";
     String dbName = "pv";
     String driver = "com.mysql.jdbc.Driver";
     String userName = "root";
     String password = "root";
   

    public Connection connect() {

        try {
            Class.forName(driver);
            System.out.println("Connected to the database");

        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed" + e);
        }
        try {
            DBConnection = DriverManager.getConnection(url, userName, password);
            System.out.println("Database connected");

        } catch (SQLException e) {
            System.out.println("No Database" + e);
        }
        return DBConnection;

    }

}
