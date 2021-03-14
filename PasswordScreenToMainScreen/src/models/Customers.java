package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Customers {

    private ObservableList<Customer> olCustomers = FXCollections.observableArrayList();
    private ArrayList<Customer> alCustomers = new ArrayList<Customer>();

    public void addCustomer(Customer customer){
        alCustomers.add(customer);
    }

    public ObservableList<Customer> getOL_Customers() {
        olCustomers.setAll(alCustomers);
        return olCustomers;
    }

    public ArrayList<Customer> getCustomers() {
        return alCustomers;
    }

    public void setAllCustomers(ArrayList<Customer> alCustomers) {
        this.alCustomers = alCustomers;
    }
}

