package models;

import java.util.ArrayList;

/** A collection to hold a list of customers */
public class Customers {

    private ArrayList<Customer> alCustomers = new ArrayList<Customer>();

    /** Adds a customer to the list */
    public void addCustomer(Customer customer){
        alCustomers.add(customer);
    }

    /** Gets all customers. */
    public ArrayList<Customer> getCustomers() {
        return alCustomers;
    }

    /** Sets all customers. */
    public void setAllCustomers(ArrayList<Customer> alCustomers) {
        this.alCustomers = alCustomers;
    }

    /** Checks to see if a customer Id exists. */
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

    /** Used after customerExists to retrieve a customer. */
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

