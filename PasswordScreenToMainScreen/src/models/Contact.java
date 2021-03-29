package models;

import java.time.LocalDateTime;

public class Contact {

    public int contact_ID;
    public String contact_Name;
    public String email;

    public Contact(int contact_ID, String contact_Name, String email) {
        this.contact_ID = contact_ID;
        this.contact_Name = contact_Name;
        this.email = email;
    }
    public Contact() {
        this.contact_ID = -1;
        this.contact_Name = "";
        this.email = "";
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contact_ID=" + contact_ID +
                ", contact_Name='" + contact_Name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    public String getContact_Name() {
        return contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        this.contact_Name = contact_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
