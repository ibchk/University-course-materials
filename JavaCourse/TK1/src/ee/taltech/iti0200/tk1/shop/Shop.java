import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Shop {
    private List<Product> products = new LinkedList<>();

    public Shop() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product product) {
        for (Product thing : products) {
            if (thing.getName().equals(product.getName()) && thing.getPrice() == product.getPrice()) {
                return false;
            }
        }
        if (product.getPrice() >= 0) {
            products.add(product);
            return true;
        }
        return false;
    }

    public Optional<Product> sellProduct(String name, int maxPrice) {
        Product thing = null;
        for (Product product : products) {
            if (product.getName().equals(name) && maxPrice >= product.getPrice()
                    && (thing == null || thing.getPrice() < product.getPrice())) {
                thing = product;
            }
        }
        products.remove(thing);
        return (thing == null) ? Optional.empty() : Optional.of(thing);
    }
}
