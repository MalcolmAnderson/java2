package utils;





import javafx.stage.Stage;

// StageManager is the class that controlls the stage,
// it is responsible for building up, and tearing down scenes
public class AppointmentStageManager {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AppointmentStageManager.stage = stage;
    }
}
