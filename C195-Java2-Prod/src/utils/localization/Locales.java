package utils.localization;


import java.util.Locale;

/**
 * Handles Locale objects for internationalization
 */
public class Locales {
    /**
     * English Locale object
     * @return Locale object for US
     */
    public static Locale English(){
        return Locale.US;
    }

    /**
     * French Locale object
     * @return Locale object for France
     */
    public static Locale French(){
        return new Locale("fr", "FR");
    }
}
