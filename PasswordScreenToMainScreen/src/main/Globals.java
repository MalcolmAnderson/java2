package main;

import models.Contacts;
import models.Customers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Provides a globals store of state values
 */
public class Globals {
    private static boolean stillFirstLogin;
    private static boolean hasLoginBeenAttempted;
    private static String localLanguage;
    private static boolean wasLoginSuccessful;
    private static int userId = -1;
    private static String userName = "userName not set";
    private static ResourceBundle rb;
    private static Customers masterCustomers;
    private static Contacts masterContacts;
    private static String selectedRadioButtonName;
    private static LocalDateTime currentReferenceDate;

    /** provides 8am Eastern in local time */
    public static LocalTime earlyLimitEasternTime = LocalTime.of(8, 0,0);
    /** provides 10pm Eastern in local time */
    public static LocalTime lateLimitEasternTime = LocalTime.of(22, 0,0);


    // TODO set the resource bundle one time, and put the languge updating logic in another method.
    /** provides the resource bundle. */
    public static ResourceBundle getResourceBundle() {
        String rbPath = "utils.localization/Nat";
        rb = ResourceBundle.getBundle(rbPath, Locale.getDefault());
        return rb;
    }
    /** Sets the global resource bundle.  Probably not used */
    public static void setResourceBundle(ResourceBundle rb) {
        Globals.rb = rb;
    }

    /** Tracks whether or not the user has yet attempted to log in. */
    public static boolean getHasLoginBeenAttempted() {
        return hasLoginBeenAttempted;
    }
    /** Used to updote the tracker for whether or not the user has yet attempted to log in. */
    public static void setHasLoginBeenAttempted(boolean hasLoginBeenAttempted) {
        Globals.hasLoginBeenAttempted = hasLoginBeenAttempted;
    }

    /** Returns the local language value */
    public static String getLocalLanguage() { return localLanguage; }
    /** Sets the local language value */
    public static void setDetectedSystemLocalLanguage(String localLanguage) {
        Globals.localLanguage = localLanguage;
    }

    /** gets login status, used to insure correct messages on login screen. */
    public static boolean getWasLoginSuccessful() {
        return wasLoginSuccessful;
    }
    /** records login status, used to insure correct messages on login screen. */
    public static void setWasLoginSuccessful(boolean wasLoginSuccessful) {
        Globals.wasLoginSuccessful = wasLoginSuccessful;
    }

    /** returns system user name */
    public static String getUserName() {
        return userName;
    }
    /** sets system user name */
    public static void setUserName(String userName) {
        Globals.userName = userName;
    }

    /** gets system user Id */
    public static int getUserId() {
        return userId;
    }
    /** sets system user Id */
    public static void setUserId(int user_id) {
        Globals.userId = user_id;
    }

    /** gets all customers for the system. */
    public static Customers getMasterCustomers() {
        return masterCustomers;
    }
    /** sets all customers for the system. */
    public static void setMasterCustomers(Customers masterCustomers) {
        Globals.masterCustomers = masterCustomers;
    }

    /** gets all contacts for the system. */
    public static Contacts getMasterContacts() {
        return masterContacts;
    }
    /** sets all contacts for the system. */
    public static void setMasterContacts(Contacts masterContacts) {
        Globals.masterContacts = masterContacts;
    }

    /** returns the selected Radio Button. */
    public static String getSelectedRadioButton() {
        return selectedRadioButtonName;
    }
    /** records the selected Radio Button. */
    public static void setSelectedRadioButtonName(String radioButtonName) {
        Globals.selectedRadioButtonName = radioButtonName;
    }

    /** Gets the current date and time. Used as a starting reference. */
    public static LocalDateTime getCurrentReferenceDate() {
        return currentReferenceDate;
    }
    /** Sets the current date and time. Used as a starting reference. */
    public static void setCurrentReferenceDate(LocalDateTime currentReferenceDate) {
        Globals.currentReferenceDate = currentReferenceDate;
    }

    /** Returns true before the first login attempt. */
    public static boolean isStillFirstLogin() {
        return stillFirstLogin;
    }
    /** Used to set the First Login flag to false. */
    public static void setStillFirstLogin(boolean stillFirstLogin) {
        Globals.stillFirstLogin = stillFirstLogin;
    }
}
