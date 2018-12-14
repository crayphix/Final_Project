/*
 * Class: CMSC214
 * Instructor: Alla Webb
 * Description: Control GUI for the library databases
 *
 * Due: 12/14/18
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Bryan Speelman
*/

import com.sun.rowset.JdbcRowSetImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sql.RowSet;
import java.awt.print.Book;
import java.sql.*;

public class LibraryControl extends Application {
    //SQL Variables
    private Statement statement;
    private static Connection connection;

    //Text
    private Text title = new Text("Library Controls"); // Title

    //Buttons
    private Button btConnect = new Button("Connect To Database");
    private Button btSearchISBN = new Button("Search by ISBN");
    private Button btSearchUsr = new Button("Search by UserID");
    private Button btCheckout = new Button("Checkout");
    private Button btReturn = new Button("Return");
    private Button btRntlCkUsr = new Button("Rental Check (User ID Only)");
    private Button btRnlCkBk = new Button("Check Status(Book ID only)");

    //TextBoxes
    private TextField tfISBN = new TextField(); //ISBN
    private TextField author = new TextField(); //Author
    private TextField bookIdIn = new TextField(); //BookID In
    private TextField bookIdOut = new TextField(); // Book ID Out
    private TextField usrId = new TextField(); // User ID
    private TextField name = new TextField(); // Name
    private TextField address = new TextField(); // Address
    private TextField usrIdCk = new TextField(); // User ID 2
    private TextField bookIdCk = new TextField(); // Book ID
    private TextField bookTitle = new TextField(); // Title

    //Vbox
    private VBox vBox = new VBox(); //VBox build main pane

    //HBoxes
    private HBox titleHBox = new HBox(); //HBox to hold title
    private HBox menuHBox = new HBox(); //HBox to hold grid pane of menus

    //Gridpanes
    private GridPane bookPane = new GridPane(); //Grid Pane to hold book info
    private GridPane userPane = new GridPane(); //Grid Pane to hold user info
    private GridPane checkPane = new GridPane(); //Grid pane to hold checkout status

    //TextArea
    private TextArea taStatus =
            new TextArea("Welcome to the Library Database Control System"); //Text Area to hold status

    //Rowsets
    private static RowSet rowSet = new JdbcRowSetImpl();

    @Override
    public void start(Stage primaryStage) {
        //Main Pane
        Pane pane = new Pane();

        //Vbox
        VBox vBox = new VBox();

        //Set title
        title.setText("Library Controls");
        title.setStyle("-fx-font-size: 20");

        titleHBox.getChildren().addAll(title, btConnect);
        titleHBox.setPadding(new Insets(5,5,5,5));
        titleHBox.setSpacing(550);

        //build book menu pane
        bookPane.add(new Text("ISBN: "), 0, 0);
        bookPane.add(tfISBN, 1, 0);

        bookPane.add(new Text("Title: "),0,2);
        bookPane.add(bookTitle, 1, 2);
        bookTitle.setEditable(false);

        bookPane.add(new Text("Author: "),0, 3);
        bookPane.add(author, 1,3);
        author.setEditable(false);

        bookPane.add(new Text("Book IDs in: "), 0, 4);
        bookPane.add(bookIdIn, 1, 4);
        bookIdIn.setEditable(false);
        bookIdIn.setMinHeight(70);

        bookPane.add(new Text("Book IDs out"), 0, 5);
        bookPane.add(bookIdOut, 1, 5);
        bookIdOut.setEditable(false);
        bookIdOut.setMinHeight(70);

        bookPane.add(btSearchISBN,0,6);
        bookPane.setStyle("-fx-border-color: BLACK; -fx-spacing: 10");
        bookPane.setVgap(5);

        //build user menu
        userPane.add(new Text("User ID: "), 0, 0);
        userPane.add(usrId, 1,0);

        userPane.add(new Text("Name: "), 0, 1);
        userPane.add(name, 1, 1);
        name.setEditable(false);

        userPane.add(new Text("Address: "), 0, 2);
        userPane.add(address,1,2);
        address.setEditable(false);
        address.setMinHeight(70);

        userPane.add(btSearchUsr,0,3);
        userPane.setStyle("-fx-border-color: BLACK; -fx-spacing: 10");
        userPane.setVgap(5);

        //build checkout menu
        checkPane.add(new Text("User ID: "),0,0);
        checkPane.add(usrIdCk, 1,0);

        checkPane.add(new Text("Book ID: "),0,1);
        checkPane.add(bookIdCk, 1,1);

        checkPane.add(new Text("Check Out Book"),0 ,2);
        checkPane.add(btCheckout,1,2);

        checkPane.add(new Text("Return Book"), 0, 3);
        checkPane.add(btReturn, 1, 3);

        checkPane.add(new Text("Check Rentals By User ID"), 0, 4);
        checkPane.add(btRntlCkUsr, 1, 4);

        checkPane.add(new Text("Check Rental by Book ID"), 0, 5);
        checkPane.add(btRnlCkBk, 1, 5);

        checkPane.setStyle("-fx-border-color: BLACK; -fx-spacing: 10");
        checkPane.setVgap(5);

        //Build main pane
        taStatus.setMinSize(700, 150);
        taStatus.setScrollTop(0);

        menuHBox.setSpacing(10);
        menuHBox.getChildren().addAll(bookPane, userPane, checkPane);

        vBox.getChildren().addAll(titleHBox, menuHBox, taStatus);
        pane.getChildren().addAll(vBox);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 955, 450);
        primaryStage.setTitle("Library Software"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        //button that activates pop up window to get connection
        btConnect.setOnAction(e->{
            if(rowSet.getUsername() == null) {
                try {
                    rowSet = DBSetup.display();
                } catch (Exception ex) {
                    taStatus.appendText("Error connecting to database!");
                }
            }
            else
                taStatus.appendText("\nConnected to Database");
            });

        //button that searches for bookinfo from isbn number
        btSearchISBN.setOnAction(e-> {
            try {
                //Clear TextFields
                author.clear();
                bookTitle.clear();
                bookIdIn.clear();
                bookIdOut.clear();

                //Run SQL commands
                processISBNSearch("Select * from books where isbn = '" +
                        tfISBN.getText().trim() + "'");
            } catch (Exception e1) {
                taStatus.appendText("\nCheck connection");
            }
        });

        //button that searches users from user ID
        btSearchUsr.setOnAction(e->{
            try{
                //Clear TextFields
                name.clear();
                address.clear();

                //Run Sql commands
                processUserSearch("select * from users where userId = '"
                        + usrId.getText().trim() + "'" );
            }
            catch (Exception ex){
                ex.printStackTrace();
                taStatus.appendText("\nCheck Connection");
            }
        });

        //button that process checkout
        btCheckout.setOnAction(e->{
            checkoutBook();
        });


    }

