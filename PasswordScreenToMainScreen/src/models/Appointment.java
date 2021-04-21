package models;

import java.time.LocalDateTime;

/** Model for individual Appointments */
public class Appointment {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customer_Id;
    private int contact_Id;
    private Contact contact;

    /** empty / default constructor */
    public Appointment(){
        this.id = -1;
        this.title = "";
        this.description = "";
        this.location = "";
        this.type = "";
        this.start = null;
        this.end = null;
        this.customer_Id = -1;
        this.contact_Id = -1;
        this.contact = new Contact();
    }

    /** Normal "full" constructor. */
    public Appointment(int id, String title, String description, String location,
                       String type, LocalDateTime start, LocalDateTime end, int customer_Id, int contact_Id){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_Id = customer_Id;
        this.contact_Id = contact_Id;
        this.contact = new Contact();
    }

    /** toString override, usually only used in system out statements. */
    @Override public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", customer_Id=" + customer_Id +
                ", contact_Id=" + contact_Id +
                '}';
    }

    /** get appointment ID value. */
    public int getId() { return id; }
    /** set appointment ID value. */
    public void setId(int id) { this.id = id; }

    /** get appointment title */
    public String getTitle() {return title;}
    /** set appointment title */
    public void setTitle(String title) {this.title = title;}

    /** get appointment description */
    public String getDescription() {return description;}
    /** set appointment description */
    public void setDescription(String description) {this.description = description;}

    /** get appointment location */
    public String getLocation() {return location;}
    /** set appointment location */
    public void setLocation(String location) {this.location = location;}

    /** get appointment type */
    public String getType() {return type;}
    /** set appointment type */
    public void setType(String type) {this.type = type;}

    /** get appointment start date and time */
    public LocalDateTime getStart() {return start;}
    /** set appointment start date and time */
    public void setStart(LocalDateTime start) {this.start = start;}
    /** get appointment start date and time in a display friendly format */
    public String getStartDisplay() {return start.toLocalDate() + " - " + start.toLocalTime();}

    /** get appointment end date and time */
    public LocalDateTime getEnd() {return end;}
    /** set appointment end date and time */
    public void setEnd(LocalDateTime end) {this.end = end;}
    /** get appointment end date and time in a display friendly format */
    public String getEndDisplay() {return end.toLocalDate() + " - " + end.toLocalTime();}

    /** get appointment Customer Id */
    public int getCustomer_Id() {return customer_Id;}
    /** set appointment Customer Id */
    public void setCustomer_Id(int customer_Id) {this.customer_Id = customer_Id;}

    /** get appointment Contact Id */
    public int getContact_Id() {return contact_Id;}
    /** set appointment Contact Id */
    public void setContact_Id(int contact_Id) {this.contact_Id = contact_Id;}

    /** get appointment Contact Object */
    public Contact getContact() {return contact;}
    /** set appointment Contact Object */
    public void setContact(Contact contact) {this.contact = contact;}
    /** get appointment Contact name from Contact Object */
    public String getContactName() {return this.contact.contact_Name;}
}
