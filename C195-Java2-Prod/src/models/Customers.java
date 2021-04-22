package models;

import java.util.ArrayList;

/** A collection to hold a list of customers */
public class Customers {

    private ArrayList<Customer> alCustomers = new ArrayList<Customer>();

    /** Adds a customer to the list
     * @param customer - customer object to add to collection */
    public void addCustomer(Customer customer){
        alCustomers.add(customer);
    }

    /** Gets all customers.
     * @return ArrayList - Full list of customers */
    public ArrayList<Customer> getCustomers() {
        return alCustomers;
    }

    /** Sets all customers.
     * @param alCustomers - Arraylist of Customers to load into object */
    public void setAllCustomers(ArrayList<Customer> alCustomers) {
        this.alCustomers = alCustomers;
    }

    /** Checks to see if a customer Id exists.
     * @param customerID - customer Id to search for
     * @return boolean - true false value if customer found or not. */
    public boolean customerExists(int customerID) {
        boolean retVal = false;
        for(int i = 0; i < alCustomers.size(); i++){
            if(alCustomers.get(i).getCustomer_ID() == customerID){
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    /** Used after customerExists to retrieve a customer.
     * @param customerID - Customer ID to get
     * @return Customer - Customer object returned */
    public Customer getCustomerByID(int customerID) {
        Customer retVal = null;
        for(int i = 0; i < alCustomers.size(); i++){
            if(alCustomers.get(i).getCustomer_ID() == customerID){
                retVal = alCustomers.get(i);
                break;
            }
        }
        return retVal;
    }
}

