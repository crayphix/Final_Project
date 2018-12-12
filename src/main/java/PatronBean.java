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
    private int usrId;
    private String name;
    private String address;

    PatronBean(){};

    PatronBean(String name, String address){
        this.name = name;
        this.address = address;
    }

    //getters
    public int getUsrId(){
        return usrId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    //Setters
    public void setUsrId(int usrId){
        this.usrId = usrId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
