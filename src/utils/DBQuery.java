package utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.SQLException;

public class DBQuery {

    //Create a Statement reference
    private static Statement statement;

    //Create Statement Object
    public static void setStatement(Connection conn)
    {

        try {
            statement = (Statement) conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Return Statement Object
    public static Statement getStatement()
    {
        return statement;

    }
}
