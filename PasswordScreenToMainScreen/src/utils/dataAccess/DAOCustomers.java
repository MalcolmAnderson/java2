package utils.dataAccess;

import main.Globals;
import models.Customer;
import models.Customers;
import utils.TimeConversion;
import utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DAOCustomers {

    private DBQueryManager dbQM = new DBQueryManager();
    TimeConversion tc = new TimeConversion();
    Utils utils = new Utils();

    public Customers selectAllCustomers(){
        String sql = "SELECT * FROM customers;";
        Customers customers = loadCustomersFromResultSet(sql);
        return customers;
    }

    private Customers loadCustomersFromResultSet(String sql) {
        Customers customers = new Customers();
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
//                System.out.println("       getting Column names for customer");
                int customer_ID = rs.getInt("Customer_ID");
                String customer_Name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postal_Code = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                LocalDateTime create_Date = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Create_Date"));
                String created_By = rs.getString("Created_By");
                LocalDateTime last_Update = utils.TimeStamp_to_LocalDateTime(rs.getTimestamp("Last_Update"));
                String last_Updated_By = rs.getString("Last_Updated_By");
                int division_ID = rs.getInt("Division_ID");

                Customer current = new Customer(
                        customer_ID,
                        customer_Name,
                        address,
                        postal_Code,
                        phone,
                        create_Date,
                        created_By,
                        last_Update,
                        last_Updated_By,
                        division_ID);
                customers.addCustomer(current);
            }

        } catch (SQLException throwables) {
            System.out.println( throwables.getMessage());
            throwables.printStackTrace();
        }
        return customers;
    }

    public void insertOrUpdateCustomer(Customer current) {
        String sqlStatement = "";
        tableInfo c = new tableInfo_Customers();
        if(dbQM.recordExists(c.getTableName(), c.getPrimaryKeyName(), current.getCustomer_ID())){
            sqlStatement = createCustomerUpdateSQLString(current);
            System.out.println("DAOCustomers - insertOrUpdateCustomer - Update Statement");
        } else {
            sqlStatement = createCustomerInsertStatement(current);
            System.out.println("DAOCustomers - insertOrUpdateCustomer - Insert Statement");
        }
        System.out.println(sqlStatement);
        dbQM.RunSQLString(sqlStatement);
    }

    public String createCustomerInsertStatement(Customer current) {
        LocalDateTime utcLast_Update = getUTC_Now();
        LocalDateTime utcCreated_Date = getUTC_Now();

        String sqlStatement = String.format(
                "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) "
                        + " VALUES ("
                        + "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                current.getCustomer_ID(), current.getCustomer_Name(), current.getAddress(),
                current.getPostal_Code(), current.getPhone(), utcCreated_Date.toString()
                ,
                current.getCreated_By(), utcLast_Update.toString(), current.getLast_Updated_By(),
                current.getDivision_ID());
        return sqlStatement;
    }

    public String createCustomerUpdateSQLString(Customer current) {
        LocalDateTime utcLast_Update = getUTC_Now();
        System.out.println("Update time = " + utcLast_Update.toString());

        String sqlStatement = String.format(
            "UPDATE customers SET"
            + " Customer_Name = '%s', Address = '%s',"
            + " Postal_Code = '%s', Phone = '%s',  Last_Update = '%s',"
            + " Last_Updated_By = '%s', Division_ID = %s WHERE Customer_ID = %s;",
                current.getCustomer_Name(),
                current.getAddress(), current.getPostal_Code(),
                current.getPhone(), utcLast_Update.toString(),
                Globals.getUserName(),
                current.getDivision_ID(), current.getCustomer_ID());
        System.out.println(sqlStatement);
        return sqlStatement;
    }

    private LocalDateTime getUTC_Now() {
        return tc.Time_FromTo(
                LocalDateTime.now(), tc.getLocalZoneID(), tc.getUTCZoneID());
    }

    public void deleteCustomerByID(int customerID) {
            String deleteStatement = String.format(
                    "DELETE FROM customers WHERE Customer_ID = '%s'", customerID);
            System.out.println("DAOCustomers - deleteCustomerByID - Delete Statement");
            System.out.println(deleteStatement);
            dbQM.RunSQLString(deleteStatement);
    }
}
