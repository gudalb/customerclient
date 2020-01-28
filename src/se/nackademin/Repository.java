package se.nackademin;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {
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

    public int verifyLogin(String name, String surname, String password) {
        ResultSet result;
        boolean verified = false;
        int id = 0;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement userCount = con.prepareStatement("select customerID from customer " +
                        "where name like ? and surname like ? and password like ?;"
                );
        ) {

            userCount.setString(1, name);
            userCount.setString(2, surname);
            userCount.setString(3, password);
            result = userCount.executeQuery();

            while (result.next()) {
                id = result.getInt("customerID");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

        return id;
    }

    public void getItems() {
        ResultSet result;
        int stock;
        String brand;
        int id;
        double price;
        String name;
        int itemsize;
        String color;
        List<Item> items;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement userCount = con.prepareStatement("select *  from items");

        ) {

            result = userCount.executeQuery();
            items = new ArrayList<>();

            while (result.next()) {
                stock = result.getInt("stock");
                brand = result.getString("brand");
                id = result.getInt("id");
                price = result.getDouble("price");
                name = result.getString("name");
                itemsize = result.getInt("itemsize");
                color = result.getString("color");

                Item item = new Item(stock, brand, id, price, name, itemsize, color);
                items.add(item);
            }

            System.out.println(items);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ;

    }

    public void addToCart(User user, int itemID) {
        ResultSet result = null;
        ResultSet firstorderMessage = null;
        ResultSet orderId;
        int lastOrderId = user.lastOrderId;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement addtocart = con.prepareStatement("call addtocart(?,?,?);");
                PreparedStatement addtocartnopo = con.prepareStatement("call addtocart(?,null,?);");
                PreparedStatement orderid = con.prepareStatement("select max(id) as id from newpo\n" +
                        "where customerID = ?;");

        ) {


            if(firstorder == 0) {
                addtocartnopo.setInt(1, user.id);
                addtocartnopo.setInt(2, itemID);
                firstorderMessage = addtocartnopo.executeQuery();
                firstorder = 1;
            }
            else {
                addtocart.setInt(1, user.id);
                addtocart.setInt(2, lastOrderId);
                addtocart.setInt(3, itemID);
                result = addtocart.executeQuery();
            }

            if(firstorder == 1) {
                orderid.setString(1, String.valueOf(user.id));
                orderId = orderid.executeQuery();

                while (orderId.next()) {
                    lastOrderId = orderId.getInt("id");
                    user.lastOrderId = lastOrderId;
                }
                firstorder = 2;
            }

            if (firstorderMessage != null) {
                while (firstorderMessage.next()) {
                    String resultMessage = firstorderMessage.getString("message");
                    System.out.println(resultMessage);
                }
            }
            if (result != null) {
                while (result.next()) {
                    String resultMessage = result.getString("message");
                    System.out.println(resultMessage);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showCart (User user) {
        ResultSet result;
        String brand;
        int id;
        double price;
        String name;
        int itemsize;
        String color;
        List<cartItem> items;

        try (
                Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                        p.getProperty("name"), p.getProperty("password"));
                PreparedStatement getCart = con.prepareStatement("call getlastcustomercart(?)");

        ) {

            if (firstorder == 0) {
                System.out.println("Current shopping cart is empty.");
            } else {
                getCart.setInt(1, user.id);
                result = getCart.executeQuery();
                items = new ArrayList<>();

                while (result.next()) {
                    brand = result.getString("brand");
                    id = result.getInt("id");
                    price = result.getDouble("price");
                    name = result.getString("name");
                    itemsize = result.getInt("itemsize");
                    color = result.getString("color");

                    cartItem item = new cartItem(brand, id, price, name, itemsize, color);
                    items.add(item);
                }

                System.out.println(items);

            }
            } catch(SQLException e){
                e.printStackTrace();
            }

    }
}
