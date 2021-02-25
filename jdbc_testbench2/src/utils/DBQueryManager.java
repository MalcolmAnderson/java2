package utils;

import java.sql.SQLException;
import java.sql.Statement;

public class DBQueryManager {

    public void RunSQLString(String unvalidatedSQL){
        try {
            DBQuery.setStatement(DBConnection.getConnection());
            Statement statement = DBQuery.getStatement();

            System.out.println("RunSQLString: unvalidatedSQL = " + unvalidatedSQL);
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
