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

import com.sun.rowset.JdbcRowSetImpl;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.sql.RowSet;
import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class DBSetup {
    //SQL Variables
    private static Statement statement;
    private static Connection connection;

    //Labels
    private static Label status = new Label("No Connection");

    //GridPanes
    private static GridPane gPane = new GridPane();

    //Panes
    private static Pane setupPane = new Pane();

    //ComboBoxes
    private static ComboBox cboDriver = new ComboBox();
    private static ComboBox cboURL = new ComboBox();

    //TextFields
    private static TextField tfUsername = new TextField();
    private static PasswordField pfPassword = new PasswordField();

    //Hboxes
    private static HBox hBox = new HBox();

    //Buttons
    //Button to connect to database
    private static Button btConnect = new Button("Connect");

    //Button to create tables and sample database
    private static Button btTable = new Button("Create Tables");

    //Button to close and open Library control
    private static Button btClose = new Button("Close");

    //Strings
    private static String sqlCommand;
    private static String url;
    private static String username;
    private static String password;

    //Scenes
    private static Scene setupScene = new Scene(setupPane, 600, 250);
    private static String driver = null;

    //Rowset
    private static RowSet rowSet = new JdbcRowSetImpl();

    public static RowSet display(){
        //Stage to display
        Stage dbPopUp = new Stage();

        //GridPane
        gPane.setHgap(10);
        gPane.setVgap(10);
        gPane.setPadding(new Insets(5,5,5,5));

        //Text showing status
        gPane.add(status, 0, 0);

        //Label and Combobox for JDBC Driver Selection
        cboDriver.setMinWidth(150);
        cboDriver.getItems().addAll(
                "com.mysql.jdbc.Driver",
                "sun.jdbc.odbc.dbc0dbcDriver",
                "oracle.jdbc.driver.OracleDriver"
        );
        cboDriver.getSelectionModel().selectFirst();

        gPane.add(new Label("JDBC Driver:"), 0, 1);
        gPane.add(cboDriver, 1,1);

        //Label and Combobox with Editing enables to choose Database URL
        cboURL.setMinWidth(150);
        cboURL.setEditable(true); //Editable
        cboURL.setPromptText("jdbc:mysql://localhost:3306/bookslibrary");
        cboURL.getItems().addAll("jdbc:mysql://localhost:3306/bookslibrary");

        gPane.add(new Label("Database URL"), 0, 2);
        gPane.add(cboURL, 1,2);

        //Label and Textbox for Username
        gPane.add(new Label("UserName:"), 0, 3);
        gPane.add(tfUsername, 1,3);

        //Label and TextBox for password
        gPane.add(new Label("Password"), 0, 4);
        gPane.add(pfPassword, 1,4);

        //Add buttons to hbox then add hbox to gpane
        hBox.getChildren().addAll(btConnect, btTable, btClose);
        hBox.setSpacing(10);
        gPane.add(hBox, 0, 5);

        //Add gPane to pane
        setupPane.getChildren().add(gPane);

        // Create a scene and place the pane in the stage
        dbPopUp.setScene(setupScene);
        dbPopUp.show();


        //On-Action for Connect
        btConnect.setOnAction(e -> {
            //Connect to database using information from fields
            try {
                connectToDB();
                rowSet.setUrl(url);
                rowSet.setUsername(username);
                rowSet.setPassword(password);
            } catch (Exception ex){}
        });

        //On-Action for create Table
        btTable.setOnAction(e -> {
            //Connect to database using information from fields
            try {
                executeSQLScript("DBScript.sql");
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        //On-Action for Closie
        btClose.setOnAction(e->{
            try{

                dbPopUp.close();
            }
            catch (Exception ex){}
        });

    return rowSet;
    }

    /** Connect to DB */
    private static void connectToDB() {
        // Get database information from the user input
        driver = (String) cboDriver
                .getSelectionModel().getSelectedItem();
        url = (String) cboURL.getSelectionModel().getSelectedItem() + "?autoReconnect=true&useSSL=false";
        username = tfUsername.getText().trim();
        password = pfPassword.getText().trim();

        // Connection to the database
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(
                    url, username, password);
            status.setText("Connected to database");
        }
        catch (java.lang.Exception ex) {
            status.setText("Connection Error!!");
            ex.printStackTrace();
        }
    }

    /** Execute SQL commands */
    private static void executeSQLScript(String filename) {
        //Check database connection
        if (connection == null) {
            status.setText("Please connect to a database first");
            return;
        }
        else {
            //Load file and parse to string array delineated by ;
            sqlCommand = getSqlCommand(filename);
            String[] commands = sqlCommand.replace('\n', ' ').split(";");

            for (String aCommand: commands) {
                //Check for SELECT statements
                if (aCommand.trim().toUpperCase().startsWith("SELECT")) {
                    status.setText("ERROR sql file has SELECT statements!!");
                    return;
                }
                else {
                    processSQLNonSelect(aCommand);
                }
            }
        }
    }

    /** Execute SQL DDL, and modification commands */
    private static void processSQLNonSelect(String sqlCommand) {
        try {
            // Get a new statement for the current connection
            statement = connection.createStatement();

            // Execute a non-SELECT SQL command
            statement.executeUpdate(sqlCommand);

            status.setText("SQL command executed");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            status.setText("Errror: Check SQL script");
        }
    }

    /**
     * Parse file contents to String
     * @param filename
     * @return
     */
    private static String getSqlCommand(String filename){
        //String object to append and return
        StringBuilder sqlCommand = new StringBuilder();

        //Scann in file contents to StringBuilder object
        try {
            Scanner fIn = new Scanner(new File(filename)).useDelimiter(";");
            while(fIn.hasNext()){
                sqlCommand.append(fIn.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Parse stringbuilder object to string and return
        return sqlCommand.toString();
    }
}

