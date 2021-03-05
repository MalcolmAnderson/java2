package utils_Navigation;

import java.util.Locale;
import java.util.ResourceBundle;

abstract class navInfo {
    abstract String getFxmlFileName();
    abstract String getScreenTitle();
    ResourceBundle rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());

    String pathToViews = "../views/";
}


