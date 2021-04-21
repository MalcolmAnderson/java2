package models;

import main.Globals;
import java.time.LocalDateTime;

/** Model for an individual Customer. */
public class Customer {

    private int customer_ID;
    private String customer_Name;
    private String address;
    private String postal_Code;
    private String phone;
    private LocalDateTime create_Date;
    private String created_By;
    private LocalDateTime last_Update;
    private String last_Updated_By;
    private int division_ID;
    private Geography geography;

    /** Default empty Customer Constructor */
    public Customer() {
        this.customer_ID = -1;
        this.customer_Name = "";
        this.address = "";
        this.postal_Code = "";
        this.phone = "";
        this.create_Date = LocalDateTime.now();
        this.created_By = Globals.getUserName();
        this.last_Update = LocalDateTime.now();;
        this.last_Updated_By = Globals.getUserName();
        this.division_ID = 1;
    }

    /** Normal Customer Constructor */
    public Customer(
            int customer_ID,
            String customer_Name,
            String address,
            String postal_Code,
            String phone,
            LocalDateTime create_Date,
            String created_By,
            LocalDateTime last_Update,
            String last_Updated_By,
            int division_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
        this.division_ID = division_ID;
        this.geography = Geography.getGeographyByDivisionId(division_ID);
    }

    /** Getter for full address */
    public String getFullAddress() {
        return address + ", " + geography.getDivisionName();
    }

    /** Override to string to show customer name. */
    @Override public String toString() {
        return customer_Name;
    }
    /** Provide a full diagnostic toString for object. */
    public String toString_Full() {
        return "Customer{" +
                "customer_ID=" + customer_ID +
                ", customer_Name='" + customer_Name + '\'' +
                ", address='" + address + '\'' +
                ", postal_Code='" + postal_Code + '\'' +
                ", phone='" + phone + '\'' +
                ", create_Date=" + create_Date +
                ", created_By='" + created_By + '\'' +
                ", last_Update=" + last_Update +
                ", last_Updated_By='" + last_Updated_By + '\'' +
                ", division_ID=" + division_ID +
//                ", division='" + geography.getDivisionName() + '\'' +
//                ", country='" + geography.getCountryName() + '\'' +
                '}';
    }

    /** Get Customer Id. */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /** Set Customer Id. */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /** Get Customer Name. */
    public String getCustomer_Name() {
        return customer_Name;
    }

    /** Set Customer Name. */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /** Get Customer Address. */
    public String getAddress() {
        return address;
    }

    /** Set Customer Address. */
    public void setAddress(String address) {
        this.address = address;
    }

    /** Get Customer Postal Code. */
    public String getPostal_Code() {
        return postal_Code;
    }

    /** Set Customer Postal Code. */
    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    /** Get Customer Phone. */
    public String getPhone() {
        return phone;
    }

    /** Set Customer Phone. */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** Get Customer Create Date. */
    public LocalDateTime getCreate_Date() {
        return create_Date;
    }

    /** Set Customer Create Date. */
    public void setCreate_Date(LocalDateTime create_Date) {
        this.create_Date = create_Date;
    }

    /** Get name of user who created Customer. */
    public String getCreated_By() {
        return created_By;
    }

    /** Set name of user who created Customer. */
    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    /** Get most recent time Customer was changed. */
    public LocalDateTime getLast_Update() {
        return last_Update;
    }

    /** Set most recent time Customer was changed. */
    public void setLast_Update(LocalDateTime last_Update) {
        this.last_Update = last_Update;
    }

    /** Get name of user who last updated Customer. */
    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    /** Set name of user who last updated Customer. */
    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    /** Get Id number of division (State, province or country.) */
    public int getDivision_ID() {
        return division_ID;
    }

    /** Set Id number of division (State, province or country.) */
    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    /** Get name of division (State, province or country.) */
    public String getDivisionName() {
        return geography.getDivisionName();
    }

    /** Set name of division (State, province or country.) */
    public void setGeography(Geography geography) {
        this.geography = geography;
    }

    /** Get country name related to this division. */
    public String getCountryName() {
        return geography.getCountryName();
    }

}
