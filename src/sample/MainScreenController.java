package sample;

import com.mysql.jdbc.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DBQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainScreenController {
    @FXML
    TableView<Customer> customerTable;
    @FXML
    TableView appointmentsTable;
    @FXML
    TableColumn<Customer,Integer> customerIDCol;
    @FXML
    TableColumn<Customer,String> customerNameCol;
    @FXML
    TableColumn<Customer,String> customerPostalCodeCol;
    @FXML
    TableColumn<Customer,String> customerPhoneCol;
    @FXML
    TableColumn<Customer,Integer> customerCreateDateCol;
    @FXML
    TableColumn<Customer,String> customerCreatedByCol;
    @FXML
    TableColumn<Customer,Integer> customerLastUpdateCol;
    @FXML
    TableColumn<Customer,String> customerLastUpdatedByCol;
    @FXML
    TableColumn<Customer,Integer> customerDivisionIDCol;
    @FXML
    TableColumn<Customer,String> customerAddressCol;

    @FXML
    private void initialize() throws SQLException {

        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //pulls in any existing customers and displays them into the first table
        ResultSet results = statement.executeQuery("SELECT * FROM customers");

        //parse the data returned from the result set into a series of lists

        while(results.next())
        {
            System.out.println("The result is true!");
        Customer cust = new Customer(results.getInt("Customer_ID"),results.getString("Customer_Name"),
                results.getString("Address"),results.getString("Postal_Code"),results.getString("Phone"),
                results.getString("Create_Date"),results.getString("Created_By"), results.getInt("Last_Update"),
                results.getString("Last_Updated_By"), results.getInt("Division_ID"));
            Customer.getCustomers().add(cust);
        }
        customerTable.setItems(Customer.getCustomers());
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        customerCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("CreateDate"));
        customerCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
        customerLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("LastUpdate"));
        customerLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("LastUpdatedBy"));
        customerDivisionIDCol.setCellValueFactory(new PropertyValueFactory<>("DivisionID"));




    }
}
