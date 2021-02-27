package utils;

import java.sql.SQLException;
import java.sql.Statement;

public class DBQueryManager {

    public void RunSQLString(String unvalidatedSQL){
        try {
            System.out.println("RunSQLString: unvalidatedSQL = " + unvalidatedSQL);

            DBQuery.setPreparedStatement(DBConnection.getConnection(), unvalidatedSQL);
            Statement statement = DBQuery.getPreparedStatement();

            // Execute Statement
            Boolean isASelectStatement = statement.execute(unvalidatedSQL);
            System.out.println("isASelectStatement = " + isASelectStatement.toString());
            if( ! isASelectStatement){
                System.out.println("Impacted records = " + statement.getUpdateCount());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
