package main;

import java.util.Locale;
import java.util.ResourceBundle;

public class Globals {
    private static boolean hasLoginBeenAttempted;
    private static String localLanguage;
    private static boolean wasLoginSuccessful;
    private static String userName = "userName not set";
    private static ResourceBundle rb;

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
}
