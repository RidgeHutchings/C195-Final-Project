package sample;

import com.mysql.jdbc.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBQuery;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {

    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private Date createDate;
    private String createdBy;
    private int lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private String division;
    private int countryID;
    private String country;

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    Customer(int id, String name, String address, String postalCode, String phoneNumber, Date createDate
            , String createdBy, int lastUpdate, String lastUpdatedBy, int divisionID) throws SQLException {

        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        //this.division = getDivision(divisionID);
    }

    public static ObservableList getCustomers() {
        return allCustomers;
    }
    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }


    public String getDivision() {
        return division;
    }

    public void setDivision(int divisionID) throws SQLException {
        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //run an updated SQL query on the customer selected
        ResultSet resultSet = statement.executeQuery("SELECT * FROM first_level_divisions WHERE Division_ID='"+divisionID+"'");
        resultSet.next();
        this.division = resultSet.getString("Division");
        //return resultSet.getString("Division");

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(int countryID) throws SQLException {
        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //run an updated SQL query on the customer selected
        ResultSet resultSet = statement.executeQuery("SELECT * FROM countries WHERE Country_ID='"+countryID+"'");
        resultSet.next();
        this.country = resultSet.getString("Country");
        //return resultSet.getString("Division");


    }

    public int getCountryID() throws SQLException {
        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //run an updated SQL query on the customer selected
        ResultSet resultSet = statement.executeQuery("SELECT COUNTRY_ID FROM first_level_divisions WHERE Division_ID='"+divisionID+"'");
        resultSet.next();
        return resultSet.getInt("Country_ID");
    }

    public void setDivision(String toString) {
        this.division = toString;
    }

    public void setCountry(String toString) {
        this.country = toString;
    }
}