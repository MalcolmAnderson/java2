package utils.dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBQuery {
    // At some point, rename this DBStatement,
    // or be able to explain why DBQuery is the right name for this class

    private static PreparedStatement statement;

    // Create Statement object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    // Return Statement object
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }
}
