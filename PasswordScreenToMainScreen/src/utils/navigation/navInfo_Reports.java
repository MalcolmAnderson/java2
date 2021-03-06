package utils.navigation;

public class navInfo_Reports extends navInfo {
    @Override String getFxmlFileName() {
        return pathToViews + "Reports.fxml";
    }
    @Override String getScreenTitle() {
        return "Reports";
    }
}
