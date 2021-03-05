package main;

public class Globals {
    private static boolean hasLoginBeenAttempted;
    private static String localLanguage;
    private static boolean wasLoginSuccessful;

    public static boolean getHasLoginBeenAttempted() {
        return hasLoginBeenAttempted;
    }

    public static void setHasLoginBeenAttempted(boolean hasLoginBeenAttempted) {
        Globals.hasLoginBeenAttempted = hasLoginBeenAttempted;
    }

    public static String getLocalLanguage() {
        return localLanguage;
    }

    public static void setLocalLanguage(String localLanguage) {
        Globals.localLanguage = localLanguage;
    }

    public static boolean getWasLoginSuccessful() {
        return wasLoginSuccessful;
    }

    public static void setWasLoginSuccessful(boolean wasLoginSuccessful) {
        Globals.wasLoginSuccessful = wasLoginSuccessful;
    }
}
