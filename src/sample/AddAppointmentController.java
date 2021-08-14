package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class AddAppointmentController {
    @FXML
    Button addAppointmentButton;
    @FXML
    Button cancelButton;
    @FXML
    TextField appointmentTitle;
    @FXML
    TextField appointmentDescription;
    @FXML
    TextField appointmentLocation;
    @FXML
    TextField appointmentType;
    @FXML
    TextField appointmentCustomerID;
    @FXML
    TextField appointmentUserID;
    @FXML
    DatePicker appointmentStart;
    @FXML
    DatePicker appointmentEnd;
    @FXML
    ComboBox appointmentContact;

    private void cancelButtonPressed() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.setTitle("Appointment Scheduler");
        stage.setResizable(true);
        stage.setScene(scene);
    }
    //This method adds the new appointment to the MySQL database
    private void addAppointmentButtonPressed(){

    }
}
