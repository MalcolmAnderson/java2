package utils_Navigation;

public class navInfo_MainScreen extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "sample.fxml";
    }
    @Override String getScreenTitle() {
        return "Hello World";
    }
}
