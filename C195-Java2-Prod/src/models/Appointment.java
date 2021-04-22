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

    /** Normal "full" constructor.
     *
     * @param id - Appointment Id
     * @param title - Appointment Title
     * @param description - Appointment Description
     * @param location - Appointment Location
     * @param type - Appointment Type
     * @param start - Appointment Start date and time
     * @param end - Appointment End date and time
     * @param customer_Id - Customer Id associated with appointment
     * @param contact_Id - Contact Id associated with appointment
     */
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

    /** get appointment ID value.
     * @return Id Value */
    public int getId() { return id; }
    /** set appointment ID value.
     * @param id - id for setter */
    public void setId(int id) { this.id = id; }

    /** get appointment title
     * @return  Title Value */
    public String getTitle() {return title;}
    /** set appointment title
     * @param title - value for setter */
    public void setTitle(String title) {this.title = title;}

    /** get appointment description
     * @return Description Value */
    public String getDescription() {return description;}
    /** set appointment description
     * @param description - value for setter */
    public void setDescription(String description) {this.description = description;}

    /** get appointment location
     * @return String - Value from getter*/
    public String getLocation() {return location;}
    /** set appointment location
     * @param location - value for setter */
    public void setLocation(String location) {this.location = location;}

    /** get appointment type
     * @return String - Value from getter*/
    public String getType() {return type;}
    /** set appointment type
     * @param type - value for setter */
    public void setType(String type) {this.type = type;}

    /** get appointment start date and time
     * @return LocalDateTime - Value from getter*/
    public LocalDateTime getStart() {return start;}
    /** set appointment start date and time
     * @param start - value for setter */
    public void setStart(LocalDateTime start) {this.start = start;}
    /** get appointment start date and time in a display friendly format
     * @return String - Value from getter */
    public String getStartDisplay() {return start.toLocalDate() + " - " + start.toLocalTime();}

    /** get appointment end date and time
     * @return LocalDateTime - Value from getter */
    public LocalDateTime getEnd() {return end;}
    /** set appointment end date and time
     * @param end - value for setter */
    public void setEnd(LocalDateTime end) {this.end = end;}
    /** get appointment end date and time in a display friendly format
     * @return String - Value from getter */
    public String getEndDisplay() {return end.toLocalDate() + " - " + end.toLocalTime();}

    /** get appointment Customer Id
     *  @return int - value from getter  */
    public int getCustomer_Id() {return customer_Id;}
    /** set appointment Customer Id
     * @param customer_Id - value for setter */
    public void setCustomer_Id(int customer_Id) {this.customer_Id = customer_Id;}

    /** get appointment Contact Id
     * @return int - value from getter */
    public int getContact_Id() {return contact_Id;}
    /** set appointment Contact Id
     * @param contact_Id - Value for setter */
    public void setContact_Id(int contact_Id) {this.contact_Id = contact_Id;}

    /** get appointment Contact Object
     *  @return int - value from getter */
    public Contact getContact() {return contact;}
    /** set appointment Contact Object
     * @param contact - Value for setter */
    public void setContact(Contact contact) {this.contact = contact;}
    /** get appointment Contact name from Contact Object
     *  @return int - value from getter */
    public String getContactName() {return this.contact.contact_Name;}
}
