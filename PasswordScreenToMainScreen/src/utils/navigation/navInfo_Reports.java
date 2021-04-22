package utils.navigation;

public class navInfo_Reports extends navInfo {
    /**
     * get file name
     * @return file name
     */
    @Override String getFxmlFileName() {
        return pathToViews + "Reports.fxml";
    }

    /**
     * gets Screen title as a resource bundle key value
     * @return
     */
    @Override String getScreenTitle() {
        return rb.getString("Reports");
    }
}
