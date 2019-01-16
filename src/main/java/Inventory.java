import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class InsufficientInventory extends Exception {
    public InsufficientInventory(int currentInventory, int requestedInventory) {
        super("There is not enough inventory for this product. " +
                "Current Inventory: " + currentInventory + " Needed: " + requestedInventory);
    }
}

public class Inventory {
    private Set<Product> products = new HashSet<>();

    void addProduct(String productId, double price, int quantity) {
        Product product = getProduct(productId);
        if (product != null) {
            product.addStock(quantity);
            if (product.getPrice() != price) {
                product.setPrice(price);
            }

        } else {
            products.add(new Product(productId, price, quantity));
        }
    }

    void removeProduct(String productId, int quantity) throws InsufficientInventory {
        Product product = getProduct(productId);
        if (product == null) {
            throw new InsufficientInventory(0, quantity);
        } else if (product.getQuantity() < quantity) {
            throw new InsufficientInventory(product.getQuantity(), quantity);
        } else if (product.getQuantity() == quantity) {
            products.remove(product);
        } else {
            product.removeStock(quantity);
        }
    }

    boolean inStock(String productId) {
        Product product = getProduct(productId);
        return product != null && product.getQuantity() > 0;
    }

    double totalInventoryValue() {
        double value = 0.0;
        for (Product product : products) {
            value += product.getPrice() * product.getQuantity();
        }

        return value;
    }

    Product getProduct(String productId) {
        for (Product product: products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

    String getAllProductNames() {
        List<String> productIds = new ArrayList<>();
        for (Product product : products) {
            productIds.add(product.getProductId());
        }

        return String.join(", ", productIds);
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addProduct("milk", 3.5, 1);
        inventory.addProduct("banana", .6, 1);

        System.out.println(inventory.getAllProductNames());
    }
}
