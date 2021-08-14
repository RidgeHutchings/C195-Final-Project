package sample;

import com.mysql.jdbc.Statement;
import com.sun.javafx.scene.control.IntegerField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import utils.DBQuery;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MainScreenController {
    @FXML
    TableView<Customer> customerTable;
    @FXML
    Button addAppointmentButton;

    @FXML
    TableColumn<Customer,Integer> customerIDCol;
    @FXML
    TableColumn<Customer,String> customerNameCol;
    @FXML
    TableColumn<Customer,String> customerPostalCodeCol;
    @FXML
    TableColumn<Customer,String> customerPhoneCol;
    @FXML
    TableColumn<Customer, Date>customerCreateDateCol;
    @FXML
    TableColumn<Customer,String> customerCreatedByCol;
    @FXML
    TableColumn<Customer,Integer> customerLastUpdateCol;
    @FXML
    TableColumn<Customer,String> customerLastUpdatedByCol;
    @FXML
    TableColumn<Customer,String> customerDivisionIDCol;
    @FXML
    TableColumn<Customer,String> customerAddressCol;
    @FXML
    TableColumn<Customer,String> customerCountryCol;
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
    Button addCustomerButton;

    String[] listOfStates ={ "Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","District of Columbia","Federated States of Micronesia",
            "Florida","Georgia","Guam","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan",
            "Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota",
            "Ohio","Oklahoma","Oregon","Palau","Pennsylvania","Puerto Rico","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia",
            "Washington","West Virginia","Wisconsin","Wyoming"};

    String[] listOfUkDivisions = {"England","Wales","Scotland","Northern Ireland"};
    String[] listOfCanadaDivisions = {"Northwest Territories" , "Alberta","British Columbia" ,"Manitoba" ,"New Brunswick" ,"Nova Scotia" ,
            "Prince Edward Island" ,
            "Ontario" ,
            "Québec" ,
            "Saskatchewan" ,
            "Nunavut" ,
            "Yukon" ,
            "Newfoundland and Labrador"};
    @FXML
    private void initialize() throws SQLException {
        appointmentTable.setPlaceholder(new Label("Currently there are no appointments for this user"));

        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //pulls in any existing customers and displays them into the first table
        ResultSet results = statement.executeQuery("SELECT * FROM customers");

        //clear the list of Customer's to avoid duplicates
        Customer.getCustomers().clear();
        //parse the data returned from the result set into a series of lists

        while(results.next())
        {
            System.out.println("The result is true!");
        Customer cust = new Customer(results.getInt("Customer_ID"),results.getString("Customer_Name"),
                results.getString("Address"),results.getString("Postal_Code"),results.getString("Phone"),
                results.getDate("Create_Date"),results.getString("Created_By"), results.getInt("Last_Update"),
                results.getString("Last_Updated_By"), results.getInt("Division_ID"));
            Customer.getCustomers().add(cust);
        }
        for (int i=0;i<Customer.getCustomers().size();i++){
            Customer cust = (Customer) Customer.getCustomers().get(i);
            cust.setDivision(cust.getDivisionID());
            cust.setCountry(cust.getCountryID());
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
        customerDivisionIDCol.setCellValueFactory(new PropertyValueFactory<>("Division"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("Country"));

        //allows the table to be editable
        customerTable.setEditable(true);
        customerIDCol.setEditable(false);
        customerAddressCol.setEditable(true);
        customerPostalCodeCol.setEditable(true);
        customerPhoneCol.setEditable(true);
        customerCreateDateCol.setEditable(true);
        customerCreatedByCol.setEditable(true);
        customerLastUpdateCol.setEditable(true);
        customerDivisionIDCol.setEditable(true);
        customerCountryCol.setEditable(true);
        customerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerAddressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerPostalCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerPhoneCol.setCellFactory(TextFieldTableCell.forTableColumn());

        customerCreateDateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        customerCreatedByCol.setCellFactory(TextFieldTableCell.forTableColumn());
        customerLastUpdateCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        customerLastUpdatedByCol.setCellFactory(TextFieldTableCell.forTableColumn());

        customerCountryCol.setCellFactory(ComboBoxTableCell.forTableColumn("U.S","UK","Canada"));
        //TODO update the line below to a combo box, and then have it only populate divisions that are within that country.
       // customerDivisionIDCol.setCellValueFactory(ComboBoxTableCell.forTableColumn());





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
        if(selectedCustomer.getCountry().equals("U.S")){ customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfStates));}
        if(selectedCustomer.getCountry().equals("UK")){ customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfUkDivisions));}
        if(selectedCustomer.getCountry().equals("Canada")){ customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfCanadaDivisions));}
    }
    //this method opens up a new window that allows a customer to be added
    public void addCustomer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene scene = new Scene(root,400,400);

        Stage stage = (Stage)addCustomerButton.getScene().getWindow();
        stage.setTitle("Add Customer");
        stage.setResizable(false);
        stage.setScene(scene);

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerFirstName(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setName(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerAddress(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setAddress(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerPostalCode(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setPostalCode(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerPhoneNumber(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setPhoneNumber(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerCreateDate(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setCreateDate((Date) editedCell.getNewValue());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerCreatedBy(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setCreatedBy(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerLastUpdatedBy(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setLastUpdatedBy(editedCell.getNewValue().toString());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerLastUpdate(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setLastUpdate((Integer) editedCell.getNewValue());

    }
    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerDivision(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setDivision(editedCell.getNewValue().toString());

    }


    //this method puts the customer table into edit mode so the user can edit customer details
    public void editCustomerCountry(TableColumn.CellEditEvent editedCell) throws IOException{
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
        selectedCustomer.setCountry(editedCell.getNewValue().toString());

        String currentCountry = selectedCustomer.getCountry();
        if(currentCountry=="U.S"){
            System.out.println("the customer is in the US!");
            selectedCustomer.setDivision("");
            customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfStates));
        }
        else if(currentCountry=="UK"){

            selectedCustomer.setDivision("");
            customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfUkDivisions));
        }
        else if(currentCountry=="Canada"){

            selectedCustomer.setDivision("");
            customerDivisionIDCol.setCellFactory(ComboBoxTableCell.forTableColumn(listOfCanadaDivisions));
        }
        else{
            System.out.println("Could not determine customer's country");
        }

    }




    //this method deletes the selected customer
    public void deleteCustomer(ActionEvent event) throws SQLException {

        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //first we need to get the list of all possible appointments.
       ObservableList allAppointments = Appointments.getAppointments();
        //Next we get the customer ID of the selected customer.
        int deletedCustomerID = customerTable.getSelectionModel().getSelectedItem().getID();
        //now we delete the appointments that belong to the customer, and then delete the customer.
        for(int i = 0; i<allAppointments.size();i++){
            Appointments potentialAppointment = (Appointments) allAppointments.get(i);
            if(potentialAppointment.getCustomerID() == deletedCustomerID){
                allAppointments.remove(i);
                statement.execute("DELETE  FROM appointments WHERE Customer_ID='"+deletedCustomerID+"'");
            }
        }
        ObservableList allCustomers = Customer.getCustomers();
        for(int i =0; i<Customer.getCustomers().size();i++){
            Customer potentialCustomer = (Customer) allCustomers.get(i);
            if(potentialCustomer.getID() == deletedCustomerID){
                allCustomers.remove(i);
                statement.execute("DELETE  FROM customers WHERE Customer_ID='"+deletedCustomerID+"'");
            }
        }

    }

    @FXML
    private void addAppointmentButtonPressed() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addAppointment.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)addAppointmentButton.getScene().getWindow();
        stage.setTitle("Add an Appointment");
        stage.setResizable(true);
        stage.setScene(scene);
    }


}
