package utils.dataAccess;

import models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean recordExists(String tableName, String keyColumnName, int key) {
        ResultSet rs = null;
        // prepare statement
        try {
            String sql = String.format(
                    "SELECT * FROM %s WHERE %s = %s", tableName, keyColumnName, key);
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        int size = 0;
        if (rs != null) {
            // run statement
            try {
                rs.last();
            } catch (SQLException throwables) {
                System.out.println("DBQueryManager - recordExists - rs.last threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }
            // get the size of the result set
            try {
                size = rs.getRow();
            } catch (SQLException throwables) {
                System.out.println("DAOCustomers - recordExists - rs.getRow threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }
        }
        return size > 0;
    }
}
