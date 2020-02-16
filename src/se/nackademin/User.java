package se.nackademin;

public class User {
    String name;
    String ssnumber;
    String password;
    int id;

    User (String name, String ssnumber, String password, int id) {
        this.name = name;
        this.ssnumber = ssnumber;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ssnumber='" + ssnumber + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
