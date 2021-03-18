package models;


import java.time.LocalDateTime;
import models.Appointments;
import models.Customer;

public class _ManageTestData {


    public static Appointments BuildPlaceHolderData_Appointments() {

        Appointments appointments = new Appointments();

        Appointment one = new Appointment(1, "Bob #1", "An initial visit to determine needs",
                "Safeway Starbucks", "Bob Smith", "buyer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 1, "Cust1");

        Appointment two = new Appointment(2, "Bob #2", "Review analysis for prospect",
                "Starbucks 4th and main", "Bob Smith", "buyer",
                LocalDateTime.of(2021, 04, 16, 19, 00),
                LocalDateTime.of(2021, 04, 16, 20, 00), 1, "Cust2");

        Appointment three = new Appointment(3, "Sam #2", "Review analysis for prospect",
                "Safeway 428 Cedar", "Sam Brown", "investor",
                LocalDateTime.of(2021, 04, 17, 11, 00),
                LocalDateTime.of(2021, 04, 17, 12, 00), 1, "Cust3");

        Appointment four = new Appointment(4, "Sam #3", "Complete client paperwork",
                "Safeway Starbucks", "Bob Smith", "buyer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 2, "Cust4");

        Appointment five = new Appointment(5, "Carl #3", "Review wireframes",
                "Safeway Starbucks", "Carl Jones", "Web Designer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 2, "Cust5");

        Appointment six = new Appointment(6, "Carl #4", "Issue payment, secure follow up",
                "Safeway Starbucks", "Carl Jones", "UI Designer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 2, "Cust6");

        appointments.addAppointment(one);
        appointments.addAppointment(two);
        appointments.addAppointment(three);
        appointments.addAppointment(four);
        appointments.addAppointment(five);
        appointments.addAppointment(six);


//        LocalDateTime start = LocalDateTime.of(2020, 04, 28, 9, 00);
//        LocalDateTime end = LocalDateTime.of(2020, 04, 28, 10, 00);
//        appointments.addAppointment(new Appointment(1, "a", "b", "c", "d", "e", start, end, 2));
//        appointments.addAppointment(new Appointment(2, "a", "b", "c", "d", "e", start, end, 5));
//        appointments.addAppointment(new Appointment(3, "a", "b", "c", "d", "e", start, end, 3));
//        appointments.addAppointment(new Appointment(4, "a", "b", "c", "d", "e", start, end, 3));
//        appointments.addAppointment(new Appointment(5, "a", "b", "c", "d", "e", start, end, 5));


        //for(int i = 1; i <= 5; i++){

            //Appointment appointment = new Appointment("This is appointment number " + i);
            //appointments.addAppointment(appointment);

        //}

        return appointments;
    }

    public static Customers BuildPlaceHolder_Customers() {
        Customers customers = new Customers();

        Customer zero = new                                    Customer(
                1,
                "Daddy Warbucks",
                "1919 Boardwalk",
                "01291",
                "869-908-1875",
                LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                "script",
                LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                "script",
                60);

//        Customer one = new Customer(
//                4,
//                "Bob Smith",
//                "123 Cherry Lane, Machester CO 12345",
//                "Boston4@hello.com");

//        Customer two = new Customer( 5, "Tom Slytherin", "123 Cherry Lane, Machester CO 12345", "Boston5@aol.com");
//
//        Customer three = new Customer( 6, "Malcolm MacLucas", "1135 SE Kamiaken Street, Pullman WA 99163", "malcolm.b.anderson@gmail.com");
//
//        Customer four = new Customer( 7, "Periandros of Corinth", "123 Cherry Lane, Machester CO 12345", "Periandros.O.Corinth@gmail.com");
//
//        Customer five = new Customer( 8, "Samuel of Sparta", "123 Cherry Lane, Machester CO 12345", "Boston7@hotmail.com");

        customers.addCustomer(zero);
//        customers.addCustomer(one);
//        customers.addCustomer(two);
//        customers.addCustomer(three);
//        customers.addCustomer(four);
//        customers.addCustomer(five);
        return customers;
    }
}