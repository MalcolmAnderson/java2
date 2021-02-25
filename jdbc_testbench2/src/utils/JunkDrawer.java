package utils;

import java.sql.SQLException;
import java.sql.Statement;

public class JunkDrawer {
    DBQueryManager dbQM = new DBQueryManager();

    String insertUSSR = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
            + " VALUES ('USSR', '2021-02-23 00:00:00', 'admin', 'admin' )";
    String strDeleteAllUSSR = "DELETE FROM countries WHERE  Country = 'USSR'";

    private String countryName ;
    private String createDate ;

    private String sqlString_InsertCountry;
    private String sqlString_DeleteCountry;

    public void addUSSRToDB(){
        dbQM.RunSQLString(insertUSSR);
    }

    public void deleteAllUSSR(){
        dbQM.RunSQLString(strDeleteAllUSSR);
    }

    public void AddNewCountry(String text) {
    }

    public void RemoveAllCountry(String text) {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getSqlString_InsertCountry() {
        return sqlString_InsertCountry;
    }

    public void setSqlString_InsertCountry(String sqlString_InsertCountry) {
        String insertUSSR = String.format("INSERT INTO countries (%1, Create_Date, Created_By, Last_Updated_By) "
                + " VALUES ('USSR', %2, 'admin', 'admin' )", sqlString_InsertCountry, createDate);

        this.sqlString_InsertCountry = sqlString_InsertCountry;
    }

    public String getSqlString_DeleteCountry() {
        return sqlString_DeleteCountry;
    }

    public void setSqlString_DeleteCountry(String sqlString_DeleteCountry) {
        this.sqlString_DeleteCountry = sqlString_DeleteCountry;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
