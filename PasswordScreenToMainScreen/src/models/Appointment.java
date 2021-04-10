package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    }

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
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
//                ", contact_Name='" + contact_Name + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", customer_Id=" + customer_Id +
//                ", customer_Name=" + customer_Name +
                '}';
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public LocalDateTime getStart() {return start;}
    public void setStart(LocalDateTime start) {this.start = start;}

    public LocalDateTime getEnd() {return end;}
    public void setEnd(LocalDateTime end) {this.end = end;}

    public int getCustomer_Id() {return customer_Id;}
    public void setCustomer_Id(int customer_Id) {this.customer_Id = customer_Id;}

    public int getContact_Id() {return contact_Id;}
    public void setContact_Id(int contact_Id) {this.contact_Id = contact_Id;}
}
