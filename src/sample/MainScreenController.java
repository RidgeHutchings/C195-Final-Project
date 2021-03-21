package sample;

import com.mysql.jdbc.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.DBQuery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainScreenController {
    @FXML
    TableView<Customer> customerTable;

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
    //appointment table FXML below
    @FXML
    TableView appointmentTable;
    @FXML
    TableColumn<Appointments,Integer> appointmentIDCol;
    @FXML
    TableColumn<Appointments,String>appointmentTitleCol;
    @FXML
    TableColumn<Appointments,String>appointmentDescriptionCol;
    @FXML
    TableColumn<Appointments,String>appointmentLocationCol;
    @FXML
    TableColumn<Appointments,String>appointmentTypeCol;
    @FXML
    TableColumn<Appointments,String>appointmentStartCol;
    @FXML
    TableColumn<Appointments,String>appointmentEndCol;
    @FXML
    TableColumn<Appointments,String>appointmentCreateDateCol;
    @FXML
    TableColumn<Appointments,String>appointmentCreatedByCol;
    @FXML
    TableColumn<Appointments,String>appointmentLastUpdateCol;
    @FXML
    TableColumn<Appointments,String>appointmentLastUpdatedByCol;
    @FXML
    TableColumn<Appointments,Integer>appointmentCustomerIDCol;
    @FXML
    TableColumn<Appointments,Integer>appointmentUserIDCol;
    @FXML
    TableColumn<Appointments,Integer>appointmentContactIDCol;


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

    //This method populates the appointment table based on the selected customer
    public void populateAppointmentTable(MouseEvent event) throws IOException, SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        //clear the table in case there are previous entries
        appointmentTable.getItems().clear();
        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //run an updated SQL query on the customer selected
        ResultSet results = statement.executeQuery("SELECT * FROM appointments WHERE Customer_ID='"+selectedCustomer.getID()+"'");

        //populate the appointment table
        while(results.next())
        {
            System.out.println("populating appointment table now!");
            Appointments appoint = new Appointments(results.getInt("Appointment_ID"),results.getString("Title"),results.getString("Description"),results.getString("Location"),
                    results.getString("Type"), results.getString("Start"),results.getString("End"),results.getString("Create_Date"),
                    results.getString("Created_By"),results.getString("Last_Update"), results.getString("Last_Updated_By"),results.getInt("Customer_ID"),
                    results.getInt("User_ID"),results.getInt("Contact_ID"));
            Appointments.getAppointments().add(appoint);

        }
        //if there are no appointments, the table is not populated
        if(Appointments.getAppointments()!=null) {
            appointmentTable.setItems(Appointments.getAppointments());
            appointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
            appointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
            appointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
            appointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
            appointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
            appointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
            appointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
            appointmentCreateDateCol.setCellValueFactory(new PropertyValueFactory<>("CreateDate"));
            appointmentCreatedByCol.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
            appointmentLastUpdateCol.setCellValueFactory(new PropertyValueFactory<>("LastUpdate"));
            appointmentLastUpdatedByCol.setCellValueFactory(new PropertyValueFactory<>("LastUpdatedBy"));
            appointmentCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
            appointmentUserIDCol.setCellValueFactory(new PropertyValueFactory<>("UserID"));
            appointmentContactIDCol.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        }
    }
}
