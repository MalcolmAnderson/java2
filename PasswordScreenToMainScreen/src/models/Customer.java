package models;

import main.Globals;
import utils.dataAccess.DAOGeography;

import java.time.LocalDateTime;

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
//    private String division;
//    private String country;

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
        this.division_ID = -1;
//        this.division = "";
//        this.country = "";
    }

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
//        this.division = division;
//        this.country = country;
    }

    // custom getter
    public String getFullAddress() {
        return address + ", " + geography.getDivisionName() + " " + postal_Code;
    }





    @Override
    public String toString() {
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
                ", division='" + geography.getDivisionName() + '\'' +
                ", country='" + geography.getCountryName() + '\'' +
                '}';
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_Code() {
        return postal_Code;
    }

    public void setPostal_Code(String postal_Code) {
        this.postal_Code = postal_Code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(LocalDateTime create_Date) {
        this.create_Date = create_Date;
    }

    public String getCreated_By() {
        return created_By;
    }

    public void setCreated_By(String created_By) {
        this.created_By = created_By;
    }

    public LocalDateTime getLast_Update() {
        return last_Update;
    }

    public void setLast_Update(LocalDateTime last_Update) {
        this.last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        this.last_Updated_By = last_Updated_By;
    }

    public int getDivision_ID() {
        return division_ID;
    }

    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getDivision() {
        return geography.getDivisionName();
    }

//    public void setDivision(String division) {
//        this.geography. division = division;
//    }

    public String getCountry() {
        return geography.getCountryName();
    }

//    public void setCountry(String country) {
//        this.country = country;
//    }
}
