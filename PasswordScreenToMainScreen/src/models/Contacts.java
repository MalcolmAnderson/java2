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

    public Contact getContactById(int contact_id) {
        Contact current;
        for(int i = 0; i < alContacts.size(); i++){
            current = alContacts.get(i);
            if(current.contact_ID == contact_id){
                return current;
            }
        }
        return null;
    }
}

