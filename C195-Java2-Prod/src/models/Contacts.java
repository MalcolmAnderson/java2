package models;

import java.util.ArrayList;

/** Provides a container for a collection of Contacts. */
public class Contacts {

    private ArrayList<Contact> alContacts = new ArrayList<Contact>();

    /** add a new contact to the collection.
     * @param contact - Value for setter */
    public void addContact(Contact contact){
        alContacts.add(contact);
    }

    /** returns the entire list of contacts.
     * @return ArrayList - Value from getter */
    public ArrayList<Contact> getContacts() {
        return alContacts;
    }

    /** allows all contacts to be replaced at once from an external source.
     * @param alContacts - input to replace entire Contact list*/
    public void setAllContacts(ArrayList<Contact> alContacts) {
        this.alContacts = alContacts;
    }

    /** returns a single contact based on ID number, or null if not found.
     * @param contact_id - contact Id to search for
     * @return Contact - Contact object matching contact_id, null if not found */
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

