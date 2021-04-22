package utils.dataAccess;

public class tableInfo_Appointments extends tableInfo {
    /**
     * returns table name
     * @return table name
     */
    @Override public String getTableName() {
        return "appointments";
    }

    /**
     * Returns primary key column name
     * @return key column
     */
    @Override public String getPrimaryKeyName() {
        return "Appointment_ID";
    }
}
