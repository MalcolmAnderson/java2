package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import secrets.mysqlSecrets;

public class DBConnection {
    // JDBC Connection String
    // jdbc:mysql: //wgudb.ucertify.com:3306/WJ05mJn 
    private static mysqlSecrets secrets = new mysqlSecrets();
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql";
    private static final String urlAndPort = "://wgudb.ucertify.com:3306";
    private static final String dbName = "/WJ05mJn?useSSL=false";

    // JDBC URL
    private static final String connectionString = protocol + vendor + urlAndPort + dbName;

    // Driver Interface Reference
    private static final String mySqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

//    private static final String userName = "U05mJn";
//    private static final String password = "53688547109";

    public static Connection getConnection(){
        return conn;
    }


    public static Connection startConnection()
    {
        System.out.println("Begin startConnection");
        try {
            Class.forName(mySqlJDBCDriver);
            conn = (Connection) DriverManager.getConnection(
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
