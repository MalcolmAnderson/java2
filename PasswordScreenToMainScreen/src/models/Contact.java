package models;

/** Model for individual contact objects. */
public class Contact {

    public int contact_ID;
    public String contact_Name;
    public String email;

    /** Full constructor. */
    public Contact(int contact_ID, String contact_Name, String email) {
        this.contact_ID = contact_ID;
        this.contact_Name = contact_Name;
        this.email = email;
    }
    /** Empty default constructor. */
    public Contact() {
        this.contact_ID = -1;
        this.contact_Name = "";
        this.email = "";
    }

    /** Overrides to string to show contact name. */
    @Override public String toString() {
        return contact_Name;
    }

    /** allows a full contact object to be displayed, used for debugging. */
    public String toString_Full() {
        return "Contact{" +
                "contact_ID=" + contact_ID +
                ", contact_Name='" + contact_Name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    /** returns the objects Contact Id. */
    public int getContact_ID() {
        return contact_ID;
    }

    /** sets the Contact Id. */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    /** gets the Contact Name. */
    public String getContact_Name() {
        return contact_Name;
    }

    /** gets the Contact Email Address. */
    public String getEmail() {
        return email;
    }

    /** sets the Contact Email Address. */
    public void setEmail(String email) {
        this.email = email;
    }
}
