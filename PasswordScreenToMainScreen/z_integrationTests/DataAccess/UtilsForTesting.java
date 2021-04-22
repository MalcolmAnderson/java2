package DataAccess;

import java.io.File;

public class UtilsForTesting {

    public void deleteFileIfItExists(String filePathToBeDeleted) {
        File myFile = new File(filePathToBeDeleted);
        if(myFile.exists()){
            myFile.delete();
        } else {
            System.out.println("File " + filePathToBeDeleted + " has been deleted.");
        }
    }


}
