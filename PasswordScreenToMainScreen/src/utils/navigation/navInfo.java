package utils.navigation;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Nav info class is used to simplify screen navigation.
 */
abstract class navInfo {
    /**
     * used to return the Fxml File name for screen
     * @return FxmlFileName
     */
    abstract String getFxmlFileName();

    /**
     * Used to get the screen title
     * @return Screen title
     */
    abstract String getScreenTitle();
    ResourceBundle rb = ResourceBundle.getBundle("utils/localization/Nat", Locale.getDefault());

    String pathToViews = "../../views/";
}


