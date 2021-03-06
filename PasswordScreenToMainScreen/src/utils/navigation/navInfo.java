package utils.navigation;

import java.util.Locale;
import java.util.ResourceBundle;

abstract class navInfo {
    abstract String getFxmlFileName();
    abstract String getScreenTitle();
    ResourceBundle rb = ResourceBundle.getBundle("utils/localization/Nat", Locale.getDefault());

    String pathToViews = "../../views/";
}


