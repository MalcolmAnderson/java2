package models;

import java.util.ArrayList;

public class Contacts {

    private ArrayList<Contact> alContacts = new ArrayList<Contact>();

    public void addContact(Contact contact){
        alContacts.add(contact);
    }

    public ArrayList<Contact> getContacts() {
        return alContacts;
    }

    public void setAllContacts(ArrayList<Contact> alContacts) {
        this.alContacts = alContacts;
    }
}

