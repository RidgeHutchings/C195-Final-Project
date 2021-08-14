package sample;

import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.EventListener;


public class AddCustomerController {
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField customerName;
    @FXML
    TextField customerAddress;
    @FXML
    TextField postalCode;
    @FXML
    TextField phoneNumber;
    @FXML
    ComboBox country;
    @FXML
    ComboBox division;

    ObservableList<String> listOfCountries = FXCollections.observableArrayList("U.S","UK","Canada");
    ObservableList<String> listOfStates =FXCollections.observableArrayList("Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","District of Columbia","Federated States of Micronesia",
            "Florida","Georgia","Guam","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan",
            "Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota",
            "Ohio","Oklahoma","Oregon","Pennsylvania","Puerto Rico","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia",
            "Washington","West Virginia","Wisconsin","Wyoming");
    ObservableList<String> listOfUKDivisions =FXCollections.observableArrayList("England","Wales","Scotland","Northern Ireland");
    ObservableList<String> listOfCanadaDivisions=FXCollections.observableArrayList("Northwest Territories" , "Alberta","British Columbia" ,"Manitoba" ,"New Brunswick" ,"Nova Scotia" ,
            "Prince Edward Island" ,
            "Ontario" ,
            "Québec" ,
            "Saskatchewan" ,
            "Nunavut" ,
            "Yukon" ,
            "Newfoundland and Labrador");

    @FXML
    public void initialize(){

    country.setItems(listOfCountries);


    }
    public String getCountrySelected(){
    return (String) country.getSelectionModel().getSelectedItem();



        }
    public int getDivisionSelected() throws SQLException {
        String div = (String) division.getSelectionModel().getSelectedItem();
        PreparedStatement countryIDStatement = DBConnection.startConnection().prepareStatement("SELECT Division_ID FROM first_level_divisions WHERE Division=?");
        countryIDStatement.setString(1,div);
        ResultSet rs = countryIDStatement.executeQuery();
        rs.next();
        return rs.getInt("Division_ID");



    }

    @FXML
    private void populateDivisions(){
        System.out.println(getCountrySelected());
        if(getCountrySelected().equals("U.S")){division.setItems(listOfStates);}
        if(getCountrySelected().equals("UK")){division.setItems(listOfUKDivisions);}
        if(getCountrySelected().equals("Canada")){division.setItems(listOfCanadaDivisions);}
    }
    @FXML
    private void addCustomerButtonPressed() throws SQLException, IOException {
        //Get the Statement reference
        Statement statement = DBQuery.getStatement();
        PreparedStatement countStatement = DBConnection.startConnection().prepareStatement("SELECT COUNT(*) FROM customers");
        ResultSet sizeResult = countStatement.executeQuery();
        sizeResult.next();


        PreparedStatement stm = DBConnection.startConnection().prepareStatement("INSERT INTO customers Values(?,?,?,?,?,NOW(),?,NOW(),?,?)");
        stm.setInt(1,sizeResult.getInt(1)+1);
        stm.setString(2,customerName.getText());
        stm.setString(3,customerAddress.getText());
        stm.setString(4,postalCode.getText());
        stm.setString(5,phoneNumber.getText());

        stm.setString(6,"test");

        stm.setString(7,"test");
        stm.setInt(8,getDivisionSelected());
        stm.executeUpdate();
        stm.close();

        //change the scene back to the main screen after the customer is added
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)addButton.getScene().getWindow();
        stage.setTitle("Appointment Scheduler");
        stage.setResizable(true);
        stage.setScene(scene);
    }
    @FXML
    private void cancelButtonPressed() throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
    Scene scene = new Scene(root);

    Stage stage = (Stage)addButton.getScene().getWindow();
    stage.setTitle("Appointment Scheduler");
    stage.setResizable(true);
    stage.setScene(scene);
}

    }



