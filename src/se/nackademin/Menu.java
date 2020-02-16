package se.nackademin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Repository repo;
    Scanner sc = new Scanner(System.in);
    User user;

    public Menu(Repository repo) {
        this.repo = repo;
    }

    public void loginmenu() {
        String ssnumber = "";
        String password = "";
        User user;

        while (true) {
            System.out.println("Social security number:");
            ssnumber = sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();

            user = repo.verifyLogin(ssnumber, password);
            this.user = user;

            if (user != null) {
                System.out.println("account details verified. Welcome " + user.name);
                break;
            }
            else
                System.out.println("incorrect login");
        }
    }

    public void alternativesMenu() {


        while (true) {
            String alternative = "0";
            System.out.println("1. to show accounts \n2. to show loans\n3. to withdraw\n4. to exit");
            alternative = sc.nextLine();



            switch (alternative) {

                case "1":
                    int count = 1;
                    repo.getAccounts(user);
                    for(Account account: repo.accounts) {
                        System.out.print(count + ". ");
                        System.out.println(account);
                        count ++;
                    }
                    break;
                case "2":
                    count = 1;
                    repo.getLoans(user);
                    for(Loan loan: repo.loans) {
                        System.out.print(count + ". ");
                        System.out.println(loan);
                        count++;
                    }
                    break;
                case "3":
                    System.out.println("Account number:");
                    int selectedAccount = sc.nextInt();
                    selectedAccount = repo.accounts.get(selectedAccount -1).id;
                    System.out.println("Amount:");
                    double amount = sc.nextDouble();
                    System.out.println(repo.withdraw(user, selectedAccount, amount));
                    break;

                case "4":
                    System.exit(0);

            }

        }
    }
}