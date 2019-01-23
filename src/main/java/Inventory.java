import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InsufficientInventory extends Exception {
    public InsufficientInventory(int currentInventory, int requestedInventory) {
        super("There is not enough inventory for this product. " +
                "Current Inventory: " + currentInventory + " Needed: " + requestedInventory);
    }
}

public class Inventory {
    private Map<String, Product> products = new HashMap<>();

    Map<String, Product> getProducts() {
        return this.products;
    }

    void addProduct(String productId, double price, int quantity) {
        Product product = products.get(productId);
        if (product != null) {
            product.addStock(quantity);
            if (product.getPrice() != price) {
                product.setPrice(price);
            }

        } else {
            products.put(productId, new Product(productId, price, quantity));
        }
    }

    void removeProduct(String productId, int quantity) throws InsufficientInventory {
        Product product = products.get(productId);
        if (product == null) {
            throw new InsufficientInventory(0, quantity);
        } else if (product.getQuantity() < quantity) {
            throw new InsufficientInventory(product.getQuantity(), quantity);
        } else if (product.getQuantity() == quantity) {
            products.remove(productId);
        } else {
            product.removeStock(quantity);
        }
    }

    boolean inStock(String productId) {
        Product product = products.get(productId);
        return product != null && product.getQuantity() > 0;
    }

    double totalInventoryValue() {
        double value = 0.0;
        for (Product product : products.values()) {
            value += product.getPrice() * product.getQuantity();
        }

        return value;
    }

    String getAllProductNames() {
        List<String> productIds = new ArrayList<>();
        for (Product product : products.values()) {
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
