package models;


import java.time.LocalDateTime;

public class _ManageTestData {


    public static Appointments BuildPlaceHolderData_Appointments() {

        Appointments appointments = new Appointments();
        int id = 40;

        Appointment one = new Appointment(id++, "Bob #1", "An initial visit to determine needs",
                "Safeway Starbucks", "buyer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 1, 2);

        Appointment two = new Appointment(id++, "Bob #2", "Review analysis for prospect",
                "Starbucks 4th and main", "buyer",
                LocalDateTime.of(2021, 04, 16, 19, 00),
                LocalDateTime.of(2021, 04, 16, 20, 00), 1, 3);

        Appointment three = new Appointment(id++, "Sam #2", "Review analysis for prospect",
                "Safeway 428 Cedar", "investor",
                LocalDateTime.of(2021, 04, 17, 11, 00),
                LocalDateTime.of(2021, 04, 17, 12, 00), 2, 2);

        Appointment four = new Appointment(id++, "Sam #3", "Complete client paperwork",
                "Safeway Starbucks", "buyer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 2, 6);

        Appointment five = new Appointment(id++, "Carl #3", "Review wireframes",
                "Safeway Starbucks", "Web Designer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 2, 42);

        Appointment six = new Appointment(id++, "Carl #4", "Issue payment, secure follow up",
                "Safeway Starbucks", "UI Designer",
                LocalDateTime.of(2021, 04, 15, 9, 00),
                LocalDateTime.of(2021, 04, 15, 10, 00), 3, 57);

        appointments.addAppointment(one);
        appointments.addAppointment(two);
        appointments.addAppointment(three);
        appointments.addAppointment(four);
        appointments.addAppointment(five);
        appointments.addAppointment(six);

        //for(int i = 1; i <= 5; i++){

            //Appointment appointment = new Appointment("This is appointment number " + i);
            //appointments.addAppointment(appointment);

        //}

        return appointments;
    }
    public static Appointment BuildTestData_Appointment(int testAppointmentNumber){
        int id = 70;
        String title = "This is a title";
        String description = "This is a description";
        String location = "location";
        String type = "type";
        LocalDateTime start = LocalDateTime.of(1964, 03, 14, 23, 15, 48);
        LocalDateTime end = LocalDateTime.of(2765, 04, 15, 02, 17, 52);
        int customerId = 1;
        int contactId = 2;
        Appointment a = new Appointment(id, title, description, location, type, start, end, customerId, contactId);
        return a;
    }

    public static Customer BuildTestData_Customers(int testCustomerNumber) {
        //Customers customers = new Customers();
        Customer retVal = null;
        switch(testCustomerNumber){
            case 22:{
                retVal = new Customer(
                        22,
                        "Eric Estrada",
                        "1919 Main Street, Los Angeles",
                        "90031",
                        "323-908-1875",
                        LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                        "Unit Tests",
                        LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                        "Unit Tests",
                        4);
//                customers.addCustomer(zero);
            }
            break;
            case 23: {
                retVal = new Customer(
                        23,
                        "Tom Slytherin",
                        "2782 Alberta Street, Portland",
                        "98601",
                        "503-908-1875",
                        LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                        "Unit Tests",
                        LocalDateTime.of(2021, 02, 23, 02, 11, 22),
                        "Unit Tests",
                        36);
//                customers.addCustomer(one);
            }
            break;
            default:{
                System.out.println("testCustomerNumber must be either 22 or 23");
                System.exit(-1);
            }
        }
        return retVal;



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

//        customers.addCustomer(two);
//        customers.addCustomer(three);
//        customers.addCustomer(four);
//        customers.addCustomer(five);
//        return customers;
    }

    public static Contacts BuildPlaceHolder_Contacts() {
        Contacts contacts = new Contacts();
        int id = 42;
        Contact one = new Contact(id++, "one", "one@ahah.com");
        Contact two = new Contact(id++, "two", "tow@ahah.com");
        Contact three = new Contact(id++, "three", "three@ahah.com");
        Contact four = new Contact(id++, "four", "four@ahah.com");
        Contact five = new Contact(id++, "five", "five@ahah.com");
        contacts.addContact(one);
        contacts.addContact(two);
        contacts.addContact(three);
        contacts.addContact(four);
        contacts.addContact(five);
        return contacts;
    }
}