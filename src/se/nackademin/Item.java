package se.nackademin;

public class Item {
    int stock;
    String brand;
    int id;
    double price;
    String name;
    int itemsize;
    String color;

    public Item(int stock, String brand, int id, double price, String name, int itemsize, String color) {
        this.stock = stock;
        this.brand = brand;
        this.id = id;
        this.price = price;
        this.name = name;
        this.itemsize = itemsize;
        this.color = color;
    }

    @Override
    public String toString() {
        return "item{" +
                "stock=" + stock +
                ", brand='" + brand + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", itemsize=" + itemsize +
                ", color='" + color + '\'' +
                '}' + "\n";
    }
}
