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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DAOCustomers {

    private DBQueryManager dbQM = new DBQueryManager();
    TimeConversion tc = new TimeConversion();
    Utils utils = new Utils();



    public Customers selectAllCustomers(){
//        Utils utils = new Utils();
        Customers customers = new Customers();

        // TODO Replace all this appointment code with CUSTOMER code.

        try{
            String sql = "SELECT * FROM customers;";

//            String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
//                    + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
//                    + ", c.Last_Updated_By, c.Division_ID, d.Division, tree.Country "
//                    + "FROM customers as c "
//                    + "left join first_level_divisions as d on "
//                    + "c.Division_ID = d.Division_ID "
//                    + "left join countries as tree on "
//                    + "d.Country_ID = tree.Country_ID;";

            String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
                    + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
                    + ", c.Last_Updated_By, c.Division_ID "
                    + "FROM customers as c; ";


            sql = shouldWork;
            System.out.println(sql);
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
//                String division = rs.getString("Division");
//                String country = rs.getString("Country");

//                Customer current = new Customer(
//                        customer_ID,
//                        customer_Name,
//                        address,
//                        postal_Code,
//                        phone,
//                        create_Date,
//                        created_By,
//                        last_Update,
//                        last_Updated_By,
//                        division_ID,
//                        division,
//                        country);
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


    public boolean recordExists(Customer current){
        ResultSet rs = null;
        try {
            String sql = String.format(
                    "SELECT * FROM customers WHERE Customer_ID = '%s'", current.getCustomer_ID());
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throwables.printStackTrace();
            System.exit(-1);
        }
        int size = 0;
        if(rs != null){
            try {
                rs.last();
            } catch (SQLException throwables) {
                System.out.println("DAOCustomers - recordExists - rs.last threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }
            try {
                size = rs.getRow();
            } catch (SQLException throwables) {
                System.out.println("DAOCustomers - recordExists - rs.getRow threw an exception");
                throwables.printStackTrace();
                System.exit(-1);
            }

        }
        System.out.println("DAOCustomers - recordExists - size = " + size);
        return size > 0;
    }


    public void insertOrUpdateCustomer(Customer current) {
        String sqlStatement = "";
        if(recordExists(current)){
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
            + " Last_Updated_By = '%s', Division_ID = '%s' WHERE Customer_ID = %s;",
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
