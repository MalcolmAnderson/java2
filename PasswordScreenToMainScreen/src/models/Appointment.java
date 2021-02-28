package models;

public class Appointment {

    private String placeholder;

    public Appointment(String placeholder){
        this.placeholder = placeholder;
    }

    @Override
    public String toString() {
        return placeholder ;
    }

}
