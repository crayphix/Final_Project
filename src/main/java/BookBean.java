/*
 * Class: CMSC214
 * Instructor: Alla Webb
 * Description: Bean to store book info
 *
 * Due: 12/14/18
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Bryan Speelman
*/

public class BookBean {

    //Variables
    private int bookId;
    private String isbn;
    private String title;
    private String author;
    private Boolean checkout;

    //Constructors
    public BookBean() {}

    public BookBean(String isbn, String title, String author, Boolean checkout) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.checkout = checkout;
    }

    //Getters
    public int getBookId(){
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Boolean getCheckout() {
        return checkout;
    }

    //Setters
    public void setBookId(int bookId){
        this.bookId = bookId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }

    //ToString
    @Override
    public String toString() {
        return "BookBean{" +
                "bookId'" + bookId + '\'' +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", checkout=" + checkout +
                '}';
    }
}
