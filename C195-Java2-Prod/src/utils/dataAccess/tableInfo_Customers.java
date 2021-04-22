package utils.dataAccess;

public class tableInfo_Customers extends tableInfo {

    /**
     * returns table name
     * @return table name
     */
    @Override public String getTableName() {
        return "customers";
    }


    /**
     * Returns primary key column name
     * @return key column
     */
    @Override public String getPrimaryKeyName() {
        return "Customer_ID";
    }
}
