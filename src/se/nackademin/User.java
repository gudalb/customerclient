package se.nackademin;

public class User {
    String name;
    String surname;
    String password;
    int id;
    int lastOrderId = 0;

    User (String name,String surname, String password, int id) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", lastOrderId=" + lastOrderId +
                '}';
    }
}
