package main;

import models.Contacts;
import models.Customers;

import java.util.Locale;
import java.util.ResourceBundle;

public class Globals {
    private static boolean hasLoginBeenAttempted;
    private static String localLanguage;
    private static boolean wasLoginSuccessful;
    private static int userId = -1;
    private static String userName = "userName not set";
    private static ResourceBundle rb;
    private static Customers masterCustomers;
    private static Contacts masterContacts;

    public static ResourceBundle getResourceBundle() {
//        String rbPath = "utils.localization/Nat";
//        rb = ResourceBundle.getBundle(rbPath, Locale.getDefault());
        return rb;
    }

    public static void setResourceBundle(ResourceBundle rb) {
        Globals.rb = rb;
    }

    public static boolean getHasLoginBeenAttempted() {
        return hasLoginBeenAttempted;
    }

    public static void setHasLoginBeenAttempted(boolean hasLoginBeenAttempted) {
        Globals.hasLoginBeenAttempted = hasLoginBeenAttempted;
    }

    public static String getLocalLanguage() {
        return localLanguage;
    }

    public static void setDetectedSystemLocalLanguage(String localLanguage) {
        Globals.localLanguage = localLanguage;
    }

    public static boolean getWasLoginSuccessful() {
        return wasLoginSuccessful;
    }

    public static void setWasLoginSuccessful(boolean wasLoginSuccessful) {
        Globals.wasLoginSuccessful = wasLoginSuccessful;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Globals.userName = userName;
    }

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int user_id) {
        Globals.userId = user_id;
    }

    public static Customers getMasterCustomers() {
        return masterCustomers;
    }

    public static void setMasterCustomers(Customers masterCustomers) {
        Globals.masterCustomers = masterCustomers;
    }

    public static Contacts getMasterContacts() {
        return masterContacts;
    }

    public static void setMasterContacts(Contacts masterContacts) {
        Globals.masterContacts = masterContacts;
    }
}
