package Other;

public class Product implements Saleable{
    private double price;
    private final String name;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name +
            ", price: " + price;
    }
}
