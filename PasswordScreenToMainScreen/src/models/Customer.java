package models;

import java.time.LocalDateTime;

public class Customer {

    public int customer_ID;
    public String customer_Name;
    public String address;
    public String postal_Code;
    public String phone;
    public LocalDateTime create_Date;
    public String created_By;
    public LocalDateTime last_Update;
    public String last_Updated_By;
    public int Division_ID;

    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String phone, LocalDateTime create_Date, String created_By, LocalDateTime last_Update, String last_Updated_By, int division_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postal_Code = postal_Code;
        this.phone = phone;
        this.create_Date = create_Date;
        this.created_By = created_By;
        this.last_Update = last_Update;
        this.last_Updated_By = last_Updated_By;
        Division_ID = division_ID;
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
                ", last_Updated_By=" + last_Updated_By +
                ", Division_ID=" + Division_ID +
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
        return Division_ID;
    }

    public void setDivision_ID(int division_ID) {
        Division_ID = division_ID;
    }

    public String getFullAddress() {
        return address;
    }
}
