package utils.dataAccess;

public class tableInfo_Appointments extends tableInfo {
    @Override
    public String getTableName() {
        return "appointments";
    }

    @Override
    public String getPrimaryKeyName() {
        return "Appointment_ID";
    }
}
