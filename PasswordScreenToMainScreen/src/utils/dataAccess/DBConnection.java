package utils.dataAccess;

import utils.dataAccess.secrets.mysqlSecrets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // JDBC Connection String
    // jdbc:mysql: //wgudb.ucertify.com:3306/WJ05mJn 
    private static mysqlSecrets secrets = new mysqlSecrets();
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql";
    private static final String urlPortAndDbName = "://wgudb.ucertify.com:3306/WJ05mJn";
    private static final String parameters = "?useSSL=FALSE&connectionTimeZone=SERVER";
    private static final String connectionString = protocol + vendor + urlPortAndDbName + parameters;

    // Driver Interface Reference
    private static final String mySqlJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;

    public static Connection getConnection(){
        try {
            if(conn == null || conn.isClosed()){
                System.out.println("Connection was closed, reopening");
                startConnection();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    public static Connection startConnection(){
        System.out.println("Begin startConnection");
        try {
            Class.forName(mySqlJDBCDriver);
            conn =
                    DriverManager.getConnection(
                    connectionString, secrets.getUserName(), secrets.getPassword());
            System.out.println("Connection successful");

        } catch(ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException: message = " + e.getMessage());
            e.printStackTrace();
        } catch(SQLException e)
        {
            System.out.println("SQLException: message = " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    public static void endConnection(){
        System.out.println("Begin endConnection");
        try {
            conn.close();
            while(!conn.isClosed()){
                System.out.println("waiting");
            }

        } catch (SQLException throwables) {
            System.out.println("Connection Close Failure");
            throwables.printStackTrace();
        }
        System.out.println("Connection Successfully Closed");
    }
}
