/*
 * Class: CMSC214
 * Instructor: Alla Webb
 * Description: GUI to create Database that stores library books and patrons
 *
 * Due: 12/14/18
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Bryan Speelman
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class DBSetup extends Application {
    private Connection connection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Main pane
        Pane pane = new Pane();
        //GridPane
        GridPane gPane = new GridPane();
        gPane.setHgap(10);
        gPane.setVgap(10);
        gPane.setPadding(new Insets(5,5,5,5));

            //Text showing status
            Text status = new Text("Not Connected");
            gPane.add(status, 0, 0);

            //Label and Combobox for JDBC Driver Selection
            ComboBox drvSelect = new ComboBox();
            drvSelect.setMinWidth(150);
            drvSelect.getItems().addAll(
                    "com.mysql.jdbc.Driver",
                    "mysql-connector-java-ver-8.0.13"
            );
            gPane.add(new Label("JDBC Driver:"), 0, 1);
            gPane.add(drvSelect, 1,1);

            //Label and Combobox with Editing enables to choose Database URL
            ComboBox dbUrl = new ComboBox();
            dbUrl.setMinWidth(150);
            dbUrl.setEditable(true); //Editable
            dbUrl.setPromptText("jdbc:mysql://localhost:3306/bookslibrary");
            dbUrl.getItems().addAll("jdbc:mysql://localhost:3306/bookslibrary");

            gPane.add(new Label("Database URL"), 0, 2);
            gPane.add(dbUrl, 1,2);

            //Label and Textbox for Username
            TextField usrNme = new TextField();
            gPane.add(new Label("UserName:"), 0, 3);
            gPane.add(usrNme, 1,3);

            //Label and TextBox for password
            TextField pass = new TextField();
            gPane.add(new Label("Password"), 0, 4);
            gPane.add(pass, 1,4);

            //HBox for buttons
            HBox hBox = new HBox();

            //Button to connect to database
            Button btConnect = new Button("Connect");

            //Button to create tables and sample database
            Button btTable = new Button("Create Tables");

            //Button to close and open Library control
            Button btClose = new Button("Close");

            //Add buttons to hbox then add hbox to gpane
            hBox.getChildren().addAll(btConnect, btTable, btClose);
            hBox.setSpacing(10);
            gPane.add(hBox, 0, 5);

        //Add Vbod to pane
        pane.getChildren().add(gPane);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 600, 250);
        primaryStage.setTitle("Database SetUp"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        //primaryStage.setMinHeight();
        //primaryStage.setMinWidth(); // Set min width of window
        primaryStage.show(); // Display the stage

        AtomicReference<Connection> connection = null; //Connection object

        //On-Action for Connect
        btConnect.setOnAction(e -> {
            //Connect to database using information from fields
            try {
               getConnection((String) drvSelect.getValue(),
                       (String) dbUrl.getValue(), usrNme.getText().trim(), pass.getText().trim(), status);
            } catch (Exception ex){}
        });

        //On-Action for create Table
       /* btTable.setOnAction(e -> {
            //Connect to database using information from fields
            try {
                status.setText(getTables((Connection) connection));
            } catch (Exception ex){}
        });
*/
        //On-Action for Closie
        btClose.setOnAction(e->{
            try{
                primaryStage.close();
            }
            catch (Exception ex){}
        });
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     *Method for getting connection given drivername url, usrname, pass, and status object
     * @param driver
     * @param url
     * @param user
     * @param pass
     * @param text
     * @return
     */
    public void getConnection(String driver, String url,
                              String user, String pass, Text text){
        //Try registering jdbc.Driver
        try{
            Class.forName(driver);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            text.setText("Error finding JDBC driver");
        }
        //Create connection
        try{
            connection = DriverManager.getConnection(url, user, pass);
        }
        catch(SQLException e){
            e.printStackTrace();
            text.setText("Connection error please check url, Username and password");
        }
        text.setText("You Are Connected!");
    }

    //Method to run SQL script to create table w/ Test Data
    public String getTables(){
        //Try loading and running script
        try{

        }
        catch (Exception ex){
            return "Failed to load .SQL Script!";
        }
        return "Tables Created!!";
    }
}
