public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        if (name != null) {
            return name + " (" + price + ")";
        }
        return "(" + price + ")";
    }
}
