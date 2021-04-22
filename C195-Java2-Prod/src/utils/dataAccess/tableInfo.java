package utils.dataAccess;

/**
 * Abstract class for tables, used to access the Appointments and Customer tables
 */
public abstract class tableInfo {
    public abstract String getTableName();
    public abstract String getPrimaryKeyName();
}

