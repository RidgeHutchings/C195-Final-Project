package sample;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DBConnection;
import utils.DBQuery;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

        //Gets the user's default language
        ResourceBundle rb  = ResourceBundle.getBundle("sample/language", Locale.getDefault());

        primaryStage.setTitle(rb.getString("title"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Style.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        //opens a connection to the mysql database
        Connection conn = DBConnection.startConnection();

        //takes the connection Object and creates a Statement Object which we can run SQL queries on
        DBQuery.setStatement(conn);

        //Get the Statement reference
        Statement statement = DBQuery.getStatement();

        //Get the user's ZoneID(time zone)
        ZoneId.systemDefault();
        //launches GUI
        launch(args);

        //Closes connection with database
        DBConnection.closeConnection();
    }
}
