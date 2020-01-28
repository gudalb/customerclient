package se.nackademin;

public class cartItem {
    String brand;
    int id;
    double price;
    String name;
    int itemsize;
    String color;

    public cartItem(String brand, int id, double price, String name, int itemsize, String color) {
        this.brand = brand;
        this.id = id;
        this.price = price;
        this.name = name;
        this.itemsize = itemsize;
        this.color = color;
    }

    @Override
    public String toString() {
        return "cartItem{" +
                "brand='" + brand + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", itemsize=" + itemsize +
                ", color='" + color + '\'' +
                '}' + "\n";
    }
}
