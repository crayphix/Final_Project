import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
public class LibraryControl extends Application {
    //Text
    private Text title = new Text("Library Controls"); // Title
    //Buttons
    private Button btConnect = new Button("Connect");
    private Button btSearchISBN = new Button("Search by ISBN");
    private Button btSearchUsr = new Button("Search by User");
    private Button btCheckout = new Button("Checkout");
    private Button btReturn = new Button("Return");
    private Button btRntlCkUsr = new Button("Rental Check (User ID Only)");
    private Button btRnlCkBk = new Button("Check Status(Book ID only)");
    //TextBoxes
    private TextField isbn = new TextField(); //ISBN
    private TextField author = new TextField(); //Author
    private TextField bookIdIn = new TextField(); //BookID In
    private TextField bookIdOut = new TextField(); // Book ID Out
    private TextField usrId = new TextField(); // User ID
    private TextField name = new TextField(); // Name
    private TextField address = new TextField(); // Address
    private TextField usrid2 = new TextField(); // User ID 2
    private TextField bookId = new TextField(); // Book ID
    private TextField bookTitle = new TextField(); // Title

    //HBoxes
    //Gridpanes
    //Panes
    Pane pane = new Pane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Main Pane
            //Grid Pane
                //(0,0) (3, 0) HBox to hold Title and connection button
                    //Button to Connect to Database
                    //Add children to hbox
                    //Add HBox to gridpane
                //(0, 1) GridPane to hold Book Info
                //Textbox w/ Label ISBN

        //Textboax w/ Label Title
        //TextBox w/ label Author
        //TextBox w/ label Book ID's CheckedIn
        //TextBox w/ label Book Id's Checkedout
        //Button to search by isbn
        //(1, 1) VBox to hold User info
        //Textbox w/ label User Id
        //Textbox w/ label Name
        //TextBox w/ label Address
        //Button to search by userId
        //(2, 1) VBox to hold Book status and activation
        //TextBox w/ label User Id
        //TextBox w/ label Book Id
        //Button To checkout Book
        //Button to return book
        //Button to check rentals by User ID
        //Button to check status by Book Id
        //(0, 2) TextArea(Scrollable) for giving detailed information by line
    }
}
