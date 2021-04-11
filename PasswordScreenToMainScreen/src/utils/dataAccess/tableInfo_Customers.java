package utils.dataAccess;

public class tableInfo_Customers extends tableInfo {
    @Override
    public String getTableName() {
        return "customers";
    }

    @Override
    public String getPrimaryKeyName() {
        return "Customer_ID";
    }
}
