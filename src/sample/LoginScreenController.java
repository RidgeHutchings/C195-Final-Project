package sample;

import com.mysql.jdbc.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBQuery;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginScreenController {
    @FXML
    TextField usernameTextBox;
    @FXML
    PasswordField passwordTextBox;
    @FXML
    Label loginInfoLabel;


    //this method is called when the user hits the submit button on the login screen. It parses the data from the username
    //and password fields and checks them against the database to see if the user exists.
    public void attemptLogin(ActionEvent event) throws IOException, SQLException {
        //First we should check to make sure the boxes are not empty.
        if(usernameTextBox.getText().length()==0 || passwordTextBox.getText().length()==0) {
            loginInfoLabel.setText("Username or Password cannot be left blank. \nPlease check your entry and try again.");
        }else{
            String username = usernameTextBox.getText();
            String password = passwordTextBox.getText();

            //Get the Statement reference
            Statement statement = DBQuery.getStatement();

            //If this result set has anything inside of it, the user exists and should be let into the program.
            ResultSet results = statement.executeQuery("SELECT User_Name, Password FROM users WHERE User_Name='"+username + "' AND Password='"+password +"'");
           if( results.next()){
               Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
               Scene scene = new Scene(root,400,400);
               Stage stage = (Stage)usernameTextBox.getScene().getWindow();
               stage.setScene(scene);
           }
           else{
               loginInfoLabel.setText("Invalid username or password");
           }



        }


    }
}