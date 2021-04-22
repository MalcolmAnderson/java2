package utils.dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Manages Prepared statement objects
 */
public class DBQuery {
    // At some point, rename this DBStatement,
    // or be able to explain why DBQuery is the right name for this class

    private static PreparedStatement statement;

    /**
     * Sets up the prepared statement for later use
     * @param conn - Required for prepared statement
     * @param sqlStatement - sqlStatement to be run as a part of the prepared statement
     * @throws SQLException
     */
    // Create Statement object
    public static void setPreparedStatement(Connection conn, String sqlStatement) throws SQLException {
        statement = conn.prepareStatement(sqlStatement);
    }

    // Return Statement object

    /**
     * allows access to prepared statement
     * @return PreparedStatement
     */
    public static PreparedStatement getPreparedStatement(){
        return statement;
    }
}
