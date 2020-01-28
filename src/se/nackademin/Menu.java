package se.nackademin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Repository repo;
    Scanner sc = new Scanner(System.in);
    User user;
    List<Item> items = new ArrayList<>();

    public Menu(Repository repo) {
        this.repo = repo;
    }

    public void loginmenu() {
        String name = "";
        String surname = "";
        String password = "";
        int userID = 0;

        while (userID == 0) {
            System.out.println("Login \nFirst name:");
            name = sc.nextLine();
            System.out.println("Surname:");
            surname = sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();

            userID = repo.verifyLogin(name, surname, password);

            if (userID != 0)
                System.out.println("account details verified, welcome " + name + "!");
            else
                System.out.println("incorrect login");
        }

        user = new User(name, surname, password, userID);
    }

    public void alternativesMenu() {

        while (true) {
            String alternative = "";
            System.out.println("1. to see all items \n2. to order item\n3. to show shopping cart\n4. to exit");
            alternative = sc.nextLine();



            switch (alternative) {
                case "1":
                    int count = 1;
                    items = repo.getItems();
                    for(Item i: items) {
                        System.out.println("Product number: " + count +" -- " + i);
                        count++;
                    }
                    break;
                case "2":
                    System.out.println("Enter item id to add:");
                    repo.addToCart(user, items.get(sc.nextInt()-1).id);
                    break;
                case "3":
                    repo.showCart(user);
                    break;
                case "4":
                    System.exit(0);

            }

        }
    }
}