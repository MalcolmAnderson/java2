package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {

    private int id;
    private String title;
    private String description;
    private String location;
    private String contact_Name;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customer_Id;
    private String customer_Name;

    public Appointment(int id, String title, String description, String location, String contact_Name,
                       String type, LocalDateTime start, LocalDateTime end, int customer_Id, String customer_Name){
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact_Name = contact_Name;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer_Id = customer_Id;
        this.customer_Name = customer_Name;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", contact_Name='" + contact_Name + '\'' +
                ", type='" + type + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", customer_Id=" + customer_Id +
                ", customer_Name=" + customer_Name +
                '}';
    }

    public String getCustomer_Name() {return customer_Name;}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getLocation() {return location;}
    public void setLocation(String location) {this.location = location;}

    public String getContact_Name() {return contact_Name;}
    public void setContact_Name(String contact_Name) {this.contact_Name = contact_Name;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public LocalDate getDate() {return getStart().toLocalDate();}
    public LocalTime getStartTime() {return getStart().toLocalTime();}
    public LocalTime getEndTime() {return getEnd().toLocalTime();}

    public LocalDateTime getStart() {return start;}
    public void setStart(LocalDateTime start) {this.start = start;}

    public LocalDateTime getEnd() {return end;}
    public void setEnd(LocalDateTime end) {this.end = end;}

    public int getCustomer_Id() {return customer_Id;}
    public void setCustomer_Id(int customer_Id) {this.customer_Id = customer_Id;}
}
