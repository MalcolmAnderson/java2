package utils.dataAccess;

import main.Globals;
import models.Contact;
import models.Customer;
import models.Customers;
import utils.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DAOCustomers {

    private DBQueryManager dbQM = new DBQueryManager();


    public Customers selectAllCustomers(){
        Utils utils = new Utils();
        Customers customers = new Customers();

        // TODO Replace all this appointment code with CUSTOMER code.

        try{
            String sql = "SELECT * FROM customers;";

            String shouldWork = "SELECT Customer_ID, Customer_Name, Address, Postal_Code"
                    + ", Phone, c.Create_Date as Create_Date, c.Created_By, c.Last_Update"
                    + ", c.Last_Updated_By, c.Division_ID, d.Division, tree.Country "
                    + "FROM customers as c "
                    + "left join first_level_divisions as d on "
                    + "c.Division_ID = d.Division_ID "
                    + "left join countries as tree on "
                    + "d.Country_ID = tree.Country_ID;";


            sql = shouldWork;
            System.out.println(sql);
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("       getting Column names for customer");
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
                String division = rs.getString("Division");
                String country = rs.getString("Country");

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
                    division_ID,
                    division,
                    country);
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
            sqlStatement = String.format(
                "UPDATE customers SET"
                + " Customer_Name = '%s', Address = '%s',"
                + " Postal_Code = '%s', Phone = '%s',  Last_Update = NOW(),"
                + " Last_Updated_By = '%s', Division_ID = '%s' WHERE Customer_ID = %s;",
                    current.getCustomer_Name(),
                    current.getAddress(), current.getPostal_Code(),
                    current.getPhone(), Globals.getUserName(),
                    current.getDivision_ID(), current.getCustomer_ID());
            System.out.println(sqlStatement);
            System.out.println("DAOContacts - insertOrUpdateContact - Update Statement");
        } else {
//            sqlStatement = String.format(
//                    "INSERT INTO contacts (Contact_ID, Contact_Name, Email) "
//                            + " VALUES ("
//                            + "'%s', '%s', '%s');",
//                    current.contact_ID, current.contact_Name, current.email);
            System.out.println("DAOContacts - insertOrUpdateContact - Insert Statement");
        }
        System.out.println(sqlStatement);
        dbQM.RunSQLString(sqlStatement);
    }

}
