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
}

