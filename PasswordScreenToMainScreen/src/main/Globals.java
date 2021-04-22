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
    /** provides the resource bundle.
     * @return - Global resource bundle*/
    public static ResourceBundle getResourceBundle() {
        String rbPath = "utils.localization/Nat";
        rb = ResourceBundle.getBundle(rbPath, Locale.getDefault());
        return rb;
    }

    /**
     * Sets the global resource bundle.  Probably not used.
     * @param rb - inbound Resource Bundle - used to set the resource bundle one time
     */
    public static void setResourceBundle(ResourceBundle rb) {
        Globals.rb = rb;
    }

    /** Tracks whether or not the user has yet attempted to log in.
     *  @return int - value from getter */
    public static boolean getHasLoginBeenAttempted() {
        return hasLoginBeenAttempted;
    }
    /** Used to updote the tracker for whether or not the user has yet attempted to log in.
     * @param hasLoginBeenAttempted - inbound value for setter */
    public static void setHasLoginBeenAttempted(boolean hasLoginBeenAttempted) {
        Globals.hasLoginBeenAttempted = hasLoginBeenAttempted;
    }

    /** Returns the local language value
     *  @return int - value from getter */
    public static String getLocalLanguage() { return localLanguage; }
    /** Sets the local language value
     * @param localLanguage - inbound value for setter */
    public static void setDetectedSystemLocalLanguage(String localLanguage) {
        Globals.localLanguage = localLanguage;
    }

    /** gets login status, used to insure correct messages on login screen.
     *  @return int - value from getter */
    public static boolean getWasLoginSuccessful() {
        return wasLoginSuccessful;
    }
    /** records login status, used to insure correct messages on login screen.
     * @param wasLoginSuccessful - inbound value for setter */
    public static void setWasLoginSuccessful(boolean wasLoginSuccessful) {
        Globals.wasLoginSuccessful = wasLoginSuccessful;
    }

    /** returns system user name
     *  @return int - value from getter */
    public static String getUserName() {
        return userName;
    }
    /** sets system user name
     * @param userName - inbound value for setter */
    public static void setUserName(String userName) {
        Globals.userName = userName;
    }

    /** gets system user Id
     *  @return int - value from getter */
    public static int getUserId() {
        return userId;
    }
    /** sets system user Id
     * @param user_id - inbound value for setter */
    public static void setUserId(int user_id) {
        Globals.userId = user_id;
    }

    /** gets all customers for the system.
     *  @return int - value from getter */
    public static Customers getMasterCustomers() {
        return masterCustomers;
    }
    /** sets all customers for the system.
     * @param masterCustomers - inbound value for setter */
    public static void setMasterCustomers(Customers masterCustomers) {
        Globals.masterCustomers = masterCustomers;
    }

    /** gets all contacts for the system.
     *  @return int - value from getter */
    public static Contacts getMasterContacts() {
        return masterContacts;
    }
    /** sets all contacts for the system.
     * @param masterContacts - inbound value for setter */
    public static void setMasterContacts(Contacts masterContacts) {
        Globals.masterContacts = masterContacts;
    }

    /** returns the selected Radio Button.
     *  @return int - value from getter */
    public static String getSelectedRadioButton() {
        return selectedRadioButtonName;
    }
    /** records the selected Radio Button.
     * @param radioButtonName - inbound value for setter */
    public static void setSelectedRadioButtonName(String radioButtonName) {
        Globals.selectedRadioButtonName = radioButtonName;
    }

    /** Gets the current date and time. Used as a starting reference.
     *  @return int - value from getter */
    public static LocalDateTime getCurrentReferenceDate() {
        return currentReferenceDate;
    }
    /** Sets the current date and time. Used as a starting reference.
     * @param currentReferenceDate - inbound value for setter */
    public static void setCurrentReferenceDate(LocalDateTime currentReferenceDate) {
        Globals.currentReferenceDate = currentReferenceDate;
    }

    /** Returns true before the first login attempt.
     *  @return int - value from getter */
    public static boolean isStillFirstLogin() {
        return stillFirstLogin;
    }
    /** Used to set the First Login flag to false.
     * @param stillFirstLogin - - inbound value for setter */
    public static void setStillFirstLogin(boolean stillFirstLogin) {
        Globals.stillFirstLogin = stillFirstLogin;
    }
}
