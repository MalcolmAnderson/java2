package utils;

public class JunkCountryManager {
    DBQueryManager dbQM = new DBQueryManager();

    private String countryName = "Republic of Congo";
    private String createDate = "2021-02-24 00:00:00";
    private String createdBy = "admin";
    private String lastUpdatedBy = "admin";
    private String where_CountryName = "Tongo";

//    String insertUSSR = "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
//            + " VALUES ('USSR', '2021-02-23 00:00:00', 'admin', 'admin' )";
//    String strDeleteAllUSSR = "DELETE FROM countries WHERE  Country = 'USSR'";

    private String insertStatement = String.format(
            "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
            + " VALUES ('%s', '%s', '%s', '%s' )", countryName, createDate, createdBy, lastUpdatedBy);
    private String deleteStatement = String.format(
            "DELETE FROM countries WHERE  Country = '%s'", countryName);
    private String updateStatement = String.format(
            "UPDATE countries SET Country = '%s' WHERE Country = '%s'", countryName, where_CountryName);

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

    public void RunUpdate(){
        dbQM.RunSQLString(updateStatement);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
        UpdateInsertStatement();
        UpdateDeleteStatement();
        UpdateUpdateStatement();
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

    public String getWhere_CountryName() {
        return where_CountryName;
    }

    public void setWhere_CountryName(String where_CountryName) {
        this.where_CountryName = where_CountryName;
    }

    private void UpdateInsertStatement() {
        insertStatement = String.format(
                "INSERT INTO countries (Country, Create_Date, Created_By, Last_Updated_By) "
                + " VALUES ('%s', '%s', '%s', '%s' )",
                getCountryName(), getCreateDate(), getCreatedBy(), getLastUpdatedBy());
    }
    private void UpdateUpdateStatement(){
        updateStatement = String.format(
                "UPDATE countries SET Country = '%s' WHERE Country = '%s'",
                countryName, where_CountryName);
    }

    private void UpdateDeleteStatement(){
        deleteStatement = String.format(
                "DELETE FROM countries WHERE  Country = '%s'", getCountryName());
    }
}
