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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        primaryStage.setTitle("Appointment Scheduler");
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

        //launches GUI
        launch(args);

        //Closes connection with database
        DBConnection.closeConnection();
    }
}
