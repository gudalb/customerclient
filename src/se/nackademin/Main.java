package se.nackademin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws ClassNotFoundException, IOException {

        Repository repo = new Repository();
        Menu menu = new Menu(repo);
        User user = menu.loginmenu();



        menu.alternativesMenu();




    }
}
