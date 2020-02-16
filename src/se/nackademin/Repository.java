package se.nackademin;

import javax.swing.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
    List<Loan> loans;
    List<Account> accounts;
    Properties p = new Properties();
    int firstorder = 0;

    public Repository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            p.load(new FileInputStream("src/se/nackademin/settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User verifyLogin(String ssnumber, String password) {
        ResultSet result;
        int id = 0;
        String name = "";
        User user;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement userCount = con.prepareStatement("select id,name from customer " +
                        "where ssnumber like ? and password like ?;"
                );
        ) {

            userCount.setString(1, ssnumber);
            userCount.setString(2, password);
            result = userCount.executeQuery();

            while (result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
            }

            if (id == 0) {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        user = new User(name, ssnumber, password, id);
        getAccounts(user);
        getLoans(user);
        return user;

    }


    public List<Loan> getLoans(User user) {
        ResultSet result;
        int id;
        double amount;
        double interestRate;
        List<Loan> loans = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement getLoans = con.prepareStatement("select id, amount, interest_rate from loan " +
                        "where customer_id = ?;");

        ) {

            getLoans.setInt(1, user.id);

            result = getLoans.executeQuery();
            loans = new ArrayList<>();

            while (result.next()) {
                id = result.getInt("id");
                amount = result.getDouble("amount");
                interestRate = result.getDouble("interest_rate");

                Loan loan = new Loan(id, amount, interestRate);
                loans.add(loan);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.loans = loans;
        return loans;
    }

    public List<Account> getAccounts(User user) {
        ResultSet result;
        int id;
        double amount;
        double interestRate;
        List<Account> accounts = new ArrayList<>();

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement getLoans = con.prepareStatement("select id, balance, interest_rate from account " +
                        "where customer_id = ?;");

        ) {

            getLoans.setInt(1, user.id);

            result = getLoans.executeQuery();
            accounts = new ArrayList<>();

            while (result.next()) {
                id = result.getInt("id");
                amount = result.getDouble("balance");
                interestRate = result.getDouble("interest_rate");

                Account account = new Account(id, amount, interestRate);
                accounts.add(account);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.accounts = accounts;
        return accounts;
    }

    public String withdraw(User user,int accountID, double amount) {
        ResultSet result;
        String message = "";

        if (amount < 0)
            amount *= -1;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                CallableStatement withdraw = con.prepareCall("call transaction(?,?,?)");

        ) {

            withdraw.setInt(1, user.id);
            withdraw.setInt(2, accountID);
            withdraw.setDouble(3, amount * -1);

            result = withdraw.executeQuery();

            while (result.next()) {
                message = result.getString("message");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }
}
