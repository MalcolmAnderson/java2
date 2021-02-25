package utils;

public class JunkCountryManager {
    DBQueryManager dbQM = new DBQueryManager();

    private String countryName = "Republic of Congo";
    private String createDate = "2021-02-24 00:00:00";
    private String createdBy = "admin";
    private String lastUpdatedBy = "admin";

//    String insertUSSR = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
//            + " VALUES ('USSR', '2021-02-23 00:00:00', 'admin', 'admin' )";
//    String strDeleteAllUSSR = "DELETE FROM countries WHERE  Country = 'USSR'";

    private String insertStatement = String.format("INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
            + " VALUES ('%s', '%s', '%s', '%s' )", getCountryName(), getCreateDate(), getCreatedBy(), getLastUpdatedBy());
    private String deleteStatement = String.format("DELETE FROM countries WHERE  Country = '%s'", getCountryName());

    public String getInsertStatement() {
        return insertStatement;
    }

    public void setInsertStatement(String insertStatement) {
        this.insertStatement = insertStatement;
    }

    public String getDeleteStatement() {
        return deleteStatement;
    }

    public void setDeleteStatement(String deleteStatement) {
        this.deleteStatement = deleteStatement;
    }

    public void RunInsert(){
        dbQM.RunSQLString(insertStatement);
    }

    public void RunDelete(){
        dbQM.RunSQLString(deleteStatement);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
        UpdateInsertStatement();
        UpdateDeleteStatement();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
        UpdateInsertStatement();
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        UpdateInsertStatement();
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        UpdateInsertStatement();
    }

    private void UpdateInsertStatement() {
        insertStatement = String.format("INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
                + " VALUES ('%s', '%s', '%s', '%s' )", getCountryName(), getCreateDate(), getCreatedBy(), getLastUpdatedBy());
    }

    private void UpdateDeleteStatement(){
        deleteStatement = String.format("DELETE FROM countries WHERE  Country = '%s'", getCountryName());
    }
}
