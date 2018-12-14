/*
 * Class: CMSC214
 * Instructor: Alla Webb
 * Description: Bean to store patron info
 *
 * Due: 12/14/18
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Bryan Speelman
*/

public class PatronBean {

    //Variables
    private String usrId;
    private String name;
    private String address;
    private String[] books;

    PatronBean(){};

    PatronBean(String name, String address){
        this.name = name;
        this.address = address;
    }

    //getters
    public String getUsrId(){
        return usrId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String[] getBooks(){return books;}

    //Setters
    public void setUsrId(String usrId){
        this.usrId = usrId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBooks(String[] books){this.books = books;}

    //ToString
    @Override
    public String toString() {
        return "PatronBean{" +
                "userId'" + usrId + '\'' +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