    private void checkoutBook() {
        try {
            //Check userId
            rowSet.setCommand("Select * from users " +
                    "where userId ='" + usrIdCk.getText().trim() + "'");
            rowSet.execute();
            if(!rowSet.next()){
                taStatus.appendText("\nUser does not exist");
                return;
            }

            //Check bookId
            rowSet.setCommand("Select * from books " +
                    "where bookId ='" + bookIdCk.getText().trim() + "' " +
                    "and checkout = 0");
            rowSet.execute();
            if(!rowSet.next()){
                taStatus.appendText("\nBook does not exist or is unavailable");
                return;
            }

            //update database
            rowSet.setCommand("update books " +
                    "set checkout = 1, userId = '" + usrIdCk.getText().trim() + "' "+
                    "where bookId = '" + bookIdCk.getText().trim() + "'");
            taStatus.appendText("\nBook # " + bookIdCk.getText().trim() +
                    " checked out to user # " + usrIdCk.getText().trim());
        }
        catch (SQLException ex){
            ex.printStackTrace();
            taStatus.appendText("\nCheck Connection");
        }
    }

    private void processUserSearch(String sqlCommand) {
        PatronBean userBean = new PatronBean();
        try{
            //execute search
            rowSet.setCommand(sqlCommand);
            rowSet.execute();

            //map info to userBean
            while(rowSet.next()) {
                userBean.setUsrId((String) rowSet.getObject(1));
                userBean.setName((String) rowSet.getObject(2));
                userBean.setAddress((String) rowSet.getObject(3));
            }
            //output to pane
            name.setText(userBean.getName());
            address.setText(userBean.getAddress());

            //Check correct input
            if(userBean.getName() == null)
                taStatus.appendText("\nUser does not exist!");
        }
        catch (SQLException ex){
            ex.printStackTrace();
            taStatus.appendText("\nCheck Connection");
        }
    }

    /** Execute SQL SELECT commands */
    private void processISBNSearch(String sqlCommand) {
        BookBean bookBean = new BookBean();
        try {
                rowSet.setCommand(sqlCommand);
                rowSet.execute();
                while(rowSet.next()){
                    //Map information
                    bookBean.setBookId((String)rowSet.getObject(1));
                    bookBean.setIsbn(String.valueOf(rowSet.getObject(2)));
                    bookBean.setTitle(String.valueOf(rowSet.getObject(3)));
                    bookBean.setAuthor(String.valueOf(rowSet.getObject(4)));
                    bookBean.setCheckout((Boolean)rowSet.getObject(5));

                    //Send Checkout info to in or out pane
                    if(bookBean.getCheckout() != true){
                        bookIdIn.appendText(bookBean.getBookId() + ", ");
                    }
                    else{
                        bookIdOut.appendText(bookBean.getBookId() + ", ");
                    }
                }
                //Set textfield info
                author.setText(bookBean.getAuthor());
                bookTitle.setText(bookBean.getTitle());

                //Check if isbn exists
                if(bookBean.getAuthor() == null)
                    taStatus.appendText("\nISBN does not exist");
            }
            catch (SQLException e) {
               taStatus.appendText("Check connection\n");
            }

    }
}
