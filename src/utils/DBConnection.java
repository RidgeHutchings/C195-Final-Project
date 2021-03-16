package utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.SQLException;

/*This class sets up and provides an interface with the mysql database*/
public class DBConnection {

    // JDBC URL parts
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String ipAddress ="//wgudb.ucertify.com/WJ06VOw";

    //completed JDBC URL
    private static final String jdbcURL = protocol + vendor + ipAddress;

    //Driver and Connection interface reference
    private static final String mysqlDriver = "com.mysql.jdbc.Driver";
    private static  Connection conn = null;


    //Username
    private static final String username = "U06VOw";

    //Password
    private static String password = "53688879543";


    //This method attempts to establish a connection with the mysql database
    public static Connection startConnection()
    {
        try {
            Class.forName(mysqlDriver);
            try {
                conn = (Connection)DriverManager.getConnection(jdbcURL,username,password);
                System.out.println("Connection successful!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //This method attempts to close the connection with the mysql database
    public static void closeConnection()
    {
        try {
            conn.close();
            System.out.println("Database connection closed successfully!");
        } catch (SQLException throwables) {
            System.out.println("Unable to close database connection");
            throwables.printStackTrace();
        }


    }


}
