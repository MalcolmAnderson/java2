package utils;

import java.sql.Connection;
import utils.DBQuery;

public class PreparedQueryExample {
    Connection conn = DBConnection.getConnection();
    String insertStatement = "INSERT INTO countries "
            + "(Country, Create_Date, Created_By, Last_Updated_By) "
            + "VALUES (?, ?, ?, ?)";



    //DBQuery.setPreparedStatement(conn, insertStatement);

}
