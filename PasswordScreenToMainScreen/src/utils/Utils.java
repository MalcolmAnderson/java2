package utils;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Globals;
import utils.dataAccess.DBConnection;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class Utils {
    static Random random = new Random();
    private String nextIdNumberFilePath = "./NextIdNumber.txt";

    public int add(int a, int b) {
        return a + b;
    }

    public LocalDateTime TimeStamp_to_LocalDateTime(Timestamp ts){
        if(ts != null){
            return ts.toLocalDateTime();
        } else {
            return null;
        }
    }

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

    public String getRandomColor() {
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        return colorCode;
    }

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

    public String getNextIdNumberFilePath() {
        return nextIdNumberFilePath;
    }
    public void setNextIdNumberFilePath(String nextIdNumberFilePath) {
        this.nextIdNumberFilePath = nextIdNumberFilePath;
    }

    public void deleteFileIfItExists(String filePathToBeDeleted) {
        File myFile = new File(filePathToBeDeleted);
        if(myFile.exists()){
            myFile.delete();
        } else {
            System.out.println("File " + filePathToBeDeleted + " has been deleted.");
        }
    }

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

//    public String LocalDateTime_ToTimeStamp(LocalDateTime dt) {
//        String retVal = dt.toLocalDate().toString();
//        retVal += " ";
//        retVal += dt.toLocalTime().toString();
//        retVal += ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now()).toString();
//        return retVal;
//    }

    public LocalDateTime Local_ToEastern(LocalDateTime ldt) {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId estZoneId = ZoneId.of("America/New_York");

        ZonedDateTime zdtLocal = ZonedDateTime.of(ldt, localZoneId);
        ZonedDateTime zdtEastern = ZonedDateTime.ofInstant(zdtLocal.toInstant(), estZoneId);
        return zdtEastern.toLocalDateTime();
    }

    public LocalDateTime Eastern_ToLocal(LocalDateTime eastern){
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime zdtUTC = ZonedDateTime.of(eastern, estZoneId);
        ZonedDateTime zdtLocal = ZonedDateTime.ofInstant(zdtUTC.toInstant(), localZoneId);
        return zdtLocal.toLocalDateTime();
    }

    public LocalDateTime Local_ToUTC(LocalDateTime ldt) {
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime zdtLocal = ZonedDateTime.of(ldt, localZoneId);
        ZonedDateTime zdtUTC = ZonedDateTime.ofInstant(zdtLocal.toInstant(), utcZoneId);
        return zdtUTC.toLocalDateTime();
    }

    public LocalDateTime UTC_ToLocal(LocalDateTime utc){
        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
        ZoneId utcZoneId = ZoneId.of("UTC");

        ZonedDateTime zdtUTC = ZonedDateTime.of(utc, utcZoneId);
        ZonedDateTime zdtLocal = ZonedDateTime.ofInstant(zdtUTC.toInstant(), localZoneId);
        return zdtLocal.toLocalDateTime();
    }

//
//        ZonedDateTime localStartTime = ZonedDateTime.ofInstant(utcZDT.toInstant(),localZoneId);
//
//        LocalDate estDate = LocalDate.of(2020, 8, 3);
//        LocalTime estTime = LocalTime.of(11, 0); // Get from Combo Box
//        ZoneId estZoneId = ZoneId.of("America/New_York");
//        ZonedDateTime estZDT = ZonedDateTime.of(estDate, estTime, estZoneId);
//        assertEquals("2020-08-03T11:00", estZDT.toLocalDateTime().toString());// Creates ZonedDateTime object
//
//        // Convert EST to UTC
//        ZoneId utcZID = ZoneId.of("UTC");
//        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(estZDT.toInstant(), utcZID);
//        System.out.println("EST - UTC Time: " + utcZDT);
//        assertEquals("2020-08-03T15:00", utcZDT.toLocalDateTime().toString());// Creates ZonedDateTime object
//
//        // Convert UTC to Local
//        ZoneId localZoneId = ZoneId.of(ZoneId.systemDefault().toString());
//        ZonedDateTime localStartTime = ZonedDateTime.ofInstant(utcZDT.toInstant(),localZoneId);
//        System.out.println("UTC - Local Time: " + localStartTime);
//        assertEquals("2020-08-03T08:00", localStartTime.toLocalDateTime().toString());// Creates ZonedDateTime object

//    }
}