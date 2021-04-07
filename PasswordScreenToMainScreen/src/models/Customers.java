package models;

import java.util.ArrayList;

public class Customers {

    private ArrayList<Customer> alCustomers = new ArrayList<Customer>();

    public void addCustomer(Customer customer){
        alCustomers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return alCustomers;
    }

    public void setAllCustomers(ArrayList<Customer> alCustomers) {
        this.alCustomers = alCustomers;
    }

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

