package utils;

import main.Globals;
import utils.dataAccess.DBConnection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Utils {
    private static Random random = new Random();
    private String nextIdNumberFilePath = "./NextIdNumber.txt";
    static private LocalDateTime forcedNowValue;

    /**
     * Convert Database TimeStamp value to LocalDateTime
     * @param ts timestamp value
     * @return converted local date time.
     */
    public LocalDateTime TimeStamp_to_LocalDateTime(Timestamp ts){
        if(ts != null){
            return ts.toLocalDateTime();
        } else {
            return null;
        }
    }

    public Timestamp LocalDateTime_to_TimeStamp(LocalDateTime ldt){
        if(ldt != null){
            return Timestamp.valueOf(ldt);
        } else {
            return null;
        }
    }

    /**
     * Compares username and password to database values
     * @param userName - User name to check
     * @param password - Submitted password
     * @return - true if values matched, false if values do not match
     */
    public boolean CheckPassword(String userName, String password) {
        boolean retVal = false;
        String sql = String.format("SELECT * FROM users WHERE User_Name = '%s'", userName);
        try{
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int size = rsSize(rs);
            if(size > 1){
                System.out.println("Constraint error, result set size = " + size + " for user name " + userName);
                System.exit(-1);
            }
            if(rsSize(rs) == 1){
                String expectedPassword = rs.getString("Password");
                if(password.equals(expectedPassword)){
                    Globals.setUserId(rs.getInt("User_ID"));
                    Globals.setUserName(userName);
                    retVal = true;
                } else {
                    retVal = false;
                }
            } else {
                // user name does not exist
//                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retVal;

        // normally this would create a hash of the password,
        // and then check the password table to see if the hash maps

//        Boolean successfulLoginAttempt = (user.equals("admin") && password.equals("admin"));
//        return successfulLoginAttempt;
    }

    /**
     * Returns the number of rows in a result set while handling errors.
     * @param resultSet - submitted result set
     * @return int - number of rows in result set
     */
    private int rsSize(ResultSet resultSet) {
        int resultSetSize = 0;
        if(resultSet != null){
            try {
                resultSet.last();
                resultSetSize = resultSet.getRow();
                resultSet.first();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return resultSetSize;
    }

    /**
     * Provides a random color for password failure.
     * During repeated fails, gives user feedback that screen is still working.
     * @return returns a color code as a 6 digit string.
     */
    public String getRandomColor() {
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }

    /**
     * Single use routine to write Login Attempts to the logs
     * @param userName - User attempting to log in
     * @param success - Success or failure of attempt
     */
    public void WriteLoginAttempt(String userName, boolean success) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String datetime = simpleDateFormat.format(date);
        System.out.println(datetime);
        String successString = success ? "SUCCESS" : "FAILURE";
        String lineToLog = datetime + " " + successString + " " + userName;
        try {
            FileWriter fw = new FileWriter("login_activity.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(lineToLog);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets next Id Number, but does not commit that number, will require logic change for multi user setups
     * @return int - next number from list.
     */
    public int getNextIdNumber() {
        BufferedReader reader;
        File nextIdNumberFile = new File(nextIdNumberFilePath);
        CheckForIdNumFile_OrCreate(nextIdNumberFile);
        int nextNumber = getNextIdNumberFromFile(nextIdNumberFile);
        return nextNumber;
    }

    private int getNextIdNumberFromFile(File nextIdNumberFile)  {
        FileReader fr = null;
        try {
            fr = new FileReader(nextIdNumberFile);
        } catch (FileNotFoundException e) {
            System.out.println("FileReader failed.");
            e.printStackTrace();
            System.exit(-1);
        }
        BufferedReader reader = new BufferedReader(fr);
        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("reader.readLine() call failed.");
            e.printStackTrace();
            System.exit(-1);
        }
        int nextNumber = Integer.parseInt(line);
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("reader.close() call failed.");
            e.printStackTrace();
            System.exit(-1);
        }
        return nextNumber;
    }

    private void CheckForIdNumFile_OrCreate(File nextIdNumberFile)  {
        if(!nextIdNumberFile.exists()){
            FileWriter fw = null;
            try {
                fw = new FileWriter(nextIdNumberFilePath, false);
            } catch (IOException e) {
                System.out.println("FileWriter failed.");
                e.printStackTrace();
                System.exit(-1);
            }
            PrintWriter pw = new PrintWriter(fw);
            int defaultIdNumber = 101;
            pw.println(defaultIdNumber);
            try {
                fw.close();
            } catch (IOException e) {
                System.out.println("FileWriter.close failed.");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    /**
     * Returns path to next-ID file
     * @return path to next-ID file
     */
    public String getNextIdNumberFilePath() {
        return nextIdNumberFilePath;
    }

    /**
     * Sets file path
     * @param nextIdNumberFilePath - File path
     */
    public void setNextIdNumberFilePath(String nextIdNumberFilePath) {
        this.nextIdNumberFilePath = nextIdNumberFilePath;
    }


    /**
     * Commits next Id Number
     * The "next ID number" in the file is not actually in the system.
     * When this routine is called, it means that the existing number has been written to the database
     * and that the existing number needs to be incremented by one.
     */
    public void commitNextIdNumber() {
        int currentIdNumber = getNextIdNumber();
        currentIdNumber++;
        try {
            FileWriter fw = new FileWriter(getNextIdNumberFilePath(), false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(currentIdNumber);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating Id Number");
            e.printStackTrace();
            System.exit(-1);
        }
    }


    /**
     * Forced Now value for testing
     * @param forcedValueForNow Sets time of "now"
     */
    public void setForcedNowValue(LocalDateTime forcedValueForNow) {
        this.forcedNowValue = forcedValueForNow;
    }

    /**
     * Test safe version of now.
     * If forcedNow has been set, return forcedNow, otherwise return time from system clock
     * @return LocalDateTime - "Now" as far as the system knows.
     */
    public LocalDateTime now(){
        LocalDateTime now;
        if(forcedNowValue == null){
            now = LocalDateTime.now();
        } else {
            now = LocalDateTime.of(forcedNowValue.toLocalDate(), forcedNowValue.toLocalTime());
        }
        return now;
    }

}